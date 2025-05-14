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
PKL_FOLDER  = "data_java/processed_subgraphs"
PYG_DATA_FILE = os.path.join(PKL_FOLDER, "all_subgraphs_pyg.pt")
BATCH_SIZE  = 32
LR          = 1e-3
EPOCHS      = 50
RANDOM_SEED = 42
# New hyperparameters for regularization
DROPOUT_RATE = 0.5 # Example dropout rate
WEIGHT_DECAY = 5e-4 # Example weight decay (L2 regularization)
EARLY_STOPPING_PATIENCE = 10 # Example patience for early stopping

# Set device
device = torch.device('cuda' if torch.cuda.is_available() else 'cpu')
print(f"Using device: {device}")

# ——— 2) Dataset flatten subgraphs ———
class SubgraphDataset(Dataset):
    def __init__(self, pyg_data_path, transform=None, pre_transform=None):
        super().__init__(None, transform, pre_transform)
        
        # Load the list of PyG Data objects directly from the .pt file
        if not os.path.exists(pyg_data_path):
            raise FileNotFoundError(f"PyG data file not found: {pyg_data_path}. "
                                    f"Please ensure '{pyg_data_path}' exists by running the preprocessing script.")

        # Load the list of Data objects using torch.load()
        print(f"Loading PyG data from {pyg_data_path}...") # Add loading message
        self.graphs = torch.load(pyg_data_path, weights_only=False)
        print(f"Finished loading {len(self.graphs)} graphs.")

        # --- Validation Step --- 
        print("Validating graph data integrity...")
        invalid_indices = []
        for i, data in enumerate(self.graphs):
            if data.edge_index is not None and data.edge_index.numel() > 0:
                num_nodes = data.num_nodes # Get number of nodes for this graph
                max_edge_index = data.edge_index.max().item()
                if max_edge_index >= num_nodes:
                    print(f"  [ERROR] Graph {i}: Invalid edge_index. Max index is {max_edge_index}, but num_nodes is {num_nodes}. Edge Index: {data.edge_index}")
                    invalid_indices.append(i)
            # Optional: Check for NaNs or Infs in features if needed
            # if torch.isnan(data.x).any() or torch.isinf(data.x).any():
            #    print(f"  [WARN] Graph {i}: Contains NaN/Inf in node features.")

        if invalid_indices:
             # Option 1: Raise error
             raise ValueError(f"Found {len(invalid_indices)} graphs with invalid edge indices (max index >= num_nodes). Check preprocessing script. Problematic graph indices: {invalid_indices[:10]}...")
             # Option 2: Filter out invalid graphs (use with caution)
             # print(f"[WARN] Removing {len(invalid_indices)} graphs with invalid edge indices.")
             # self.graphs = [g for i, g in enumerate(self.graphs) if i not in invalid_indices]
             # print(f"Dataset size after filtering: {len(self.graphs)}")
        else:
            print("Graph data validation successful.")
        # --- End Validation --- 

    def len(self): 
        return len(self.graphs)

    def get(self, idx):
        # Simply return the pre-loaded Data object
        return self.graphs[idx] 


# reproducibility
torch.manual_seed(RANDOM_SEED)
np.random.seed(RANDOM_SEED)
if torch.cuda.is_available():
    torch.cuda.manual_seed(RANDOM_SEED)

# load dataset
dataset = SubgraphDataset(PYG_DATA_FILE)
print(f"Loaded {len(dataset)} graphs from {PYG_DATA_FILE}")

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
    def __init__(self, in_dim, hidden_dim, num_relations, num_classes, dropout_rate):
        super().__init__()
        self.conv1 = RGCNConv(in_dim,  hidden_dim, num_relations)
        self.conv2 = RGCNConv(hidden_dim, hidden_dim, num_relations)
        self.lin   = torch.nn.Linear(hidden_dim, num_classes)
        self.dropout = torch.nn.Dropout(p=dropout_rate) # Add dropout layer

    def forward(self, data):
        x, edge_index, edge_type, batch = (
            data.x, data.edge_index, data.edge_type, data.batch
        )
        x = F.relu(self.conv1(x, edge_index, edge_type))
        x = self.dropout(x) # Apply dropout
        x = F.relu(self.conv2(x, edge_index, edge_type))
        x = self.dropout(x) # Apply dropout
        x = global_mean_pool(x, batch)
        return self.lin(x)

# khởi tạo mô hình
sample      = dataset[0]
in_dim      = sample.num_node_features
num_rel     = 3 # Hardcoded based on the 3 types (0, 1, 2) defined in preprocessing
num_classes = int(sample.y.max().item()) + 1

model     = RGCN(in_dim, hidden_dim=128, num_relations=num_rel, num_classes=num_classes, dropout_rate=DROPOUT_RATE).to(device) # Pass dropout_rate
optimizer = torch.optim.Adam(model.parameters(), lr=LR, weight_decay=WEIGHT_DECAY) # Add weight_decay

# ——— 5) Train & Validate ———
def train_epoch():
    model.train()
    total_loss = 0
    for batch in train_loader:
        batch = batch.to(device)
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
            batch = batch.to(device)
            out  = model(batch)
            pred = out.argmax(dim=1)
            all_y.append(batch.y.cpu().numpy())
            all_pred.append(pred.cpu().numpy())
    y_true = np.concatenate(all_y)
    y_pred = np.concatenate(all_pred)
    return classification_report(y_true, y_pred, digits=4)

best_val_loss = float('inf') # Renamed for clarity
best_epoch = 0 # New: Track best epoch
patience_counter = 0 # New: Counter for early stopping

print("\nStarting training...")
for epoch in range(1, EPOCHS+1):
    train_loss = train_epoch()

    # --- Validation --- 
    model.eval()
    val_loss = 0
    with torch.no_grad():
        for batch in val_loader:
            batch = batch.to(device)
            out  = model(batch)
            val_loss += F.cross_entropy(out, batch.y).item() * batch.num_graphs
    val_loss /= len(val_loader.dataset)
    # --- End Validation ---

    print(f"Epoch {epoch:02d} | Train Loss: {train_loss:.4f} | Val Loss: {val_loss:.4f}")

    # --- Checkpointing and Early Stopping Logic --- 
    if val_loss < best_val_loss:
        best_val_loss = val_loss
        best_epoch = epoch # Store the epoch number
        torch.save(model.state_dict(), "best_rgcn.pt")
        print(f"  Saved new best model (Epoch {best_epoch})")
        patience_counter = 0 # Reset patience counter
    else:
        patience_counter += 1 # Increment patience counter
        print(f"  Validation loss did not improve. Patience: {patience_counter}/{EARLY_STOPPING_PATIENCE}")

    if patience_counter >= EARLY_STOPPING_PATIENCE:
        print(f"\nEarly stopping triggered at epoch {epoch}. Best validation loss was {best_val_loss:.4f} at epoch {best_epoch}.")
        break # Exit the training loop
    # --- End Checkpointing --- 

# Ensure best_epoch is set if training finishes normally without early stopping
if patience_counter < EARLY_STOPPING_PATIENCE:
     print(f"\nTraining finished after {EPOCHS} epochs. Best validation loss was {best_val_loss:.4f} at epoch {best_epoch}.")

# ——— 6) Test ———
# Load the *best* model for testing
print(f"\nLoading best model from epoch {best_epoch} (Validation Loss: {best_val_loss:.4f}) for testing...")
model.load_state_dict(torch.load("best_rgcn.pt"))
print("=== Test Report ===")
print(evaluate(test_loader))
