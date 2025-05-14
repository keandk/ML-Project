import os, re
import numpy as np
import pandas as pd
from gensim.models import Word2Vec
from extractToken import extract_and_replace_tokens
import torch
from torch_geometric.data import Data

# ———————— Cấu hình đường dẫn ————————
BASE        = "data_java"
CTX_FOLDER  = os.path.join(BASE, "subgraph_contexts")
OUT_FOLDER  = os.path.join(BASE, "processed_subgraphs")
MODEL_PATH  = os.path.join(BASE, "model", "word2vec.model")
CHAR_CSV    = os.path.join(BASE, "vuln-char-table-final.csv")
PYG_DATA_FILE = os.path.join(OUT_FOLDER, "all_subgraphs_pyg.pt")

# ———————— 1. Hàm parse 1 file context ————————
def parse_context_file(path):
    """
    Đọc file context, gom multi-line CODE[...] về một dòng,
    rồi parse như bình thường.
    """
    # 1) Load và gộp các dòng node/edge multi-line
    flattened = []
    buffer = None
    with open(path, 'r', encoding='utf-8') as f:
        for raw in f:
            line = raw.rstrip('\n')
            # Nếu đang trong buffer (đã bắt đầu một block nhưng chưa kết thúc)
            if buffer is not None:
                buffer += ' ' + line.strip()
                if line.strip().endswith('];'):
                    flattened.append(buffer)
                    buffer = None
                continue

            # Nếu là dòng bắt đầu node/edge
            if re.match(r'^\s*"\d+"\s*\[', line) and not line.strip().endswith('];'):
                # mở buffer và tiếp tục đọc
                buffer = line.strip()
                continue

            # bình thường: thêm thẳng
            flattened.append(line)

    # 2) Dùng flattened list thay cho việc đọc line-by-line
    subgraphs = []
    current = None
    attr_pattern = re.compile(r'(\w+)="([^"]*)"')

    for line in flattened:
        line = line.strip()
        if not line or line.startswith('#'):
            continue

        # START_SUBGRAPH
        m_start = re.match(r'^START_SUBGRAPH center_node=(\d+)', line)
        if m_start:
            center = m_start.group(1)
            current = {'center_str': center, 'nodes': [], 'edges': []}
            subgraphs.append(current)
            continue

        # END_SUBGRAPH
        if line.startswith('END_SUBGRAPH'):
            current = None
            continue

        if current is None:
            continue

        # edge
        if '->' in line:
            m = re.match(
                r'^"(?P<src>\d+)"\s*->\s*"(?P<dst>\d+)"\s*\[(?P<attrs>.+)\];', line
            )
            if m:
                attrs = dict(attr_pattern.findall(m.group('attrs')))
                current['edges'].append({
                    'src_str': m.group('src'),
                    'dst_str': m.group('dst'),
                    'type': attrs.get('label')
                })
            continue

        # node
        m = re.match(r'^"(?P<id>\d+)"\s*\[(?P<attrs>.+)\];', line)
        if m:
            attrs = dict(attr_pattern.findall(m.group('attrs')))
            current['nodes'].append({
                'id_str': m.group('id'),
                'label': attrs.get('label'),
                'name': attrs.get('NAME'),
                'code': attrs.get('CODE')
            })
            continue

    # 3) Sắp xếp và đánh id như cũ
    for sg in subgraphs:
        center_str = sg['center_str']
        # đảm bảo có center node
        center_nodes = [n for n in sg['nodes'] if n['id_str']==center_str]
        if not center_nodes:
            # thêm node center trống nếu cần
            sg['nodes'].append({'id_str': center_str, 'label':None, 'name':None, 'code':None})
            center_node = sg['nodes'][-1]
        else:
            center_node = center_nodes[0]

        others = [n for n in sg['nodes'] if n['id_str']!=center_str]
        ordered = [center_node] + others

        id_map = {}
        for idx, node in enumerate(ordered):
            node['id'] = idx
            id_map[node['id_str']] = idx
        sg['nodes'] = ordered

        real_edges = []
        for e in sg['edges']:
            if e['src_str'] in id_map and e['dst_str'] in id_map:
                real_edges.append({
                    'src': id_map[e['src_str']],
                    'dst': id_map[e['dst_str']],
                    'type': e['type']
                })
        sg['edges'] = real_edges
        del sg['center_str']

    return subgraphs


# ———————— 2. Load mapping node-type ————————
def load_type_mapping(csv_path):
    df = pd.read_csv(csv_path)
    mapping = {}
    for idx, nt in enumerate(df['Node Type'].astype(str)):
        for key in re.split(r'\s*/\s*', nt):
            if key:
                mapping[key] = idx
    return mapping

# ———————— 3. Classify nodes & edges ————————
def classify_nodes(subgraphs, mapping):
    for sg in subgraphs:
        for node in sg['nodes']:
            t = None
            lbl = node.get('label')
            if lbl and lbl in mapping:
                t = mapping[lbl]
            else:
                nm = node.get('name') or ''
                key = nm.split('.',1)[1] if '.' in nm else nm
                t = mapping.get(key)
            node['type_id'] = t if t is not None else -1
    return subgraphs

def classify_edges(subgraphs):
    for sg in subgraphs:
        for e in sg['edges']:
            tt = (e.get('type') or '').strip().lower()
            e['type_id'] = 1 if tt=='cfg' else 2 if tt=='ast' else 0
    return subgraphs

# ———————— 4. Tính code_vector & feature_vector ————————
w2v = Word2Vec.load(MODEL_PATH)
vec_size = w2v.vector_size

def compute_code_vector(code: str):
    code = code or ""
    toks = extract_and_replace_tokens(code).split()
    vecs = [w2v.wv[t] for t in toks if t in w2v.wv]
    return np.mean(vecs, axis=0) if vecs else np.zeros(vec_size,)

def add_vectors(subgraphs):
    for sg in subgraphs:
        for n in sg['nodes']:
            cv = compute_code_vector(n.get('code',''))
            n['code_vector']    = cv.tolist()
            # concat type_id trước
            fv = np.concatenate(([n.get('type_id',0)], cv))
            n['feature_vector'] = fv.tolist()
    return subgraphs

def add_feature_matrix(subgraphs):
    for sg in subgraphs:
        # Collect all feature vectors from nodes
        features = [n['feature_vector'] for n in sg['nodes']]
        # Concatenate along axis 0 (rows = nodes, cols = features)
        feature_matrix = np.stack(features, axis=0) if features else np.zeros((0, 1 + vec_size))
        sg['feature_matrix'] = feature_matrix
    return subgraphs

# ———————— 5. Main loop: process & save ————————
def main():
    os.makedirs(OUT_FOLDER, exist_ok=True)
    node_map = load_type_mapping(CHAR_CSV)

    all_pyg_data = []
    skipped_graphs_count = 0

    for fname in os.listdir(CTX_FOLDER):
        path_in = os.path.join(CTX_FOLDER, fname)
        if not os.path.isfile(path_in):
            continue

        # 1) parse
        subs = parse_context_file(path_in)
        # 2) classify
        subs = classify_nodes(subs, node_map)
        subs = classify_edges(subs)
        # 3) vectors
        subs = add_vectors(subs)
        # 4) add feature matrix (this step can be integrated into Data object creation)
        # No need to call add_feature_matrix separately if creating Data objects directly
        # subs = add_feature_matrix(subs) 

        # Determine label from filename
        label = 0 if 'good' in fname or 'mixed' in fname else 1

        # Convert each subgraph dict to a PyG Data object and add to the list
        for i, sg_dict in enumerate(subs):
            # Calculate feature_matrix for the current subgraph
            features = [n['feature_vector'] for n in sg_dict['nodes']]
            feature_matrix = np.stack(features, axis=0) if features else np.empty((0, 1 + vec_size))

            # Validation: Check if graph has nodes
            if feature_matrix.shape[0] == 0:
                # print(f"  [WARN] Skipping subgraph {i} in {fname}: No nodes after processing.")
                skipped_graphs_count += 1
                continue # Skip this subgraph if it has no nodes

            # Node features (x)
            x = torch.tensor(feature_matrix, dtype=torch.float)
            num_nodes = x.shape[0] # Get actual number of nodes

            # Edges (edge_index, edge_type)
            if sg_dict.get('edges') and len(sg_dict['edges']) > 0:
                # Filter edges again to ensure indices are valid *after* node processing
                valid_edges = [e for e in sg_dict['edges'] if e['src'] < num_nodes and e['dst'] < num_nodes]
                
                if valid_edges:
                    edge_index = torch.tensor(
                        [[e['src'], e['dst']] for e in valid_edges],
                        dtype=torch.long
                    ).t().contiguous()
                    edge_type = torch.tensor(
                        [e.get('type_id', 0) for e in valid_edges], 
                        dtype=torch.long
                    )
                else: # No valid edges remain
                    edge_index = torch.empty((2, 0), dtype=torch.long)
                    edge_type = torch.empty((0,), dtype=torch.long)
            else:
                # Handle cases with originally no edges
                edge_index = torch.empty((2, 0), dtype=torch.long)
                edge_type = torch.empty((0,), dtype=torch.long)

            # Label (y)
            y = torch.tensor([label], dtype=torch.long)
            
            # Create Data object
            data = Data(x=x, edge_index=edge_index, edge_type=edge_type, y=y)
            
            # Final Sanity Check (optional but recommended)
            if data.edge_index.numel() > 0 and data.edge_index.max().item() >= data.num_nodes:
                 print(f"  [CRITICAL WARN] Graph {i} in {fname}: Detected invalid edge index ({data.edge_index.max().item()}) vs num_nodes ({data.num_nodes}) AFTER filtering. Skipping.")
                 skipped_graphs_count += 1
                 continue
                 
            all_pyg_data.append(data)

        print(f"Processed {fname}: Added {len(subs) - skipped_graphs_count} subgraphs (skipped {skipped_graphs_count} empty). Total added: {len(all_pyg_data)}")
        # Reset skip counter for next file if counting per file
        # skipped_graphs_count = 0 

    # Save the consolidated list of PyG Data objects using torch.save
    torch.save(all_pyg_data, PYG_DATA_FILE) 
    print(f"Total skipped graphs due to no nodes: {skipped_graphs_count}") # Print total skipped count
    print(f"All valid PyG Data objects ({len(all_pyg_data)} total) saved to {PYG_DATA_FILE}")

if __name__ == "__main__":
    main()
