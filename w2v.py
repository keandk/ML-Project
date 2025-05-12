import os
from gensim.models import Word2Vec

# Thư mục dữ liệu và model
base_folder  = "data_c"
folder_token = os.path.join(base_folder, "tokenized_contexts")
model_folder = os.path.join(base_folder, "model")
os.makedirs(model_folder, exist_ok=True)

# 1. Đọc và unique tokens
def read_unique_tokens(folder_path):
    tokens = []
    for fname in os.listdir(folder_path):
        path = os.path.join(folder_path, fname)
        if not os.path.isfile(path):
            continue
        with open(path, encoding='utf-8') as f:
            for line in f:
                if "TOKENIZED" in line:
                    parts = line.partition(":")[2].strip().split()
                    tokens.extend(parts)
    # loại trùng giữ thứ tự
    return list(dict.fromkeys(tokens))

unique_tokens = read_unique_tokens(folder_token)

# 2. Huấn luyện Word2Vec (CBOW)
model = Word2Vec(
    sentences=[ unique_tokens ],
    vector_size=512,
    window=5,
    min_count=1,
    workers=4,
    sg=0,      # 0 = CBOW, 1 = Skip-gram
    epochs=5   # Tăng nếu cần
)

# 3. Lưu model
model_path = os.path.join(model_folder, "word2vec.model")
model.save(model_path)
print(f"Đã lưu Word2Vec model vào: {model_path}")

# 4. Load lại model (nếu muốn)
loaded_model = Word2Vec.load(model_path)

# 5. Ví dụ: chuyển token → vector trung bình
def tokens_to_vectors(tokens, model):
    vectors = []
    for token in tokens:
        if token in model.wv:
            vectors.append(model.wv[token])
    if vectors:
        # Tính vector trung bình
        return [sum(x) / len(x) for x in zip(*vectors)]
    else:
        # Nếu không có token nào trong model, trả về vector 0
        return [0.0] * model.wv.vector_size

example_tokens = ["VAR1", "=", "VAR2", "[", "9", "]"]
vectors = tokens_to_vectors(example_tokens, loaded_model)
print("Vectors for example_tokens:", vectors)
