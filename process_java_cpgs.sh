#!/bin/bash
OUTPUT_DIR="/home/anhanh/Downloads/Project/data/c-src/cpg-output"

# Clean up existing output directory and recreate it
rm -rf "$OUTPUT_DIR"
mkdir -p "$OUTPUT_DIR"

# Get number of CPU cores
num_cores=$(nproc)

# Create a temporary file to store process IDs
pids_file=$(mktemp)

# Process projects in parallel
for dir in '/home/anhanh/Downloads/Project/data/c-src'/*/; do
  project_name=$(basename "$dir")
  echo "Processing $project_name..."
  
  # Create project-specific output directory
  project_output_dir="$OUTPUT_DIR/$project_name"
  # mkdir -p "$project_output_dir"
  
  # Run parsing and export in background
  (
    # Create a temporary working directory for this project
    temp_dir=$(mktemp -d)
    cp -r "$dir"/* "$temp_dir/"
    cd "$temp_dir"
    
    # Parse the Java code in the temporary directory
    joern-parse .
    
    # Export the CPG to the final output directory
    joern-export --repr all --format dot --out "$project_output_dir"
    
    # Cleanup temporary directory and its contents
    cd /
    rm -rf "$temp_dir"
  ) &
  
  # Store process ID
  echo $! >> "$pids_file"
  
  # Limit concurrent processes to number of CPU cores
  if [[ $(wc -l < "$pids_file") -ge $num_cores ]]; then
    wait $(head -n 1 "$pids_file")
    sed -i '1d' "$pids_file"
  fi
done

# Wait for remaining processes to complete
for pid in $(cat "$pids_file"); do
  wait $pid
done

# Cleanup
rm "$pids_file"