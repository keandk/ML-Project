# Pipeline
 
1. get_samples.py: Download and extract malicious code samples from SARD dataset
2. process_c_cpgs.sh (or process_java_cpgs.sh): Generates Code Property Graphs (CPGs) from source code using Joern (have to run on Linux)
3. create_vuln_char_table.py: Create table mapping node types to malicious characteristics
4. select_centernode.py: Select center nodes that represent malicious code patterns
5. subgraph_building_and_tokenizing.py: buile and tokenize subgraphs (1-hop) from CPGs from malicious center nodes
6. subgraph_embedding.py: Generate embeddings (from node represetion and edge represiontation) for malicious code subgraphs

# Joern Installation and CPG Export Guide

This guide explains how to install Joern and use it to generate Code Property Graphs (CPGs) from Java source code.

## Prerequisites

- Linux/WSL environment
- Java Development Kit (JDK) 17 or later (Installation instructions below)
- Python 3.x
- curl
- Git

To install Java (JDK 17):
```bash
# Update package list
sudo apt update

# Install OpenJDK 17
sudo apt install openjdk-17-jdk

# Verify installation
java -version
javac -version

# Set up JAVA_HOME
sudo update-alternatives --config java
echo 'export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64' >> ~/.bashrc
source ~/.bashrc
```

## Installation Steps

1. Install Joern:
```bash
# Download the installation script
curl -L "https://github.com/joernio/joern/releases/latest/download/joern-install.sh" -o joern-install.sh

# Make it executable
chmod +x joern-install.sh

# Run installation (you can specify a custom installation directory with --install-dir)
./joern-install.sh
```

2. Add Joern to your PATH:
```bash
# Add this line to your ~/.bashrc or ~/.zshrc
export PATH="$PATH:/path/to/joern/joern-cli"

# Reload your shell configuration
source ~/.bashrc  # or source ~/.zshrc
```

## Generating CPGs

1. Make the processing script executable:
```bash
chmod +x process_java_cpgs.sh
```

2. Run the script:
```bash
./process_java_cpgs.sh
```

The script will:
- Create a `cpg-output` directory if it doesn't exist
- Process each Java project directory in `data/java-src`
- Generate CPG files in DOT format for each project

## Script Details

The `process_java_cpgs.sh` script:
```bash
#!/bin/bash
OUTPUT_DIR="/cpg-output"
mkdir -p "$OUTPUT_DIR"

for dir in /data/java-src/*/; do
  project_name=$(basename "$dir")
  echo "Processing $project_name..."
  
  # Parse the Java code
  joern-parse "$dir"
  
  # Export the CPG to various formats
  joern-export --repr all --format dot --out "$OUTPUT_DIR/$project_name"
done
```

## Available Export Formats

You can modify the script to export CPGs in different formats:

- DOT format (Graphviz): `--format dot`
- GraphML format: `--format graphml`
- GraphSON format: `--format graphson`
- Neo4j CSV format: `--format neo4jcsv`

## Available Graph Representations

The following representations can be exported:

- Abstract Syntax Trees (AST): `--repr ast`
- Control Flow Graphs (CFG): `--repr cfg`
- Control Dependence Graphs (CDG): `--repr cdg`
- Data Dependence Graphs (DDG): `--repr ddg`
- Program Dependence Graphs (PDG): `--repr pdg`
- Code Property Graphs (CPG14): `--repr cpg14`
- All representations: `--repr all`

## Interactive Usage

You can also use Joern interactively:

1. Start Joern:
```bash
joern
```

2. Import code:
```scala
importCode("/path/to/java/src", "project-name", [], "java")
```

3. Generate data flow:
```scala
run.ossdataflow
```

4. Export specific representations:
```scala
run.dumpast
run.dumpcfg
run.dumpcdg
run.dumpddg
run.dumppdg
run.dumpcpg14
```

## Troubleshooting

1. If Joern fails to parse Java code:
   - Ensure you have JDK 17 or later installed
   - Try enabling type recovery: `--enable-type-recovery`
   - Check if the Java code compiles independently

2. If the export fails:
   - Check if the output directory exists and has write permissions
   - Ensure enough disk space is available
   - Check Joern logs for specific error messages

3. Memory issues:
   - Increase Java heap size: `export JAVA_OPTS="-Xmx8g"`
   - Process fewer projects at a time

## Additional Resources

- [Joern Documentation](https://docs.joern.io)
- [Code Property Graph Documentation](https://docs.joern.io/code-property-graph)
- [Java Frontend Documentation](https://docs.joern.io/frontends/java)
- [Export Documentation](https://docs.joern.io/export)