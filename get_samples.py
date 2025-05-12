# %%
""" Download theo tung loai GOOD (2000) , BAD (2000) 
if lack of GOOD/BAD -> caculate number(MIXED) = 4000 - number(GOOD) - number(BAD)
"""
import os
import json
import requests
from tqdm import tqdm
import subprocess
from concurrent.futures import ThreadPoolExecutor, as_completed
from functools import partial
import time
import urllib3, shutil
import zipfile
LANGUAGE = "cpp"
CLASSIFY = "bad"
ZIP_DIR = f"data_{LANGUAGE}/zips"
UNZIP_DIR = f"data_{LANGUAGE}/unzips"
JSON_DIR = f'data_{LANGUAGE}/json'
SRC_DIR = f'data_{LANGUAGE}/{LANGUAGE}-src'
# Create directories if they don't exist
os.makedirs(JSON_DIR, exist_ok=True)
os.makedirs(ZIP_DIR, exist_ok=True)
os.makedirs(UNZIP_DIR, exist_ok=True)
os.makedirs(SRC_DIR, exist_ok=True)

# %%

"""GET JSON testcases file of each page following state (BAD/MIXED/GOOD)"""
lang = "cplusplus" 
def get_test_cases(page):
    url = f"https://samate.nist.gov/SARD/api/test-cases/search?language%5B%5D={lang}&state%5B%5D={CLASSIFY}&page={page}&limit=100"
    try:
        response = requests.get(url)
        if response.status_code == 200:
            data = response.json()
            # Fix the directory path to match the one we created
            filename = f'data_{LANGUAGE}/json/data-{CLASSIFY}-{page}.json'
            with open(filename, 'w') as f:
                json.dump(data, f, indent=2)
            return f"Downloaded page {page}"
        else:
            return f"Failed to download page {page}: HTTP {response.status_code}"
    except Exception as e:
        return f"Error downloading page {page}: {str(e)}"

# Get first page to check total records
first_page = requests.get(f"https://samate.nist.gov/SARD/api/test-cases/search?language%5B%5D={lang}&state%5B%5D={CLASSIFY}&page=1&limit=100")

"""Change number of sample"""
total_records = min(first_page.json()['total'], 2000)
print(total_records)
total_pages = (total_records + 99) // 100  # Ceiling division

# Download pages in parallel
with ThreadPoolExecutor(max_workers=10) as executor:
    futures = [executor.submit(get_test_cases, page) for page in range(1, total_pages + 1)]
    
    for future in tqdm(as_completed(futures), total=len(futures), desc="Downloading pages"):
        try:
            result = future.result()
            if "Error" in result or "Failed" in result:
                print(f"\n{result}")
        except Exception as e:
            print(f"\nUnexpected error: {str(e)}")

# %%

"""Download source code zip to ZIP_DIR 
1. duyet qua tung file json dua vao CLASSIFY
2. Lay list download links theo keyword testcase
"""
# Increase max retries for the urllib3 connection pool
urllib3.util.retry.Retry.DEFAULT.backoff_factor = 1
urllib3.util.retry.Retry.DEFAULT.total = 5

def download_file(url, download_dir, max_retries=3, retry_delay=2):
    filename = url.split('/')[-1]
    # Add -mixed suffix before .zip extension
    filename_parts = filename.rsplit('.', 1)
    filename = f"{filename_parts[0]}-{CLASSIFY}.{filename_parts[1]}"
    
    filepath = os.path.join(download_dir, filename)
    
    # Skip if file already exists and has size > 0
    if os.path.exists(filepath) and os.path.getsize(filepath) > 0:
        return f"Skipped {filename} (already exists)"
    
    for attempt in range(max_retries):
        try:
            # Create a session with custom settings
            session = requests.Session()
            session.mount('https://', requests.adapters.HTTPAdapter(
                max_retries=urllib3.util.Retry(
                    total=5,
                    backoff_factor=1,
                    status_forcelist=[500, 502, 503, 504]
                )
            ))
            
            # Download with increased timeout
            response = session.get(url, stream=True, timeout=30)
            if response.status_code == 200:
                with open(filepath, 'wb') as f:
                    for chunk in response.iter_content(chunk_size=8192):
                        if chunk:
                            f.write(chunk)
                # Verify file was written
                if os.path.exists(filepath) and os.path.getsize(filepath) > 0:
                    return f"Successfully downloaded {filename}"
                else:
                    raise Exception("File was not written correctly")
            else:
                raise requests.exceptions.RequestException(f"HTTP {response.status_code}")
                
        except Exception as e:
            if attempt < max_retries - 1:
                time.sleep(retry_delay * (attempt + 1))  # Exponential backoff
                continue
            return f"Error downloading {filename} after {max_retries} attempts: {str(e)}"
        finally:
            session.close()
    
    return f"Failed to download {filename} after all retries"


# Get download URLs from JSON files
download_urls = []
for json_file in os.listdir(JSON_DIR):
    PREFIX = f"data-{CLASSIFY}-"
    if json_file.startswith(PREFIX):
        with open(os.path.join(JSON_DIR, json_file), 'r') as f:
            data = json.load(f)
            for test_case in data.get('testCases', []):
                download_url = test_case.get('download')
                if download_url:
                    download_urls.append(download_url)



# Reduce number of concurrent downloads to avoid overwhelming the connection
max_workers = 20  # Reduced from 50 to 10 for more stability
batch_size = 10    # Reduced batch size

# Create a partial function with the download directory
download_func = partial(download_file, download_dir=ZIP_DIR)

# Keep track of failed downloads for potential retry
failed_downloads = []

# Use ThreadPoolExecutor for parallel downloads
with ThreadPoolExecutor(max_workers=max_workers) as executor:
    futures = []
    
    # Process in smaller batches
    for i in range(0, len(download_urls), batch_size):
        batch = download_urls[i:i + batch_size]
        batch_futures = [executor.submit(download_func, url) for url in batch]
        futures.extend(batch_futures)
        
        # Small delay between batches
        time.sleep(1)
    
    # Process completed downloads with progress bar
    with tqdm(total=len(download_urls), desc="Retrying failed downloads") as pbar:
        for future in as_completed(futures):
            try:
                result = future.result()
                if "Error" in result or "Failed" in result:
                    print(f"\n{result}")
                    # Add to failed downloads list for potential future retry
                    failed_downloads.append(result.split()[2])  # Extract filename
            except Exception as e:
                print(f"\nUnexpected error: {str(e)}")
            finally:
                pbar.update(1)

# Print summary
print("\nDownload retry completed")
if failed_downloads:
    print(f"Files that still failed to download ({len(failed_downloads)}):")
    for file in failed_downloads:
        print(f"- {file}")
else:
    print("All files were downloaded successfully!")

# %%
"""
Unzip file from "data_{LANGUAGE}/zips to "data_{LANGUAGE}/unzips"
"""

def unzip_file(zip_filename):
    """
    Unzip a single zip file
    
    Args:
        zip_filename (str): Name of the zip file to unzip
    """
    zip_path = os.path.join(ZIP_DIR, zip_filename)
    try:
        with zipfile.ZipFile(zip_path, 'r') as zip_ref:
            # Extract to the destination directory
            zip_ref.extractall(os.path.join(UNZIP_DIR, zip_filename.split(".zip")[0]))
        return f"Successfully unzipped: {zip_filename}"
    except Exception as e:
        return f"Error unzipping {zip_filename}: {str(e)}"

def main():

    # Get list of zip files in the source directory
    zip_files = [f for f in os.listdir(ZIP_DIR) if f.endswith('.zip')]

    print(f"Found {len(zip_files)} zip files to process")

    # Use ThreadPoolExecutor for parallel unzipping
    with ThreadPoolExecutor(max_workers=20) as executor:
        # Submit all tasks
        futures = [executor.submit(unzip_file, zip_file) for zip_file in zip_files]
        
        # Process results with progress bar
        with tqdm(total=len(zip_files), desc="Unzipping files") as pbar:
            for future in as_completed(futures):
                try:
                    result = future.result()
                    if "Error" in result:
                        print(f"\n{result}")
                except Exception as e:
                    print(f"\nUnexpected error: {str(e)}")
                finally:
                    pbar.update(1)

if __name__ == '__main__':
    main()

# %%


# Walk through the unzips directory
for root, dirs, files in os.walk(UNZIP_DIR):
    for dir in dirs:
        if dir.endswith(CLASSIFY):
            for root_sub, _, files in os.walk(os.path.join(root, dir)):
                for file in files:
                    suffix = f".{LANGUAGE}"
                    ## JAVA: suffix = .java
                    ## C: suffix = .c
                    ## C++: suffic = .cpp or .c
                    if file.endswith(suffix) or file.endswith(".c"):
                        source_file_path = os.path.join(root_sub, file)
                        os.makedirs(SRC_DIR +"/" + dir, exist_ok=True)
                        dest_file_path = os.path.join(SRC_DIR, dir, f"{file}")
                        print(dest_file_path)
                        shutil.copy2(source_file_path, dest_file_path)

print("Done")

# %%
""" 
Coping source code 
from data_{LANGUAGE}/<unzip_dir>/<project_name>/src 
to data_{LANGUAGE}/{LANGUAGE}_src/<project_name>
"""
def extract_src_files(unzips_dir, src_dir):
    """
    For each folder in unzips_dir ending with CLASSIFY, parse its manifest.sarif,
    extract the source file paths from the 'results'->'locations'->'artifactLocation'->'uri' fields,
    and copy those files into SRC_DIR/<unzipped_folder>/
    """
    for entry in os.listdir(unzips_dir):
        if entry.endswith(CLASSIFY):
            folder = os.path.join(unzips_dir, entry)
            manifest_path = os.path.join(folder, 'manifest.sarif')
            if not os.path.isfile(manifest_path):
                print(f"Warning: manifest.sarif not found in {mixed_folder}")
                continue
            try:
                with open(manifest_path, 'r', encoding='utf-8') as f:
                    manifest = json.load(f)
            except Exception as e:
                print(f"Error reading {manifest_path}: {e}")
                continue

            # Defensive: SARIF structure
            runs = manifest.get('runs', [])
            for run in runs:
                results = run.get('results', [])
                for result in results:
                    locations = result.get('locations', [])
                    for loc in locations:
                        artifact_loc = loc.get('physicalLocation', {}).get('artifactLocation', {})
                        uri = artifact_loc.get('uri')
                        suffix = f".{LANGUAGE}"
                        ## JAVA: suffix = .java
                        ## C: suffix = .c
                        ## C++: suffic = .cpp or .c
                        if uri and uri.endswith(suffix):
                            src_file_path = os.path.join(folder, uri.replace('/', os.sep))
                            if not os.path.isfile(src_file_path):
                                print(f"Source file not found: {src_file_path}")
                                continue
                            # Create destination directory for this mixed folder
                            dest_dir = os.path.join(src_dir, entry)
                            os.makedirs(dest_dir, exist_ok=True)
                            dest_file_path = os.path.join(dest_dir, os.path.basename(uri))
                            print(f"Copying {src_file_path} -> {dest_file_path}")
                            shutil.copy2(src_file_path, dest_file_path)
    print("Done")
extract_src_files(UNZIP_DIR, SRC_DIR)