# %% [markdown]
# # **Subgraph extraction and Tokenization (Multiprocessing)**
#
# This script performs two main tasks:
# 1. Extracts 1-hop neighbor subgraphs for specified center nodes from CPG `.dot` files.
# 2. Extracts and tokenizes code fragments from the generated subgraph context files.
#
# Both tasks are parallelized using multiprocessing for speed.
# Logs are written to `processing.log`. Only progress bars are shown on stdout.

# %%
import json
import os
import glob
import logging
import pydot # Import pydot
import re # Import regex for label parsing
from pydot import Error as PydotError
from tqdm import tqdm # Use tqdm directly for progress bars
import multiprocessing
from functools import partial # To pass fixed arguments to worker functions

# --- Import the token extraction function ---
# Make sure extractToken.py is in the same directory or Python path
try:
    from extractToken import extract_and_replace_tokens
except ImportError:
    logging.error("Error: Could not import 'extract_and_replace_tokens' from 'extractToken.py'.")
    # Define a dummy function to allow the script to run partially, or raise error
    def extract_and_replace_tokens(code):
        logging.warning("Using dummy token extractor.")
        return f"TOKENIZED({code})"
    # Or uncomment the next line to stop execution if the import fails
    # raise

# --- Configure Logging ---
log_file = "processing.log"
# Remove existing handlers if any exist from previous runs in interactive envs
for handler in logging.root.handlers[:]:
    logging.root.removeHandler(handler)
# Configure logging to file
logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(processName)s - %(levelname)s - %(message)s',
    filename=log_file,
    filemode='w' # Overwrite log file each run
)
logging.info("Logging configured. Output directed to %s", log_file)
# Prevent tqdm from interfering with logging to file if it uses logging internally
# (Though it usually writes to stderr directly)
logging.getLogger('tqdm').propagate = False


# --- Define Paths ---
base_cpg_path = "data_java/cpg-output"
json_path = "data_java/center_nodes_result_specific.json"
output_base_path = "data_java/subgraph_contexts"
tokenized_output_path = "data_java/tokenized_contexts"

# --- Define Constants ---
allowed_neighbor_labels = {
    'arrayInitializer', 'CatchClause', 'stonesoup_array', 'assignment',
    'fieldAccess', 'addition', 'CONTROL_STRUCTURE', 'FIELD_IDENTIFIER',
    'cast', 'IDENTIFIER', 'indexAccess', 'logicalAnd', 'CALL',
    'logicalNot', 'alloc'
}
# Regex to extract the first word from the label attribute
label_type_pattern = re.compile(r'^"?([a-zA-Z_<>]+)')

# --- Helper function to get node type from label attribute ---
def get_node_type_from_attributes(attrs):
    label_str = attrs.get('label')
    if label_str:
        match = label_type_pattern.match(label_str)
        if match:
            return match.group(1)
    return None

# --- Worker Function for Subgraph Extraction ---
def process_folder_for_subgraphs(folder_info, base_cpg_path, output_base_path, allowed_neighbor_labels, label_type_pattern):
    """Processes a single folder to extract subgraphs."""
    folder_name, center_node_ids_str = folder_info
    center_node_ids = set(center_node_ids_str)
    folder_path = os.path.join(base_cpg_path, folder_name)
    output_file_path = os.path.join(output_base_path, f"{folder_name}_context.txt")

    logging.info(f"Processing folder: {folder_name}")

    dot_files = glob.glob(os.path.join(folder_path, '*.dot'))

    if not dot_files:
        logging.warning(f"  No .dot file found in {folder_path}. Skipping.")
        return False
    if len(dot_files) > 1:
        logging.warning(f"  Multiple .dot files found in {folder_path}. Using the first one: {dot_files[0]}.")

    dot_file_path = dot_files[0]
    logging.info(f"  Using .dot file: {dot_file_path}")

    all_subgraphs = {}

    try:
        logging.info(f"  Parsing {dot_file_path} with pydot...")
        graphs = pydot.graph_from_dot_file(dot_file_path)

        if not graphs:
            logging.warning(f"  pydot could not parse any graph from {dot_file_path}. Skipping.")
            return False

        # Handle potential list of graphs or single graph object
        if isinstance(graphs, list):
            if not graphs:
                 logging.warning(f"  pydot returned an empty list for {dot_file_path}. Skipping.")
                 return False
            graph = graphs[0]
        elif isinstance(graphs, (pydot.Graph, pydot.Dot)):
             graph = graphs
        else:
            logging.error(f"  pydot.graph_from_dot_file did not return a valid graph object for {dot_file_path}. Type: {type(graphs)}. Skipping.")
            return False

        logging.info(f"  Successfully parsed graph for {folder_name}.")

        logging.info(f"  Building node map for {folder_name}...")
        node_map = {}
        for node in graph.get_nodes():
            unquoted_id = node.get_name().strip('"')
            node_map[unquoted_id] = node
        logging.info(f"  Built map with {len(node_map)} nodes for {folder_name}.")

        edge_map = {}
        for edge in graph.get_edges():
            source_id = edge.get_source().strip('"')
            dest_id = edge.get_destination().strip('"')
            edge_key = (source_id, dest_id)
            edge_map[edge_key] = edge

        for center_node_id in center_node_ids:
            # Ensure center node exists in the graph
            if center_node_id not in node_map:
                logging.warning(f"  Center node ID {center_node_id} not found in the node map for {folder_name}. Skipping this center node.")
                continue

            logging.debug(f"  Building subgraph for center node: {center_node_id} in {folder_name}")
            subgraph_lines = set()
            subgraph_nodes = {center_node_id}
            neighbors_added = set()

            for (source_id, dest_id), edge_obj in edge_map.items():
                is_relevant_edge = False
                neighbor_to_add = None
                potential_neighbor_node = None

                if source_id == center_node_id:
                    potential_neighbor_node = node_map.get(dest_id)
                    if potential_neighbor_node:
                        node_type = get_node_type_from_attributes(potential_neighbor_node.get_attributes())
                        if node_type in allowed_neighbor_labels:
                            neighbor_to_add = dest_id
                            is_relevant_edge = True
                elif dest_id == center_node_id:
                    potential_neighbor_node = node_map.get(source_id)
                    if potential_neighbor_node:
                        node_type = get_node_type_from_attributes(potential_neighbor_node.get_attributes())
                        if node_type in allowed_neighbor_labels:
                            neighbor_to_add = source_id
                            is_relevant_edge = True

                if is_relevant_edge and neighbor_to_add:
                    subgraph_nodes.add(neighbor_to_add)
                    neighbors_added.add(neighbor_to_add)
                    subgraph_lines.add(edge_obj.to_string().strip())

            # Add node definitions for all nodes in the subgraph
            for node_id in subgraph_nodes:
                node_obj = node_map.get(node_id)
                if node_obj:
                    subgraph_lines.add(node_obj.to_string().strip())
                else:
                     logging.warning(f"  Node ID {node_id} (part of subgraph for center {center_node_id}) not found in node map for {folder_name}.")


            if subgraph_nodes: # Only store if we have nodes
                all_subgraphs[center_node_id] = {
                    "nodes": list(subgraph_nodes),
                    "neighbors": list(neighbors_added),
                    "lines": list(subgraph_lines)
                }
                logging.debug(f"  Added {len(neighbors_added)} neighbors for center node {center_node_id} in {folder_name}.")
            else:
                 logging.warning(f"  No nodes found for subgraph centered at {center_node_id} in {folder_name}.")


        if not all_subgraphs:
             logging.warning(f"  No subgraphs generated for any center node in {folder_name}.")
             # Optionally write an empty file or skip writing
             # For consistency, let's write a file indicating no subgraphs
             with open(output_file_path, 'w', encoding='utf-8') as f_out:
                 f_out.write(f"# Subgraphs for folder: {folder_name}\n")
                 f_out.write(f"# Total center nodes processed: {len(center_node_ids)}\n")
                 f_out.write(f"# No valid subgraphs generated.\n\n")
             logging.info(f"  Wrote empty context file to {output_file_path}")
             return True # Indicate processing happened, even if no subgraphs

        # Write all subgraphs to a single file
        with open(output_file_path, 'w', encoding='utf-8') as f_out:
            f_out.write(f"# Subgraphs for folder: {folder_name}\n")
            f_out.write(f"# Total center nodes with subgraphs: {len(all_subgraphs)}\n\n")

            for center_id, subgraph_data in all_subgraphs.items():
                f_out.write(f"START_SUBGRAPH center_node={center_id}\n")
                f_out.write(f"# Node count: {len(subgraph_data['nodes'])}\n")
                f_out.write(f"# Neighbor count: {len(subgraph_data['neighbors'])}\n")

                for line in sorted(subgraph_data['lines']):
                    f_out.write(f"  {line}\n")

                f_out.write(f"END_SUBGRAPH center_node={center_id}\n\n")

        logging.info(f"  Successfully wrote {len(all_subgraphs)} subgraphs to {output_file_path}")
        return True

    except FileNotFoundError:
        logging.error(f"  Error: .dot file not found during processing for {folder_name} at {dot_file_path}")
        return False
    except PydotError as e:
        logging.error(f"  A pydot library error occurred processing {dot_file_path} for {folder_name}: {e}")
        return False
    except Exception as e:
        logging.error(f"  An unexpected {type(e).__name__} occurred while processing {folder_name} ({dot_file_path}): {e}", exc_info=False) # Set exc_info=True for full traceback in log
        return False


# --- Worker Function for Tokenization ---
def process_context_file(context_file_path, tokenized_output_path):
    """Processes a single context file to tokenize code fragments."""
    base_name = os.path.basename(context_file_path).replace('_context.txt', '')
    output_file_path = os.path.join(tokenized_output_path, f"{base_name}_tokenized.txt")
    logging.info(f"Tokenizing context file: {base_name}")

    tokenized_subgraphs = {}
    processed_center_nodes = 0
    total_fragments_processed = 0

    try:
        with open(context_file_path, 'r', encoding='utf-8') as f_in:
            current_center_node = None
            in_subgraph = False
            code_fragments = {} # Store fragments per center node

            for line in f_in:
                line = line.strip()

                center_match = re.match(r'START_SUBGRAPH\s+center_node="?(\w+)"?', line) # Handle optional quotes
                if center_match:
                    current_center_node = center_match.group(1)
                    in_subgraph = True
                    code_fragments[current_center_node] = []
                    processed_center_nodes += 1
                    continue

                if line.startswith('END_SUBGRAPH'):
                    in_subgraph = False
                    current_center_node = None # Reset for safety
                    continue

                if not line or line.startswith('#') or not in_subgraph or not current_center_node:
                    continue

                # Extract node type and CODE attribute
                node_type = None
                code_fragment = None

                # More robust extraction allowing for various attribute orders
                attrs_match = re.findall(r'(\w+)="([^"]*)"', line)
                attrs = dict(attrs_match)

                label_content = attrs.get('label')
                if label_content:
                    # Extract the type (first part before comma or space, or full label)
                    node_type = re.split(r'[,\s]', label_content, 1)[0]

                code_fragment = attrs.get('CODE')

                if code_fragment is not None and node_type:
                    # Store original code fragment with its type
                    code_fragments[current_center_node].append({
                        'code': code_fragment,
                        'type': node_type
                    })
                    total_fragments_processed += 1

        # Now tokenize each code fragment
        for center_node, fragments in code_fragments.items():
            tokenized_fragments = []
            for fragment_data in fragments:
                try:
                    # Ensure the imported function is called
                    tokenized = extract_and_replace_tokens(fragment_data['code'])
                    tokenized_fragments.append({
                        'original': fragment_data['code'],
                        'tokenized': tokenized,
                        'type': fragment_data['type']
                    })
                except Exception as e:
                    logging.warning(f"Error tokenizing fragment in {base_name}, center {center_node}, code '{fragment_data['code']}': {e}")
                    tokenized_fragments.append({
                        'original': fragment_data['code'],
                        'tokenized': fragment_data['code'], # Keep original on error
                        'type': fragment_data['type'],
                        'error': str(e)
                    })

            tokenized_subgraphs[center_node] = tokenized_fragments

        # Write tokenized output to its own file
        with open(output_file_path, 'w', encoding='utf-8') as f_out:
            f_out.write(f"# Tokenized code fragments for {base_name}\n")
            f_out.write(f"# Total center nodes processed: {processed_center_nodes}\n")
            f_out.write(f"# Total code fragments found: {total_fragments_processed}\n\n")

            for center_node, fragments in tokenized_subgraphs.items():
                f_out.write(f"CENTER_NODE: {center_node}\n")
                f_out.write(f"FRAGMENT_COUNT: {len(fragments)}\n")

                for i, fragment in enumerate(fragments):
                    f_out.write(f"  ORIGINAL[{i}]: {fragment['original']}\n")
                    f_out.write(f"  TYPE[{i}]: {fragment['type']}\n")
                    f_out.write(f"  TOKENIZED[{i}]: {fragment['tokenized']}\n")
                    if 'error' in fragment:
                        f_out.write(f"  ERROR[{i}]: {fragment['error']}\n")

                f_out.write("\n")

        logging.info(f"Finished tokenizing {base_name}. Processed {processed_center_nodes} center nodes with {total_fragments_processed} fragments. Output: {output_file_path}")
        # Return base_name and the tokenized data for aggregation
        return base_name, tokenized_subgraphs

    except FileNotFoundError:
        logging.error(f"Context file not found: {context_file_path}")
        return base_name, None # Indicate error for this file
    except Exception as e:
        logging.error(f"An unexpected error occurred processing context file {context_file_path}: {e}", exc_info=False)
        return base_name, None # Indicate error for this file


# --- Main Execution ---
if __name__ == "__main__":
    multiprocessing.freeze_support() # For Windows compatibility

    # --- Stage 1: Subgraph Extraction ---
    logging.info("--- Starting Subgraph Extraction Stage ---")

    # Load the center nodes data from JSON
    try:
        with open(json_path, 'r') as f:
            center_nodes_data = json.load(f)
        logging.info(f"Successfully loaded center nodes data from {json_path}")
    except FileNotFoundError:
        logging.error(f"FATAL: JSON file not found at {json_path}. Exiting.")
        exit(1)
    except json.JSONDecodeError:
        logging.error(f"FATAL: Could not decode JSON from {json_path}. Exiting.")
        exit(1)

    # Create the output directory
    os.makedirs(output_base_path, exist_ok=True)
    logging.info(f"Ensured subgraph output directory exists: {output_base_path}")

    # Prepare data for multiprocessing
    folder_items = list(center_nodes_data.items())
    num_folders = len(folder_items)
    logging.info(f"Found {num_folders} folders to process for subgraph extraction.")

    if num_folders > 0:
        # Use context manager for the pool
        num_processes = min(multiprocessing.cpu_count(), num_folders) # Don't use more processes than tasks
        logging.info(f"Starting subgraph extraction pool with {num_processes} processes.")
        
        # Use partial to fix the arguments that are the same for all calls
        worker_func = partial(
            process_folder_for_subgraphs,
            base_cpg_path=base_cpg_path,
            output_base_path=output_base_path,
            allowed_neighbor_labels=allowed_neighbor_labels,
            label_type_pattern=label_type_pattern
        )

        results = []
        with multiprocessing.Pool(processes=num_processes) as pool:
            # Use tqdm for progress bar on the console
            with tqdm(total=num_folders, desc="Extracting Subgraphs") as pbar:
                for result in pool.imap_unordered(worker_func, folder_items):
                    # imap_unordered yields results as they complete
                    results.append(result)
                    pbar.update(1) # Update progress bar for each completed task

        successful_extractions = sum(1 for r in results if r)
        logging.info(f"Subgraph extraction pool finished. Successfully processed {successful_extractions}/{num_folders} folders.")
    else:
        logging.info("No folders found in JSON data to process for subgraphs.")

    logging.info("--- Finished Subgraph Extraction Stage ---")


    # --- Stage 2: Tokenization ---
    logging.info("--- Starting Tokenization Stage ---")

    # Create the output directory
    os.makedirs(tokenized_output_path, exist_ok=True)
    logging.info(f"Ensured tokenized output directory exists: {tokenized_output_path}")

    # Find all context files generated in Stage 1
    context_files = glob.glob(os.path.join(output_base_path, '*_context.txt'))
    num_context_files = len(context_files)

    if not context_files:
        logging.warning(f"No context files found in {output_base_path}. Nothing to tokenize.")
    else:
        logging.info(f"Found {num_context_files} context files to process for tokenization.")

        all_tokenized_data = {}
        num_processes = min(multiprocessing.cpu_count(), num_context_files)
        logging.info(f"Starting tokenization pool with {num_processes} processes.")

        # Use partial for fixed arguments
        tokenization_worker = partial(
            process_context_file,
            tokenized_output_path=tokenized_output_path
        )

        results = []
        with multiprocessing.Pool(processes=num_processes) as pool:
            with tqdm(total=num_context_files, desc="Tokenizing Contexts") as pbar:
                 for result in pool.imap_unordered(tokenization_worker, context_files):
                    # result is (base_name, tokenized_subgraphs) or (base_name, None) on error
                    if result and result[1] is not None:
                        base_name, tokenized_data = result
                        all_tokenized_data[base_name] = tokenized_data
                    elif result:
                         logging.warning(f"Tokenization failed or produced no data for base name: {result[0]}")
                    else:
                         logging.error("A tokenization worker returned an unexpected None result.")
                    results.append(result) # Keep track of completion
                    pbar.update(1)

        successful_tokenizations = len(all_tokenized_data)
        logging.info(f"Tokenization pool finished. Successfully processed {successful_tokenizations}/{num_context_files} context files.")

        # Save consolidated JSON for all tokenized data
        if all_tokenized_data:
            output_json_path = os.path.join(tokenized_output_path, "all_tokenized_fragments.json")
            try:
                with open(output_json_path, 'w', encoding='utf-8') as f_json:
                    json.dump(all_tokenized_data, f_json, indent=2)
                logging.info(f"Saved consolidated tokenized data for {len(all_tokenized_data)} base names to {output_json_path}")
            except Exception as e:
                logging.error(f"Failed to save consolidated JSON: {e}")
        else:
            logging.warning("No tokenized data was successfully generated to consolidate into JSON.")

    logging.info(f"--- Tokenization process complete. Results saved to {tokenized_output_path} ---")
    logging.info(f"Check the log file '{log_file}' for detailed processing information.")