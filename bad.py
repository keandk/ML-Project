import os
import json

directory = r"data_cpp\json"

# Duyệt từng file trong thư mục
count_bad = 0
for filename in os.listdir(directory):
    if filename.endswith(".json"):
        file_path = os.path.join(directory, filename)
        print(f"📂 Đang xử lý file: {file_path}")

        # Đọc nội dung file JSON
        with open(file_path, "r", encoding="utf-8") as f:
            data = json.load(f)

        # Kiểm tra nếu có 'testCases'
        testcases = data.get('testCases', [])
        print(f"  ➡️ Có {len(testcases)} test cases.")

        # Duyệt từng test case
        for testcase in testcases:
            # Bạn có thể xử lý mỗi testcase ở đây
            
            if testcase['sarif']['runs'][0]['properties']['state'] == 'bad':
                count_bad += 1
print(f"🔚 Tổng số test cases 'bad': {count_bad}")
