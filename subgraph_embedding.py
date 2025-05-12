import os, re, csv, pickle
import numpy as np
import pandas as pd
from gensim.models import Word2Vec
from extractToken import extract_and_replace_tokens_c

# ———————— Cấu hình đường dẫn ————————
BASE        = "data_c"
CTX_FOLDER  = os.path.join(BASE, "subgraph_contexts")
OUT_FOLDER  = os.path.join(BASE, "processed_subgraphs")
MODEL_PATH  = os.path.join(BASE, "model", "word2vec.model")
CHAR_CSV    = os.path.join(BASE, "vuln-char-table-final.csv")

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
    toks = extract_and_replace_tokens_c(code).split()
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

# ———————— 5. Main loop: process & save ————————
def main():
    os.makedirs(OUT_FOLDER, exist_ok=True)
    node_map = load_type_mapping(CHAR_CSV)

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

        # 4) save list of subgraphs vào 1 file pickle
        base, _ = os.path.splitext(fname)
        out_path = os.path.join(OUT_FOLDER, f"{base}.pkl")
        with open(out_path, 'wb') as f:
            pickle.dump(subs, f)
        print(f"Processed {fname}: {len(subs)} subgraphs → {out_path}")

if __name__ == "__main__":
    main()
