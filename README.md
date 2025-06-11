# RGCN-Based Vulnerability Detection in Source Code

A deep learning approach for automated vulnerability detection in C/C++ and Java source code using Relational Graph Convolutional Networks (RGCN) applied to Code Property Graphs (CPGs).

## Overview

This project implements a novel vulnerability detection system that:
- Extracts **Code Property Graphs (CPGs)** from source code using Joern
- Identifies **vulnerable code patterns** through statistical analysis  
- Builds **1-hop subgraphs** around vulnerability center nodes
- Learns **semantic representations** using Word2Vec on tokenized code
- Classifies **vulnerable vs. non-vulnerable** code using RGCN neural networks

The system achieves strong performance with **97.32% F1-score** on C/C++ datasets and **82.51% F1-score** on Java datasets.

## Architecture

### Data Processing Pipeline

```
Source Code → CPG Generation → Center Node Selection → Subgraph Extraction → 
Tokenization → Word2Vec Embedding → RGCN Training → Vulnerability Classification
```

### Key Components

1. **CPG Generation**: Uses Joern to extract structural representations
2. **Vulnerability Analysis**: Statistical identification of malicious code patterns  
3. **Subgraph Building**: 1-hop neighborhood extraction around center nodes
4. **Feature Learning**: Word2Vec embeddings for semantic code representation
5. **RGCN Classification**: Graph neural network for vulnerability detection

## Methodology

### Graph Representation
- **Nodes**: Code elements (variables, function calls, control structures)
- **Edges**: Three types - AST (syntax), CFG (control flow), DDG (data dependencies)  
- **Features**: Concatenation of vulnerability type embeddings and Word2Vec code vectors

### Model Architecture
- **Input**: Subgraph with node features (vulnerability type + code embeddings)
- **RGCN Layers**: Two relational convolution layers with 128 hidden dimensions
- **Aggregation**: Global mean pooling over subgraph nodes
- **Output**: Binary classification (vulnerable/non-vulnerable)

### Training Configuration
- **Optimizer**: Adam with learning rate 1e-3
- **Regularization**: Dropout (0.5) + Weight decay (5e-4)
- **Early Stopping**: Patience of 10 epochs on validation loss
- **Data Split**: 80% train, 10% validation, 10% test

## Setup and Installation

### Prerequisites

- **Operating System**: Linux/WSL (required for Joern)
- **Python**: 3.8+ with uv package manager
- **Java**: JDK 17 or later
- **Memory**: 8GB+ RAM recommended for large datasets

### Environment Setup

```bash
# Clone repository
git clone <repository-url>
cd ML-Project

# Create Python environment using uv
uv venv
source .venv/bin/activate  # Linux/Mac
# .venv\Scripts\activate   # Windows

# Install dependencies
uv pip install torch torch-geometric gensim scikit-learn pandas numpy tqdm pydot networkx matplotlib requests
```

### Joern Installation

```bash
# Download Joern installation script
curl -L "https://github.com/joernio/joern/releases/latest/download/joern-install.sh" -o joern-install.sh
chmod +x joern-install.sh

# Install Joern
./joern-install.sh

# Add to PATH
echo 'export PATH="$PATH:$HOME/joern/joern-cli"' >> ~/.bashrc
source ~/.bashrc

# Verify installation
joern --version
```

### Java Development Kit Setup

```bash
# Install OpenJDK 17
sudo apt update
sudo apt install openjdk-17-jdk

# Set JAVA_HOME
echo 'export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64' >> ~/.bashrc
source ~/.bashrc

# Verify installation
java -version
```

## Usage

### Complete Pipeline

Follow these steps to run the full vulnerability detection pipeline:

#### 1. Data Collection
Download vulnerable and non-vulnerable code samples from SARD dataset:

```bash
python get_samples.py
```

This downloads samples into `data_{language}/` directories with separate folders for source code, JSON metadata, and compressed files.

#### 2. CPG Generation
Generate Code Property Graphs using Joern:

```bash
# For C/C++ code
chmod +x process_c_cpgs.sh
./process_c_cpgs.sh

# For Java code  
chmod +x process_java_cpgs.sh
./process_java_cpgs.sh
```

Output: CPG files in DOT format under `data_{language}/cpg-output/`

#### 3. Vulnerability Analysis
Create vulnerability characteristic mapping:

```bash
python create_vuln_char_table.py
```

Output: `vuln-char-table-final.csv` with vulnerability type mappings

#### 4. Center Node Selection
Identify nodes representing vulnerability patterns:

```bash
python select_centernode.py
```

Output: `center_nodes_result.json` with selected vulnerability-relevant nodes

#### 5. Subgraph Extraction and Tokenization
Build 1-hop subgraphs and tokenize code:

```bash
python subgraph_building_and_tokenizing.py
```

Output: 
- `subgraph_contexts/`: Raw subgraph files
- `tokenized_contexts/`: Processed and tokenized code

#### 6. Word2Vec Training
Train semantic embeddings on tokenized code:

```bash
python word2vec.py
```

Output: `model/word2vec.model` with 512-dimensional code embeddings

#### 7. Graph Embedding
Convert subgraphs to PyTorch Geometric format:

```bash
python subgraph_embedding.py
```

Output: `processed_subgraphs/all_subgraphs_pyg.pt` ready for training

#### 8. Model Training
Train the RGCN vulnerability classifier:

```bash
python train.py
```

Output: 
- `best_rgcn.pt`: Best model checkpoint
- Training logs with validation metrics
- Final test set evaluation

### Configuration

Key parameters can be modified in individual scripts:

- **Language**: Set `LANGUAGE = "cpp"` or `"java"` in scripts
- **Sample Size**: Modify limits in `get_samples.py`
- **Model Architecture**: Adjust hidden dimensions, layers in `train.py`
- **Training**: Change learning rate, epochs, regularization in `train.py`

## Results

### Performance Metrics

| Dataset | Class    | Precision | Recall | F1-Score   |
| ------- | -------- | --------- | ------ | ---------- |
| C/C++   | 0 (Safe) | 84.10%    | 90.38% | 87.13%     |
| C/C++   | 1 (Vuln) | 98.05%    | 96.59% | **97.32%** |
| Java    | 0 (Safe) | 62.85%    | 87.65% | 73.21%     |
| Java    | 1 (Vuln) | 92.45%    | 74.49% | **82.51%** |

### Error Analysis
- **C/C++**: Low false positive rate (1.95%) and false negative rate (3.41%)
- **Java**: Higher false positive rate (7.55%) due to language complexity
- **Overall**: Strong performance on vulnerability detection (Class 1)

### Visualizations
Generated performance charts include:
- `performance_comparison_overall.png`: Complete metrics comparison
- `precision_comparison.png`: Precision across datasets
- `recall_comparison.png`: Recall analysis
- `f1_score_comparison.png`: F1-score comparison
- `class0_error_rates.png` & `class1_error_rates.png`: Error rate analysis

## Project Structure

```
ML-Project/
├── README.md                              # This documentation
├── get_samples.py                         # SARD dataset collection
├── process_c_cpgs.sh                      # C/C++ CPG generation
├── process_java_cpgs.sh                   # Java CPG generation  
├── create_vuln_char_table.py              # Vulnerability mapping
├── select_centernode.py                   # Center node identification
├── subgraph_building_and_tokenizing.py    # Subgraph extraction
├── word2vec.py                            # Semantic embedding training
├── subgraph_embedding.py                  # Graph data preparation
├── train.py                               # RGCN model training
├── plot.py                                # Performance visualization
├── feature_learning.py                    # Feature extraction utilities
├── extractToken.py                        # Code tokenization utilities
├── data_c/                                # C/C++ dataset and outputs
├── data_cpp/                              # C++ specific data
├── data_java/                             # Java dataset and outputs
├── torch-rgcn/                            # RGCN implementation library
└── docs/                                  # Additional documentation
```

## Technical Details

### Vulnerability Characteristics
The system identifies these vulnerability patterns:
- **Function calls**: Potentially unsafe API usage
- **Memory operations**: malloc/free, buffer operations
- **Type operations**: Casting, type checking
- **Control structures**: Conditional logic, loops
- **Data access**: Array indexing, field access
- **Assignment operations**: Variable modifications

### Graph Neural Network
- **Architecture**: Relational GCN with message passing
- **Edge Types**: AST (type=2), CFG (type=1), DDG (type=0)  
- **Aggregation**: Neighbor feature averaging with edge-type weighting
- **Activation**: ReLU between layers
- **Output**: Softmax classification over vulnerability classes

### Data Validation
- **Edge Index Validation**: Ensures graph connectivity integrity
- **Feature Consistency**: Validates node feature dimensions
- **Label Distribution**: Balanced sampling for training stability

## Troubleshooting

### Common Issues

**Joern Installation Problems**:
```bash
# Ensure Java 17+ is installed and JAVA_HOME is set
java -version
echo $JAVA_HOME

# Check Joern binary permissions
ls -la $(which joern)
```

**Memory Issues During Processing**:
```bash
# Increase Java heap size
export JAVA_OPTS="-Xmx8g"

# Process smaller batches
# Modify batch sizes in processing scripts
```

**PyTorch Geometric Installation**:
```bash
# Install with specific CUDA version if needed
uv pip install torch-geometric -f https://data.pyg.org/whl/torch-2.0.0+cpu.html
```

**Missing Dependencies**:
```bash
# Install system dependencies
sudo apt install build-essential python3-dev

# Reinstall Python packages
uv pip install --force-reinstall torch torch-geometric
```




## Acknowledgments

- **Joern**: Code analysis platform for CPG generation
- **PyTorch Geometric**: Graph neural network framework
- **SARD Dataset**: NIST Software Assurance Reference Dataset
- **Gensim**: Word2Vec implementation for semantic embeddings