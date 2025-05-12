"""Test Function"""
import os
from gensim.models import Word2Vec


# 4. Load lại model (nếu muốn)
loaded_model = Word2Vec.load(model_path)
"""
Theo defination 7: trung binh cong cac token 
Input: Token of statement S (all), number of token of S
Output: represiontation vector of all embedding vector
"""
# 5. Ví dụ: chuyển token → vector trung bình
def tokens_to_vectors(tokens, model):
    vectors = []
    for token in tokens:
        if token in model.wv:
            vectors.append(model.wv[token])
    # return vectors
    if vectors:
        # Tính vector trung bình
        return [sum(x) / len(x) for x in zip(*vectors)]
    else:
        # Nếu không có token nào trong model, trả về vector 0
        return [0.0] * model.wv.vector_size

example_tokens = ["VAR1", "VAR1", "=", "VAR2", "[", "9", "]"]
vectors = tokens_to_vectors(example_tokens, loaded_model)
print("Vectors for example_tokens:", vectors)