import os
import json

# Define paths
base_folder = "data_cpp"
json_folder = os.path.join(base_folder, "json")
zip_folder = os.path.join(base_folder, "zips")
unzips_folder = os.path.join(base_folder, "unzips")

# Ensure folders exist
if not os.path.exists(json_folder) or not os.path.exists(zip_folder):
    print("Required folders do not exist.")
    exit()

# Process JSON files
for json_file in os.listdir(json_folder):
    if json_file.endswith(".json"):
        json_path = os.path.join(json_folder, json_file)
        print(f"Processing {json_file}...")
        
        # Read JSON file
        with open(json_path, "r", encoding="utf-8") as f:
            try:
                data = json.load(f)
                identifiers = []
                
                # Collect all identifiers
                if isinstance(data, dict) and "testCases" in data:
                    for test_case in data["testCases"]:
                        identifier = test_case.get("identifier")
                        if identifier:
                            identifiers.append(identifier)
                
                if not identifiers:
                    print(f"No identifiers found in {json_file}. Skipping.")
                    continue
            except json.JSONDecodeError:
                print(f"Error decoding {json_file}. Skipping.")
                continue
        
        # Extract X from the JSON filename
        try:
            parts = json_file.split("-")
            state = parts[1].split(".")[0]  # Extract X and remove file extension
        except IndexError:
            print(f"Unexpected format in {json_file}. Skipping.")
            continue
        
        # Rename corresponding ZIP files
        for identifier in identifiers:
            # print(f"Processing identifier: {identifier}")
            # print(f"state: {state}")
            old_zip_name = f"{identifier}.zip"
            new_zip_name = f"{identifier}-{state}.zip"
            old_zip_path = os.path.join(zip_folder, old_zip_name)
            new_zip_path = os.path.join(zip_folder, new_zip_name)
            
            if os.path.exists(old_zip_path):
                os.rename(old_zip_path, new_zip_path)
                print(f"Renamed {old_zip_name} to {new_zip_name}.")
            else:
                print(f"ZIP file {old_zip_name} not found. Skipping.")
        # Rename corresponding folders in unzips
        for identifier in identifiers:
            old_folder_name = identifier
            new_folder_name = f"{identifier}-{state}"
            old_folder_path = os.path.join(unzips_folder, old_folder_name)
            new_folder_path = os.path.join(unzips_folder, new_folder_name)
            
            if os.path.exists(old_folder_path):
                os.rename(old_folder_path, new_folder_path)
                print(f"Renamed folder {old_folder_name} to {new_folder_name}.")
            else:
                print(f"Folder {old_folder_name} not found in unzips. Skipping.")