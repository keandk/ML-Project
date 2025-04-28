import requests
import os
import zipfile
import json
from io import BytesIO
# Thay đổi biến này để lấy dữ liệu cho ngôn ngữ khác
LANGUAGE = "c" 

# Các folder
zip_dir = f"data_{LANGUAGE}/zips"
unzip_dir = f"data_{LANGUAGE}/unzips"
json_dir = f"data_{LANGUAGE}/json"
summary_file = f"data_{LANGUAGE}/summary.json"

# Tạo thư mục nếu chưa tồn tại
os.makedirs(zip_dir, exist_ok=True)
os.makedirs(unzip_dir, exist_ok=True)
os.makedirs(json_dir, exist_ok=True)

url = "https://samate.nist.gov/SARD/api/test-cases/search"

# Target
total_target = 4000
good_target = total_target // 2  # 2000 good
bad_target = total_target // 2   # 2000 bad

good_list = []
bad_list = []
summary = []  # nơi lưu thông tin summary

page = 1
limit_per_page = 100  # lấy mỗi lần nhiều testcase hơn

# ======== Giai đoạn 1: Search + GET json =========
while len(good_list) < good_target:
    params = {
        "language[]": LANGUAGE,
        "state[]": ["good"],  # 🎯 ban đầu chỉ lấy good và bad
        "page": page,
        "limit": limit_per_page
    }
    response = requests.get(url, params=params)
    data = response.json()

    testcases = data.get('testCases', [])
    if not testcases:
        print(f"⚠️ Không còn testcases good ở page {page}, dừng lại.")
        break

    print(f"✅ Page {page} — tìm được {len(testcases)} testcases.")

    # Save JSON page
    json_path = os.path.join(json_dir, f"data-good-{page}.json")
    with open(json_path, "w", encoding="utf-8") as f:
        json.dump(data, f, ensure_ascii=False, indent=2)

    for test_case in testcases:
        download_url = test_case.get('download')
        identifier = test_case.get('identifier')

        if not download_url or not identifier:
            continue

        # Lấy state từ properties
        try:
            sarif = test_case.get('sarif', {})
            runs = sarif.get('runs', [])
            if runs:
                properties = runs[0].get('properties', {})
                state = properties.get('state')
            else:
                state = None
        except Exception as e:
            state = None

        if state == 'good' and len(good_list) < good_target:
            good_list.append((identifier, download_url, state))
        if len(good_list) >= good_target:
            break
    page += 1
page = 1
print(f"\n📊 Sau bước 1: GOOD={len(good_list)}, BAD={len(bad_list)}")
# ======== Giai đoạn 2: Search + GET json BAD=========
while len(bad_list) < bad_target:
    params = {
        "language[]": LANGUAGE,
        "state[]": ["bad"],  # 🎯 ban đầu chỉ lấy good và bad
        "page": page,
        "limit": limit_per_page
    }
    response = requests.get(url, params=params)
    data = response.json()

    testcases = data.get('testCases', [])
    if not testcases:
        print(f"⚠️ Không còn testcases bad ở page {page}, dừng lại.")
        break

    print(f"✅ Page {page} — tìm được {len(testcases)} testcases.")

    # Save JSON page
    json_path = os.path.join(json_dir, f"data-bad-{page}.json")
    with open(json_path, "w", encoding="utf-8") as f:
        json.dump(data, f, ensure_ascii=False, indent=2)

    for test_case in testcases:
        download_url = test_case.get('download')
        identifier = test_case.get('identifier')

        if not download_url or not identifier:
            continue

        # Lấy state từ properties
        try:
            sarif = test_case.get('sarif', {})
            runs = sarif.get('runs', [])
            if runs:
                properties = runs[0].get('properties', {})
                state = properties.get('state')
            else:
                state = None
        except Exception as e:
            state = None

        if state == 'bad' and len(bad_list) < bad_target:
            bad_list.append((identifier, download_url, state))
        if len(bad_list) >= bad_target:
            break
    page += 1

print(f"\n📊 Sau bước 2: GOOD={len(good_list)}, BAD={len(bad_list)}")

# ======= Giai đoạn 3: Nếu thiếu thì lấy thêm mixed =========
if len(good_list) < good_target or len(bad_list) < bad_target:
    print("\n🔄 Không đủ, tiếp tục search 'mixed' để bù...")
    page_mixed = 1
    while (len(good_list) + len(bad_list)) < total_target:
        print(len(good_list) + len(bad_list))
        params = {
            "language[]": LANGUAGE,
            "state[]": ["mixed"], 
            "page": page_mixed,
            "limit": limit_per_page
        }
        response = requests.get(url, params=params)
        data = response.json()

        testcases = data.get('testCases', [])
        if not testcases:
            print(f"⚠️ Không còn testcase mixed ở page {page_mixed}, dừng lại.")
            break

        print(f"✅ [MIXED] Page {page_mixed} — tìm được {len(testcases)} testcases.")

        json_path = os.path.join(json_dir, f"data-mixed-{page_mixed}.json")
        with open(json_path, "w", encoding="utf-8") as f:
            json.dump(data, f, ensure_ascii=False, indent=2)

        for test_case in testcases:
            download_url = test_case.get('download')
            identifier = test_case.get('identifier')

            if not download_url or not identifier:
                continue

            # Dán state="mixed"
            state = "mixed"

            if len(good_list) < good_target:
                good_list.append((identifier, download_url, state))
            elif len(bad_list) < bad_target:
                bad_list.append((identifier, download_url, state))

            # 🎯 Check tổng số lượng đã đủ chưa
            if (len(good_list) + len(bad_list)) >= total_target:
                break  # đủ rồi thì thoát for loop luôn
        page_mixed += 1

print(f"\n✅ Đã collect đủ: GOOD={len(good_list)}, BAD={len(bad_list)} (bao gồm một phần mixed nếu cần)")
# ======== Giai đoạn 3: Download + Unzip =========

total_downloaded = 0
print("\n🚛 Bắt đầu tải file zip...")

for group_name, case_list in [('good', good_list), ('bad', bad_list)]:
    for identifier, download_url, state in case_list:
        try:
            zip_filename = download_url.split("/")[-1]
            zip_path = os.path.join(zip_dir, zip_filename)
            unzip_path = os.path.join(unzip_dir, identifier)

            # Download file zip
            zip_response = requests.get(download_url)
            zip_response.raise_for_status()

            # Lưu file zip
            with open(zip_path, 'wb') as f:
                f.write(zip_response.content)

            # Giải nén
            os.makedirs(unzip_path, exist_ok=True)
            with zipfile.ZipFile(BytesIO(zip_response.content)) as zip_ref:
                zip_ref.extractall(unzip_path)

            total_downloaded += 1

            # Ghi thông tin vào summary
            summary.append({
                "id": identifier,
                "state": state,
                "path": os.path.abspath(unzip_path)
            })

            if total_downloaded % 50 == 0:
                print(f"✅ Đã tải {total_downloaded} testcase")

        except Exception as e:
            print(f"❌ Lỗi với {identifier}: {e}")

# ======== Giai đoạn 3: Save Summary =========

with open(summary_file, "w", encoding="utf-8") as f:
    json.dump(summary, f, ensure_ascii=False, indent=2)

print(f"\n📝 Đã ghi metadata vào {summary_file}")
print(f"\n🔚 Hoàn thành! Tổng số testcases đã tải: {total_downloaded}")
