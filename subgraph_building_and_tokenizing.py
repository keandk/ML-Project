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
base_cpg_path = "data_c/cpg-output"
json_path = "data_c/center_nodes_result.json"
output_base_path = "data_c/subgraph_contexts"
tokenized_output_path = "data_c/tokenized_contexts"

"""--- Define Constants --- """
## for java
# allowed_neighbor_labels = {
#     'arrayInitializer', 'CatchClause', 'stonesoup_array', 'assignment',
#     'fieldAccess', 'addition', 'CONTROL_STRUCTURE', 'FIELD_IDENTIFIER',
#     'cast', 'IDENTIFIER', 'indexAccess', 'logicalAnd', 'CALL',
#     'logicalNot', 'alloc'
# }
## for cpp
allowed_neighbor_labels = {
    'CALL',
    'IDENTIFIER', 
    'free',
    'malloc',
    'indirectIndexAccess',
    'FIELD_IDENTIFIER',
    'fieldAccess',
    'indirectFieldAccess',
    'assignment',
    'multiplication',
    'cast',
    'CONTROL_STRUCTURE'
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
    folder_name, center_node_ids_str = folder_info  # get folder name, list of center nodes's id
    center_node_ids = set(center_node_ids_str) # convert to set()
    folder_path = os.path.join(base_cpg_path, folder_name) # eg: data_java/cpg-output/1458-v1.0.0-good
    output_file_path = os.path.join(output_base_path, f"{folder_name}_context.txt") # eg: data_java/subgraph_contexts/1458-v1.0.0-good_context.txt

    logging.info(f"Processing folder: {folder_name}")

    dot_files = glob.glob(os.path.join(folder_path, '*.dot')) # eg: data_java/cpg-output/1458-v1.0.0-good/*.dot

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

def process_context_file(context_file_path, tokenized_output_path):
  """
  Processes a single context file to
   1) extract code fragments,
   2) ensure the center node is the first fragment,
   3) assign global node_index (centers first),
   4) compute edge_type_index (AST→2, CFG→1, else→0), (following Definition 10 in paper) 
   5) tokenize via extract_and_replace_tokens(),
   6) write a human‐readable _tokenized.txt,
   7) return (base_name, tokenized_subgraphs) for JSON dumping.
  """
  base_name = os.path.basename(context_file_path) \
                 .replace('_context.txt','')
  output_file_path = os.path.join(
    tokenized_output_path,
    f"{base_name}_tokenized.txt"
  )
  logging.info(f"Tokenizing context file: {base_name}")

  # --- 1) Read and collect fragments & edges ---
  code_fragments = {}  # center_id -> [ {node_id, type, code}, … ]
  edge_info      = {}  # center_id -> { neighbor_id -> [edge_labels…] }
  current_center = None
  in_subgraph    = False

  try:
    with open(context_file_path, 'r', encoding='utf-8') as f:
      for line in f:
        line = line.strip()
        # Detect start
        m_start = re.match(
          r'^START_SUBGRAPH\s+center_node="?(\w+)"?', line
        )
        if m_start:
          current_center = m_start.group(1)
          in_subgraph    = True
          code_fragments[current_center] = []
          edge_info[current_center]      = {}
          continue

        # Detect end
        if line.startswith('END_SUBGRAPH'):
          in_subgraph    = False
          current_center = None
          continue

        if not in_subgraph or line.startswith('#') or not current_center:
          continue

        # 1a) Edge line?
        if '->' in line:
          m_edge = re.match(r'"?(\w+)"?\s*->\s*"?(\w+)"?', line)
          if m_edge:
            src, dst = m_edge.groups()
            nbr = src if dst == current_center else dst if src == current_center else None
            if nbr:
              labels = re.findall(r'label="([^"]+)"', line)
              edge_info[current_center] \
                .setdefault(nbr, []) \
                .extend(labels)
          continue

        # 1b) Node-def line?
        if '[' in line and ']' in line:
          # Split into node ID and attributes parts
          node_id_part, attr_part = line.split('[', 1)
          attr_part = attr_part.rsplit(']', 1)[0]
          
          # Extract node ID
          node_id = node_id_part.strip().strip('"')
          
          # Extract attributes using regex that handles escaped quotes
          attrs = {}
          for m in re.finditer(r'(\w+)="((?:[^"\\]|\\.)*)"', attr_part):
            key, val = m.groups()
            attrs[key] = val.replace('\\"', '"') # Unescape quotes

          # Get type and code
          lab = attrs.get('label','')
          node_type = re.split(r'[,\s]', lab, 1)[0] if lab else None
          code_frag = attrs.get('CODE')
          
          if node_type and code_frag is not None:
            code_fragments[current_center].append({
              'node_id': node_id,
              'type': node_type,
              'code': code_frag
            })

    # --- 2) Ensure center fragment is first in each list ---
    for ctr, frags in code_fragments.items():
      for i, f in enumerate(frags):
        if f['node_id'] == ctr:
          frags.insert(0, frags.pop(i))
          break

    # --- 3) Build global node_id → node_index map ---
    node_id_to_index = {}
    next_idx = 0
    # 3a) assign all centers first, in encounter order
    for ctr in code_fragments.keys():
      if ctr not in node_id_to_index:
        node_id_to_index[ctr] = next_idx
        next_idx += 1
    # 3b) then assign all remaining nodes, in the order they appear
    for frags in code_fragments.values():
      for f in frags:
        nid = f['node_id']
        if nid not in node_id_to_index:
          node_id_to_index[nid] = next_idx
          next_idx += 1

    # --- 4) Tokenize & annotate ---
    tokenized_subgraphs = {}
    for ctr, frags in code_fragments.items():
      tok_list = []
      for f in frags:
        nid   = f['node_id']
        ntype = f['type']
        code  = f['code']
        # tokenize
        try:
          tok = extract_and_replace_tokens(code)
           
          # Post-processing for string array initializers in Java
          # This handles a special case where string literals in array initializers
          # might be incorrectly tokenized as variables (VAR1, VAR2, etc.)
          # We detect array patterns and convert these variables to string tokens (STR1, STR2, etc.)
          # This is particularly important for Java code with string array literals
          if '{' in tok and '}' in tok and 'VAR' in tok:
            # Match all VAR tokens within array initializer braces
            array_content = re.search(r'\{\s*(.*?)\s*\}', tok)
            if array_content:
              content = array_content.group(1)
              # Replace VAR with STR for comma-separated items in array initializers
              vars_in_array = re.findall(r'VAR\d+', content)
              str_counter = 1
              for var in vars_in_array:
                if ',' in content:  # Only replace if it looks like an array of items
                  tok = tok.replace(var, f'STR{str_counter}')
                  str_counter += 1
           
          err = None
        except Exception as e:
          tok = code
          err = str(e)
        # edge_type_index
        if nid == ctr:
          edge_idx = 0
        else:
          labels = edge_info.get(ctr, {}).get(nid, [])
          idxs = [
            2 if lab=='AST'
            else 1 if lab=='CFG'
            else 0
            for lab in labels
          ]
          edge_idx = max(idxs) if idxs else 0

        entry = {
          'node_id':         nid,
          'node_index':      node_id_to_index[nid],
          'edge_type_index': edge_idx,
          'type':            ntype,
          'original':        code,
          'tokenized':       tok
        }
        if err:
          entry['error'] = err
        tok_list.append(entry)

      tokenized_subgraphs[ctr] = tok_list

    # --- 5) Write human‐readable _tokenized.txt ---
    with open(output_file_path, 'w', encoding='utf-8') as f_out:
      f_out.write(f"# Tokenized code for {base_name}\n")
      for ctr, lst in tokenized_subgraphs.items():
        f_out.write(f"CENTER_NODE: {ctr}  NODE_INDEX: "
                    f"{node_id_to_index[ctr]}\n")
        f_out.write(f"FRAGMENT_COUNT: {len(lst)}\n")
        for i, frag in enumerate(lst):
          f_out.write(f"  NODE_ID[{i}]: {frag['node_id']}\n")
          f_out.write(f"  NODE_INDEX[{i}]: {frag['node_index']}\n")
          f_out.write(f"  EDGE_TYPE_INDEX[{i}]: "
                      f"{frag['edge_type_index']}\n")
          f_out.write(f"  TYPE[{i}]: {frag['type']}\n")
          f_out.write(f"  ORIGINAL[{i}]: {frag['original']}\n")
          f_out.write(f"  TOKENIZED[{i}]: {frag['tokenized']}\n")
          if 'error' in frag:
            f_out.write(f"  ERROR[{i}]: {frag['error']}\n")
        f_out.write("\n")

    logging.info(
      f"Finished tokenizing {base_name}: "
      f"{len(tokenized_subgraphs)} centers, "
      f"{sum(len(v) for v in tokenized_subgraphs.values())} frags."
    )
    return base_name, tokenized_subgraphs

  except Exception as e:
    logging.error(
      f"Error in process_context_file({base_name}): {e}",
      exc_info=False
    )
    return base_name, None


# --- Main Execution ---
if __name__ == "__main__":
    multiprocessing.freeze_support() # For Windows compatibility

    """ --- Stage 1: Subgraph Extraction --- """
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


    """ --- Stage 2: Tokenization --- """
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

        # split by suffix
        splits = {'good': {}, 'bad': {}, 'mixed': {}}
        for base_name, token_data in all_tokenized_data.items():
          variant = base_name.rsplit('-', 1)[-1]
          if variant in splits:
            splits[variant][base_name] = token_data
          else:
            splits['mixed'][base_name] = token_data

        # write out three jsons
        for variant, bucket in splits.items():
          file_name = f"all_tokenized_{variant}.json"
          file_path = os.path.join(tokenized_output_path, file_name)
          with open(file_path, 'w', encoding='utf-8') as jf:
            json.dump(bucket, jf, indent=2)
          logging.info(f"Wrote {len(bucket)} records → {file_name}")


    logging.info(f"--- Tokenization process complete. Results saved to {tokenized_output_path} ---")
    logging.info(f"Check the log file '{log_file}' for detailed processing information.")