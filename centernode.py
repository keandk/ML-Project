# %% [markdown]
# ## Create Table Vulnerablity Characteristics

# %%
import os
import json
import re
import pandas as pd
from concurrent.futures import ThreadPoolExecutor, as_completed
from tqdm import tqdm

# %%

LANGUAGE = "java"
ROOT_DIR = f"data_{LANGUAGE}"

# %%
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


# %%
def create_table():
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
    exclude = ["BLOCK", "TYPE_REF", "LITERAL", "<empty>", "RET", "try", "c", "e", "i", "cfg", "Tracer", "class", "reader", "line", "ioe", "ie"]
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

import pydot
import networkx as nx
import csv

# %%
# Step 1: Load vulnerability characteristics
df_char = pd.read_csv("vuln-char-table-final.csv")
valid_node_types = set()
for item in df_char['Node Type'].dropna().unique():
    # Assume item is a string like "['TYPE1', 'TYPE2']" or "['TYPE1']"
    try:
        # Use regex to find all quoted strings within the brackets
        types = re.findall(r"'(.*?)'", item)
        # Handle cases like ['TYPE'] without quotes or just TYPE
        if not types:
             # Try removing brackets and splitting if it looks like a list
             if isinstance(item, str) and item.startswith('[') and item.endswith(']'):
                 cleaned_item = item.strip('[]')
                 # Split by comma, strip whitespace from each part
                 types = [t.strip() for t in cleaned_item.split(',') if t.strip()]
             elif isinstance(item, str): # Assume it's a single type if not bracketed
                 types = [item.strip()]

        if types:
           valid_node_types.update(types)
        elif isinstance(item, str): # Fallback if regex and list parsing fail
            single_type = item.strip()
            if single_type: valid_node_types.add(single_type)

    except Exception as e:
        print(f"Warning: Could not parse node type string: {item} - {e}")
        # Optional: Add raw string on failure?
        # if isinstance(item, str): valid_node_types.add(item.strip())

# print(valid_node_types) # Print the full set if needed for debugging
# Print a sample to confirm parsing looks reasonable
print(f"Sample parsed valid_node_types: {set(list(valid_node_types)[:20]) if valid_node_types else 'Set is empty'}")

# %%
# Step 2: Helper functions
def parse_dot_file(dot_path):
    nodes = {}
    with open(dot_path, 'r', encoding='utf-8') as f:
        for line in f:
            if 'LABEL=' in line or 'NAME=' in line or 'METHOD_FULL_NAME=' in line:
                node_match = re.search(r'"(\d+)" \[', line)
                # print(node_match)
                if not node_match:
                    continue
                node_id = node_match.group(1)
                label_match = re.search(r'label="(.*?)"', line)
                name_match = re.search(r'NAME="(.*?)"', line)
                method_full_name_match = re.search(r'METHOD_FULL_NAME="(.*?)"', line)
                # nếu có label
                label = label_match.group(1) if label_match else ''
                # nếu co NAME
                method = name_match.group(1) if name_match else None
                # không có NAME thì lấy METHOD_FULL_NAME
                if method is None and method_full_name_match:
                    method = method_full_name_match.group(1).split('.')[-1]
                # print(f"Node ID: {node_id}, Label: {label}, Method: {method}")
                # nếu label nằm trong valid_node_types thì thêm vào dict
                if label in valid_node_types:
                    # nếu có method và method nằm trong valid_node_types
                    if (method and method in valid_node_types):
                        nodes[node_id] = {
                            'label': label,
                            'method': method
                        }
                    # elif method is None:
                    else:
                        nodes[node_id] = {
                            'label': label
                        }
                        
    dot_graph = pydot.graph_from_dot_file(dot_path)[0]
    G = nx.drawing.nx_pydot.from_pydot(dot_graph)
    # print(G)
    return nodes, G



# %%
import matplotlib.pyplot as plt
import networkx as nx
import numpy as np

# %%
def process_project(dot_path):
    nodes, G = parse_dot_file(dot_path) # G contains all nodes and edges

    # Chia thành strong và weak nodes
    strong_nodes = {nid: info for nid, info in nodes.items() if len(info) == 2}
    weak_nodes = {nid: info for nid, info in nodes.items() if len(info) < 2}
    print(f"Number of strong nodes: {len(strong_nodes)}")
    print(f"Number of weak nodes: {len(weak_nodes)}")

    final_groups = [] # Store groups (lists of nodes) instead of just centers

    # --- Handle STRONG nodes ---
    # Mỗi strong node tìm weak node nối trực tiếp
    center_to_children = {}
    for center_id in strong_nodes.keys():
        neighbors = set(G.neighbors(center_id))
        linked_weak_nodes = neighbors.intersection(weak_nodes.keys())
        if linked_weak_nodes:
            center_to_children[center_id] = linked_weak_nodes

    assigned_weak_nodes = set()
    for linked in center_to_children.values():
        assigned_weak_nodes.update(linked)

    # Add strong centers and their children
    for center_id in strong_nodes.keys():
        if center_id in center_to_children:
            group = [center_id] + list(center_to_children[center_id])
            final_groups.append(group)
        else:
            final_groups.append([center_id])

    # --- Handle WEAK nodes ---
    # We need the subgraph of weak nodes to find connections among them
    # Ensure weak_nodes keys are actually in G before creating subgraph
    valid_weak_nodes = set(weak_nodes.keys()) & set(G.nodes())
    weak_subG_undirected = None
    if not valid_weak_nodes:
        print("No valid weak nodes found in the graph.")
        remaining_weak_nodes = set()
    else:
        weak_subG = G.subgraph(valid_weak_nodes)
        weak_subG_undirected = weak_subG.to_undirected() # Use undirected for neighbor finding
        remaining_weak_nodes = set(weak_subG_undirected.nodes()) - assigned_weak_nodes

    weak_groups_count = 0
    while remaining_weak_nodes:
        # Đếm số neighbor trong remaining_weak_nodes for each node
        neighbor_counts = {}
        for node in remaining_weak_nodes:
            # Neighbors within the weak subgraph AND still remaining
            # Ensure neighbor calculation handles potential disconnections if weak_subG_undirected is empty/invalid
            if weak_subG_undirected and node in weak_subG_undirected:
                neighbors = set(weak_subG_undirected.neighbors(node)) & remaining_weak_nodes
                neighbor_counts[node] = len(neighbors)
            else: # Node might be isolated or graph might be empty
                neighbor_counts[node] = 0

        # Identify isolated nodes within the remaining set
        isolated_nodes = {node for node in remaining_weak_nodes if neighbor_counts.get(node, 0) == 0}
        non_isolated_nodes = remaining_weak_nodes - isolated_nodes

        # Process isolated nodes first
        for node in isolated_nodes:
             final_groups.append([node]) # Each isolated node is its own group
             weak_groups_count += 1

        remaining_weak_nodes -= isolated_nodes # Remove processed isolated nodes

        if not remaining_weak_nodes: # Exit if only isolated nodes were left or the set is now empty
            break

        # Chọn node nhiều neighbor nhất làm center (within the remaining non-isolated weak nodes)
        center_node = max(non_isolated_nodes, key=lambda x: neighbor_counts.get(x, -1)) # Use get with default

        # This center's group includes itself and its immediate neighbors within the remaining weak nodes
        # Need to ensure weak_subG_undirected is valid before calling neighbors
        if weak_subG_undirected and center_node in weak_subG_undirected:
             neighbors = set(weak_subG_undirected.neighbors(center_node)) & remaining_weak_nodes # Check against remaining
             group = set([center_node]) | neighbors
        else: # Should not happen if non_isolated_nodes is correct, but safety check
             group = set([center_node])

        final_groups.append(list(group)) # Add the identified group
        weak_groups_count += 1

        # Xoá the entire group (center and its neighbors) ra khỏi remaining_nodes
        remaining_weak_nodes -= group

    final_centers = []
    for group in final_groups:
        center = group[0]
        final_centers.append(center)
    # Return the list of groups and the full graph G for visualization
    return final_centers, len(G.nodes())

# Process all projects and collect results using multiprocessing
from multiprocessing import Pool, Manager

def process_project_wrapper(project):
    project_path = os.path.join(dot_folder, project)
    project_results = []
    project_stats = []
    
    for file in os.listdir(project_path):
        if file.endswith("export.dot"):
            dot_path = os.path.join(project_path, file)
            
            try:
                center_nodes, total_nodes = process_project(dot_path)
                project_results.append({
                    'project': project,
                    'center_nodes': center_nodes
                })
                project_stats.append({
                    'project': project,
                    'num_center_nodes': len(center_nodes),
                    'total_nodes': total_nodes
                })
            except Exception as e:
                print(f"Error processing {dot_path}: {e}")
                continue
                
    return project_results, project_stats

dot_folder = ROOT_DIR + "/cpg-output"
projects = [p for p in os.listdir(dot_folder)][:3]

# Use multiprocessing to process projects in parallel
with Pool() as pool:
    all_results = list(tqdm(pool.imap(process_project_wrapper, projects), 
                          total=len(projects),
                          desc="Processing projects"))

# Combine results from all processes
results = {}
stats = []
for project_results, project_stats in all_results:
    for result in project_results:
        project = result['project']
        if project not in results:
            results[project] = []
        results[project].extend(result['center_nodes'])
    stats.extend(project_stats)

# Save stats to CSV
csv_path = ROOT_DIR + "/center_nodes_stats.csv"
with open(csv_path, "w", newline='', encoding="utf-8") as csvfile:
    writer = csv.DictWriter(csvfile, fieldnames=["project", "num_center_nodes", "total_nodes"])
    writer.writeheader()
    for row in stats:
        writer.writerow(row)

# Save full results to JSON  
with open(ROOT_DIR + "/center_nodes_result.json", "w", encoding="utf-8") as f:
    json.dump(results, f, indent=2)

print("✅ Done! Saved to center_nodes_result.json")