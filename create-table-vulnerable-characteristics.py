import os
import json
import re
import pandas as pd
from concurrent.futures import ThreadPoolExecutor, as_completed
from tqdm import tqdm
LANGUAGE = "java"
ROOT_DIR = f"data_{LANGUAGE}"

def find_nodes_by_line(dot_path, target_line):
    nodes = []
    with open(dot_path, 'r', encoding='utf-8') as f:
        for line in f:
            if f'LINE_NUMBER="{target_line}"' in line:
                # Extract node information here
                label_code_match = re.search(r'"(\d+)" \[label="(.*?)".*?CODE="(.*?)"', line)
                if label_code_match:
                    node_id, label, code = label_code_match.groups()
                    method_match = re.search(r'NAME="(.*?)"', line)
                    if method_match:
                        method_name = method_match.group(1).split(".")[-1]
                    else:
                        method_match = re.search(r'METHOD_FULL_NAME="(.*?)"', line) 
                        if method_match:
                            method_name = method_match.group(1).split(".")[-1]
                        else:
                            method_name = None
                    
                    nodes.append({
                        'id': node_id,
                        'label': label,
                        'code': code,
                        'method_name': method_name
                    })
    return nodes

def load_vulnerable_lines(sarif_path):
    with open(sarif_path, 'r', encoding='utf-8') as f:
        sarif = json.load(f)

    vulnerable_lines = set()
    for run in sarif.get('runs', []):
        for result in run.get('results', []):
            for loc in result.get('locations', []):
                region = loc.get('physicalLocation', {}).get('region', {})
                start_line = region.get('startLine')
                end_line = region.get('endLine', start_line)  # if no endLine, only 1 line
                if start_line:
                    for line in range(start_line, end_line + 1):
                        vulnerable_lines.add(line)
    # print(vulnerable_lines)
    # exit()
    return list(vulnerable_lines)

def analyze_project(project_name):
    # print(f"Analyzing {project_name}...")
    dot_path = os.path.join(ROOT_DIR, "cpg-output", project_name, "export.dot")
    sarif_path = os.path.join(ROOT_DIR, "unzips", project_name, "manifest.sarif")

    if not os.path.exists(dot_path) or not os.path.exists(sarif_path):
        print(f"Missing files for {project_name}")
        return []

    vulnerable_lines = load_vulnerable_lines(sarif_path)

    results = []
    for line_num in vulnerable_lines:
        nodes = find_nodes_by_line(dot_path, line_num)
        for node in nodes:
            results.append({
                # "project": project_name,
                "line": line_num,
                "node_label": node['label'],
                "node_code": node['code'],
                "method_name": node['method_name']
            })
    # print(results)
    # exit()
    return results

def main():
    all_nodes = []

    project_names = [p for p in os.listdir(os.path.join(ROOT_DIR, "unzips")) if p.endswith("-bad")]
    # project_names = project_names[:100]  # Test nhỏ nếu muốn

    # Use ThreadPoolExecutor
    with ThreadPoolExecutor(max_workers=10) as executor:
        # Submit all tasks first
        future_to_project = {executor.submit(analyze_project, project_name): project_name 
                           for project_name in project_names}
        
        # Process completed tasks with progress bar
        for future in tqdm(as_completed(future_to_project), total=len(project_names), desc="Processing projects"):
            project_name = future_to_project[future]
            try:
                results = future.result()
                if results:
                    all_nodes.extend(results)
            except Exception as e:
                print(f"Project {project_name} generated an exception: {e}")

    print("DONE analyzing all projects")
    # Stats by label and method
    label_stats = {}
    method_stats = {}
    code_by_label = {}
    code_by_method = {}
    exclude = ["BLOCK", "TYPE_REF", "LITERAL", "<empty>", "RET", "try", "c", "e", "i", "cfg", "Tracer", "class", ]

    for node in all_nodes:
        label = node['node_label']
        method = node['method_name']
        code = node['node_code']
        if code in exclude or label in exclude or method in exclude:
            continue
        if label not in label_stats:
            label_stats[label] = 0
            code_by_label[label] = []
        label_stats[label] += 1
        code_by_label[label].append(code)
        if method is None:
            continue
        if method not in method_stats:
            method_stats[method] = 0
            code_by_method[method] = []
        method_stats[method] += 1
        code_by_method[method].append(code)

    # Create data for DataFrame
    data = []
    
    # Add method stats
    for method, count in method_stats.items():
        data.append({
            'Criterion': 'method',
            'Node Type': method,
            'Count': count,
            'Code Examples': '; '.join(code_by_method[method])
        })
    
    # Add label stats  
    for label, count in label_stats.items():
        data.append({
            'Criterion': 'label',
            'Node Type': label, 
            'Count': count,
            'Code Examples': '; '.join(code_by_label[label])
        })

    df = pd.DataFrame(data)
    df = df[~df['Node Type'].isin(exclude)]
    # Sort by Count in descending order
    df = df.sort_values('Count', ascending=False)
    # Save to CSV
    df.to_csv(f"{ROOT_DIR}/vulnerability_characteristics.csv", index=False)

if __name__ == "__main__":
    main()