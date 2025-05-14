"""Test Function"""
import os
from gensim.models import Word2Vec

# 1. Đọc và stream tokens (sentences)
def stream_sentences(folder_path):
    # Yields token lists (sentences) one by one
    for fname in os.listdir(folder_path):
        path = os.path.join(folder_path, fname)
        if not os.path.isfile(path):
            continue
        with open(path, encoding='utf-8') as f:
            for line in f:
                if "TOKENIZED" in line:
                    parts = line.partition(":")[2].strip().split()
                    if parts: # Ensure parts is not empty
                        yield parts

# Thư mục dữ liệu và model
base_folder  = ["data_c", "data_cpp", "data_java"]
for folder in base_folder:
    folder_token = os.path.join(folder, "tokenized_contexts")
    model_folder = os.path.join(folder, "model")
    os.makedirs(model_folder, exist_ok=True)

    # Use the generator to stream sentences
    sentences_iterable = stream_sentences(folder_token)

    # 2. Huấn luyện Word2Vec (CBOW)
    model = Word2Vec(
        sentences=sentences_iterable,
        vector_size=512,
        window=5,
        min_count=1,
        workers=4,
        sg=0,      # 0 = CBOW, 1 = Skip-gram
        epochs=5   # Tăng nếu cần
    )

    # 3. Lưu model
    model_path = os.path.join(model_folder, f"word2vec.model")
    model.save(model_path)
    print(f"Đã lưu Word2Vec model vào: {model_path}")