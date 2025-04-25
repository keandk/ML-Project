#!/bin/bash
OUTPUT_DIR="/mnt/d/University/HK6/ML/Project/ML-Project/data/cpg-output"
mkdir -p "$OUTPUT_DIR"

for dir in /mnt/d/University/HK6/ML/Project/ML-Project/data/java-src/*/; do
  project_name=$(basename "$dir")
  echo "Processing $project_name..."
  
  # Parse the Java code
  joern-parse "$dir"
  
  # Export the CPG to various formats
  joern-export --repr all --format dot --out "$OUTPUT_DIR/$project_name"
done