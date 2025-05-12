# train.py

import os, glob, pickle
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.metrics import classification_report
import torch
import torch.nn.functional as F
from torch_geometric.data import Data, DataLoader, Dataset
from torch_geometric.nn import RGCNConv, global_mean_pool

# ——— 1) Thông số ———
PKL_FOLDER  = "data_c/processed_subgraphs"
BATCH_SIZE  = 32
LR          = 1e-3
EPOCHS      = 50
RANDOM_SEED = 42

# ——— 2) Dataset flatten subgraphs ———
class SubgraphDataset(Dataset):
    def __init__(self, pkl_folder, transform=None, pre_transform=None):
        super().__init__(None, transform, pre_transform)
        self.graphs = []  # sẽ là list of (sg_dict, label)
        for path in glob.glob(os.path.join(pkl_folder, "*.pkl")):
            with open(path, 'rb') as f:
                subs = pickle.load(f)    # subs là list của subgraph dicts
            # label từ tên file (ví dụ nếu có 'good' → 0, else → 1)
            label = 0 if 'good' in os.path.basename(path) else 1
            for sg in subs:
                self.graphs.append((sg, label))

    def len(self):
        return len(self.graphs)

    def get(self, idx):
        sg, label = self.graphs[idx]

        # giờ sg['nodes'] là list of dicts, mỗi dict có feature_vector
        x = torch.tensor([n['feature_vector'] for n in sg['nodes']],
                         dtype=torch.float)

        # edges
        edge_index = torch.tensor(
            [[e['src'], e['dst']] for e in sg['edges']],
            dtype=torch.long
        ).t().contiguous()
        edge_type = torch.tensor([e.get('type_id',0) for e in sg['edges']],
                                 dtype=torch.long)

        y = torch.tensor([label], dtype=torch.long)
        return Data(x=x, edge_index=edge_index, edge_type=edge_type, y=y)


# reproducibility
torch.manual_seed(RANDOM_SEED)
np.random.seed(RANDOM_SEED)

# load dataset
dataset = SubgraphDataset(PKL_FOLDER)
print(f"Loaded {len(dataset)} graphs")

# ——— 3) Split train/val/test ———
all_idx = list(range(len(dataset)))
labels  = [dataset[i].y.item() for i in all_idx]

train_idx, test_idx = train_test_split(
    all_idx, test_size=0.2, random_state=RANDOM_SEED, stratify=labels
)
train_idx, val_idx = train_test_split(
    train_idx, test_size=0.1, random_state=RANDOM_SEED,
    stratify=[labels[i] for i in train_idx]
)

train_loader = DataLoader(dataset[train_idx], batch_size=BATCH_SIZE, shuffle=True)
val_loader   = DataLoader(dataset[val_idx],   batch_size=BATCH_SIZE, shuffle=False)
test_loader  = DataLoader(dataset[test_idx],  batch_size=BATCH_SIZE, shuffle=False)

print(f"Split sizes → train: {len(train_idx)}, val: {len(val_idx)}, test: {len(test_idx)}")

# ——— 4) Model RGCN + MLP ———
class RGCN(torch.nn.Module):
    def __init__(self, in_dim, hidden_dim, num_relations, num_classes):
        super().__init__()
        self.conv1 = RGCNConv(in_dim,  hidden_dim, num_relations)
        self.conv2 = RGCNConv(hidden_dim, hidden_dim, num_relations)
        self.lin   = torch.nn.Linear(hidden_dim, num_classes)

    def forward(self, data):
        x, edge_index, edge_type, batch = (
            data.x, data.edge_index, data.edge_type, data.batch
        )
        x = F.relu(self.conv1(x, edge_index, edge_type))
        x = F.relu(self.conv2(x, edge_index, edge_type))
        x = global_mean_pool(x, batch)
        return self.lin(x)

# khởi tạo mô hình
sample      = dataset[0]
in_dim      = sample.num_node_features
num_rel     = int(sample.edge_type.max().item()) + 1
num_classes = int(sample.y.max().item()) + 1

model     = RGCN(in_dim, hidden_dim=128, num_relations=num_rel, num_classes=num_classes)
optimizer = torch.optim.Adam(model.parameters(), lr=LR)

# ——— 5) Train & Validate ———
def train_epoch():
    model.train()
    total_loss = 0
    for batch in train_loader:
        optimizer.zero_grad()
        out  = model(batch)
        loss = F.cross_entropy(out, batch.y)
        loss.backward()
        optimizer.step()
        total_loss += loss.item() * batch.num_graphs
    return total_loss / len(train_loader.dataset)

def evaluate(loader):
    model.eval()
    all_y, all_pred = [], []
    with torch.no_grad():
        for batch in loader:
            out  = model(batch)
            pred = out.argmax(dim=1)
            all_y.append(batch.y.cpu().numpy())
            all_pred.append(pred.cpu().numpy())
    y_true = np.concatenate(all_y)
    y_pred = np.concatenate(all_pred)
    return classification_report(y_true, y_pred, digits=4)

best_val = float('inf')
for epoch in range(1, EPOCHS+1):
    train_loss = train_epoch()

    # validation loss
    model.eval()
    val_loss = 0
    with torch.no_grad():
        for batch in val_loader:
            out  = model(batch)
            val_loss += F.cross_entropy(out, batch.y).item() * batch.num_graphs
    val_loss /= len(val_loader.dataset)

    print(f"Epoch {epoch:02d} | Train Loss: {train_loss:.4f} | Val Loss: {val_loss:.4f}")
    if val_loss < best_val:
        best_val = val_loss
        torch.save(model.state_dict(), "best_rgcn.pt")

# ——— 6) Test ———
model.load_state_dict(torch.load("best_rgcn.pt"))
print("=== Test Report ===")
print(evaluate(test_loader))
