import os
import re
import torch
import random
from tqdm import tqdm
import numpy as np
from gensim.models import Word2Vec
from sklearn.model_selection import train_test_split
from torch_geometric.data import Data

"""DEFINE"""
LANGUAGE = "java"
CONTEXT_DIR = f'data_{LANGUAGE}/subgraph_contexts'
W2V_MODEL_PATH = f'data_{LANGUAGE}/word2vec.model'
TYPE_TABLE_PATH = f'data_{LANGUAGE}/vuln-char-table-final.csv'
DATASET_DIR = f'data_{LANGUAGE}/dataset_graphs'
EMBED_DIM = 100  # your word2vec dimension
RANDOM_SEED = 42
os.makedirs(DATASET_DIR, exist_ok=True)
w2v = Word2Vec.load(W2V_MODEL_PATH)