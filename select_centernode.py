# %% [markdown]
# ## Create Table Vulnerablity Characteristics
# (Assuming this part runs correctly or the CSV exists)
# ... (Keep the code from the first part: imports, functions like find_nodes_by_line, load_vulnerable_lines, analyze_project_vuln_chars, create_vuln_char_table) ...

# %% [markdown]
# ## Process Projects for Center Nodes

# %%
import os
import json
import re
import pandas as pd
from concurrent.futures import ThreadPoolExecutor, as_completed
from tqdm import tqdm
import pydot
import networkx as nx
import csv
import matplotlib.pyplot as plt
import numpy as np
from multiprocessing import Pool, Manager, Lock # Keep Lock just in case, though not used now
import time
import traceback # For detailed error logging

# %%

LANGUAGE = "cpp"
ROOT_DIR = f"data_{LANGUAGE}"

# --- Functions from the first part (Needed if generating CSV) ---
# ... (Paste find_nodes_by_line, load_vulnerable_lines, analyze_project_vuln_chars, create_vuln_char_table here if needed) ...
# Example placeholder if you assume the CSV exists:
def create_vuln_char_table(output_csv_path):
    print(f"Function {create_vuln_char_table.__name__} called, assuming CSV exists or generation logic is here.")
    # Add the actual generation logic if needed
    pass


# %%
# Step 1: Load vulnerability characteristics
vuln_char_csv_path = os.path.join(ROOT_DIR, "vuln-char-table-final.csv")
valid_node_types = set() # Initialize

# Ensure the directory exists before checking for the file
os.makedirs(ROOT_DIR, exist_ok=True)

if not os.path.exists(vuln_char_csv_path):
    print(f"'{vuln_char_csv_path}' not found. Generating it first...")
    # Make sure create_vuln_char_table is defined correctly above
    df_char = create_vuln_char_table(vuln_char_csv_path)
    if df_char is None or df_char.empty:
        print(
            "Failed to generate or load vulnerability characteristics table. Exiting."
        )
        exit()
else:
    print(f"Loading vulnerability characteristics from {vuln_char_csv_path}")
    try:
        df_char = pd.read_csv(vuln_char_csv_path)
    except Exception as e:
        print(f"Error reading {vuln_char_csv_path}: {e}. Trying to regenerate.")
        df_char = create_vuln_char_table(vuln_char_csv_path)
        if df_char is None or df_char.empty:
            print("Failed to load or regenerate vulnerability characteristics table. Exiting.")
            exit()

# Extract valid node types (labels and methods) from the table
if df_char is not None and "Node Type" in df_char.columns:
    for item in df_char["Node Type"].dropna().unique():
        item_str = str(item).strip()
        if item_str.startswith("[") and item_str.endswith("]"):
            types = re.findall(r"'(.*?)'|\"(.*?)\"|([^',\s\[\]]+)", item_str)
            extracted = {t for group in types for t in group if t}
            valid_node_types.update(extracted)
        elif item_str:
             valid_node_types.add(item_str)
    print(
        f"Loaded {len(valid_node_types)} unique valid node types (labels/methods)."
    )
else:
    print("Warning: Could not load or parse 'Node Type' column. Proceeding without pre-defined valid node types.")


# %%
# Step 2: Helper functions for DOT parsing and processing
def parse_dot_file(dot_path, valid_types):
    """Parses a DOT file, extracting relevant nodes based on valid_types."""
    nodes = {}
    G = None # Initialize G
    try:
        # Use pydot for robust parsing
        # Add timeout potential here if pydot hangs on specific files (more complex)
        graphs = pydot.graph_from_dot_file(dot_path)
        if not graphs:
            # print(f"Warning: pydot could not parse graph from {dot_path}")
            return {}, None
        dot_graph = graphs[0]

        # Convert to NetworkX
        G = nx.drawing.nx_pydot.from_pydot(dot_graph)

        for node_id_obj in G.nodes():
            node_id = str(node_id_obj) # Ensure string ID
            node_attrs = G.nodes[node_id_obj]
            label = node_attrs.get("label", "").strip('"')
            name = node_attrs.get("NAME", "").strip('"')
            method_full_name = node_attrs.get("METHOD_FULL_NAME", "").strip('"')

            method = name if name else None
            if method is None and method_full_name:
                method = method_full_name.split(".")[-1]

            is_valid_label = label and label in valid_types
            is_valid_method = method and method in valid_types

            node_info = {}
            if is_valid_label:
                node_info['label'] = label
            if is_valid_method:
                node_info['method'] = method

            if node_info:
                 nodes[node_id] = node_info

        return nodes, G

    except FileNotFoundError:
        # print(f"Error: DOT file not found at {dot_path}") # Less verbose
        return {}, None
    except Exception as e:
        # Log more specific parsing errors if they happen often
        # print(f"Error parsing DOT file {dot_path}: {type(e).__name__}")
        # traceback.print_exc() # Uncomment for full traceback during debugging
        return {}, None # Return empty dict and None graph on error


def process_dot_file_logic(dot_path, valid_types):
    """Contains the core logic for finding center nodes from a parsed graph."""
    nodes, G = parse_dot_file(dot_path, valid_types)

    if not nodes or G is None:
        return [], 0

    total_nodes_in_graph = len(G.nodes())
    strong_nodes = {
        nid: info for nid, info in nodes.items() if 'label' in info and 'method' in info
    }
    weak_nodes = {
        nid: info for nid, info in nodes.items() if nid not in strong_nodes
    }

    final_groups = []
    center_to_children = {}
    assigned_weak_nodes = set()

    # Ensure nodes exist in G before accessing neighbors
    valid_strong_node_ids = set(strong_nodes.keys()) & set(map(str, G.nodes()))
    valid_weak_node_ids = set(weak_nodes.keys()) & set(map(str, G.nodes()))

    for center_id in valid_strong_node_ids:
        # NetworkX neighbors are usually strings if nodes were added as strings
        neighbors = set(map(str, G.neighbors(center_id)))
        linked_weak_nodes = neighbors.intersection(valid_weak_node_ids)
        if linked_weak_nodes:
            center_to_children[center_id] = linked_weak_nodes
            assigned_weak_nodes.update(linked_weak_nodes)

    for center_id in valid_strong_node_ids:
        group = [center_id]
        if center_id in center_to_children:
            group.extend(list(center_to_children[center_id]))
        final_groups.append(group)

    remaining_weak_nodes = valid_weak_node_ids - assigned_weak_nodes

    if remaining_weak_nodes:
        # Ensure subgraph nodes are actually in G before creating subgraph
        weak_subG = G.subgraph(remaining_weak_nodes)
        weak_subG_undirected = weak_subG.to_undirected()

        processed_in_components = set()
        for component in nx.connected_components(weak_subG_undirected):
            component_list = list(component)
            if not component_list: continue
            final_groups.append(component_list)
            processed_in_components.update(component_list)

        # Handle any remaining weak nodes that might be isolated *within the weak set*
        # but weren't part of components (should be rare if components cover all)
        # truly_remaining = remaining_weak_nodes - processed_in_components
        # for node in truly_remaining:
        #     final_groups.append([node]) # Treat as own group


    final_centers = []
    for group in final_groups:
        if group:
            final_centers.append(group[0])

    return final_centers, total_nodes_in_graph


# %%
# Step 3: Wrapper function for multiprocessing with robust error handling
RESULTS_JSON_PATH = os.path.join(ROOT_DIR, "center_nodes_result.json")
STATS_CSV_PATH = os.path.join(ROOT_DIR, "center_nodes_stats.csv")

def process_project_wrapper(args):
    """Processes a single project, finds centers, and returns results/stats."""
    project_name, dot_folder_path, valid_types = args
    project_path = os.path.join(dot_folder_path, project_name)
    dot_file_path = os.path.join(project_path, "export.dot")
    # print(f"Worker starting: {project_name}") # Debug print

    project_results_list = []
    project_stats_list = []

    try: # Wrap the entire processing for this project
        if os.path.exists(dot_file_path):
            center_nodes, total_nodes = process_dot_file_logic(
                dot_file_path, valid_types
            )
            # Check if processing returned valid results (not None)
            if center_nodes is not None:
                project_results_list.append(
                    {"project": project_name, "center_nodes": center_nodes}
                )
                project_stats_list.append(
                    {
                        "project": project_name,
                        "num_center_nodes": len(center_nodes),
                        "total_nodes": total_nodes,
                    }
                )
        # else: # Optionally log missing dot files
        #     print(f"Worker info: No export.dot found for project {project_name}")

    except Exception as e:
        # Catch ANY exception during processing
        print(f"!!! Worker ERROR processing project {project_name} !!!")
        print(f"Error Type: {type(e).__name__}")
        print(f"Error Args: {e.args}")
        # Print traceback for detailed debugging if needed
        # traceback.print_exc()
        # Return empty lists to signal failure but not block the pool
        return [], []

    # print(f"Worker finished: {project_name}") # Debug print
    # Return lists (potentially empty if file missing or error occurred)
    return project_results_list, project_stats_list


# %%
# Step 4: Main execution block with multiprocessing and incremental saving

if __name__ == "__main__":
    dot_folder = os.path.join(ROOT_DIR, "cpg-output")
    if not os.path.isdir(dot_folder):
        print(f"Error: CPG output directory not found: {dot_folder}")
        exit()

    projects = [
        p for p in os.listdir(dot_folder) if os.path.isdir(os.path.join(dot_folder, p))
    ]

    if not projects:
        print(f"No projects found in {dot_folder}")
        exit()

    print(f"Found {len(projects)} projects to process.")
    # projects = projects[:15] # Limit for testing if needed
    # print(f"Processing first {len(projects)} projects...")


    process_args = [(p, dot_folder, valid_node_types) for p in projects]

    # --- Initialize Results Storage ---
    all_project_results = {}
    if os.path.exists(RESULTS_JSON_PATH):
        try:
            with open(RESULTS_JSON_PATH, "r", encoding="utf-8") as f:
                all_project_results = json.load(f)
            print(f"Loaded {len(all_project_results)} existing results from {RESULTS_JSON_PATH}")
        except json.JSONDecodeError:
            print(f"Warning: Could not parse existing JSON file {RESULTS_JSON_PATH}. Starting fresh.")
        except Exception as e:
            print(f"Error loading existing JSON {RESULTS_JSON_PATH}: {e}. Starting fresh.")

    # --- Prepare CSV File ---
    # Check if header needs to be written (thread-safe check before pool starts)
    write_csv_header = not os.path.exists(STATS_CSV_PATH) or os.path.getsize(STATS_CSV_PATH) == 0
    if not write_csv_header:
        print(f"Appending stats to existing file: {STATS_CSV_PATH}")
    else:
        print(f"Creating new stats file: {STATS_CSV_PATH}")


    # --- Multiprocessing Pool ---
    num_workers = max(1, os.cpu_count() - 1) if os.cpu_count() else 4
    print(f"Starting processing with {num_workers} workers...")

    # Use a context manager for the pool
    with Pool(processes=num_workers) as pool:
        # Use imap_unordered for potentially better throughput if tasks vary in time
        results_iterator = pool.imap_unordered(process_project_wrapper, process_args)

        # Wrap with tqdm for progress
        for project_results, project_stats in tqdm(
            results_iterator, total=len(projects), desc="Processing projects"
        ):
            # --- Process results from a completed worker ---

            # Check if the worker returned valid data (not empty lists due to error)
            if project_stats and project_results:
                current_project_name = project_stats[0]['project'] # Get project name
                # print(f"Main: Received results for {current_project_name}") # Debug print

                # --- Save Stats Incrementally (CSV) ---
                try:
                    with open(STATS_CSV_PATH, "a", newline="", encoding="utf-8") as csvfile:
                        fieldnames = ["project", "num_center_nodes", "total_nodes"]
                        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
                        # Simple lock for header writing (less critical for append)
                        # but ensures header is written only once if multiple workers finish fast initially
                        if write_csv_header:
                             # Check again inside lock just in case
                             if not os.path.exists(STATS_CSV_PATH) or os.path.getsize(STATS_CSV_PATH) == 0:
                                writer.writeheader()
                             write_csv_header = False # Set flag to false after first write attempt

                        writer.writerows(project_stats)
                except IOError as e:
                    print(f"Error writing stats to CSV for {current_project_name}: {e}")
                except Exception as e:
                    print(f"Unexpected error writing CSV for {current_project_name}: {e}")

                # --- Update and Save Full Results Incrementally (JSON) ---
                try:
                    # Update the main dictionary
                    for result in project_results:
                        project_name = result['project']
                        # Overwrite or append based on desired behavior if project processed multiple times
                        all_project_results[project_name] = result['center_nodes']

                    # Save the *entire updated* dictionary to JSON
                    with open(RESULTS_JSON_PATH, "w", encoding="utf-8") as f:
                        json.dump(all_project_results, f, indent=2)
                except IOError as e:
                    print(f"Error writing results to JSON for {current_project_name}: {e}")
                except Exception as e:
                    print(f"Unexpected error writing JSON for {current_project_name}: {e}")

            # else: # Handle cases where worker returned empty lists (error occurred)
            #     # Optionally log that a project failed if needed,
            #     # but the error should have been printed by the worker.
            #     # print("Main: Received empty result, likely an error in worker.")
            #     pass


    print(f"✅ Processing complete.")
    print(f"Stats saved incrementally to: {STATS_CSV_PATH}")
    print(f"Full results saved to: {RESULTS_JSON_PATH}")