#!/bin/bash
OUTPUT_DIR="/home/keanlt/ML-Project/data_cpp/cpg-output"

mkdir -p "$OUTPUT_DIR"

# Get number of CPU cores
num_cores=$(nproc)

# Create a temporary file to store process IDs
pids_file=$(mktemp)

# Create log directory (assuming LOG_DIR is needed based on error message)
LOG_DIR="${OUTPUT_DIR}/logs"
mkdir -p "$LOG_DIR"

# Process projects in parallel
for dir in '/home/keanlt/ML-Project/data_cpp/cpp-src'/*/; do
  project_name=$(basename "$dir")
  project_output_dir="$OUTPUT_DIR/$project_name"
  
  # Skip if project has already been processed
  if [ -d "$project_output_dir" ] && [ -n "$(ls -A "$project_output_dir" 2>/dev/null)" ]; then
    echo "Skipping $project_name - already processed"
    continue
  fi
  
  echo "Processing $project_name..."

  # Run parsing and export in background using a subshell
  ( # Start subshell for background processing
    # Create a temporary working directory for this project
    temp_dir=$(mktemp -d)
    # Ensure cleanup happens even on errors within the subshell
    trap 'cd /; rm -rf "$temp_dir"; exit 1' ERR INT TERM

    cp -r "$dir"/* "$temp_dir/"
    cd "$temp_dir"

    log_file="$LOG_DIR/${project_name}.log"
    echo "Starting processing for $project_name" > "$log_file"

    # Parse the C code in the temporary directory - redirect output to log
    echo "Running joern-parse..." >> "$log_file"
    if ! joern-parse --language c . >> "$log_file" 2>&1; then
      echo "ERROR: Parsing failed for $project_name" >> "$log_file"
      echo "ERROR: Parsing failed for $project_name" >&2
      # Trap will handle cleanup and exit
      exit 1 # Exit subshell on failure
    fi

    # Export full graph - redirect output to log
    echo "Running joern-export..." >> "$log_file"
    if ! joern-export --repr all --format dot --out "$project_output_dir" >> "$log_file" 2>&1; then
      echo "ERROR: Export failed for $project_name" >> "$log_file"
      echo "ERROR: Export failed for $project_name" >&2
      # Trap will handle cleanup and exit
      exit 1 # Exit subshell on failure
    fi

    echo "Processing completed successfully for $project_name" >> "$log_file"

    # Cleanup temporary directory and its contents
    cd /
    rm -rf "$temp_dir"
    # Disable the trap on successful exit
    trap - ERR INT TERM
  ) & # End subshell and run it in the background

  # Store process ID of the backgrounded subshell
  echo $! >> "$pids_file"

  # Limit concurrent processes to number of CPU cores
  while [[ $(wc -l < "$pids_file") -ge $num_cores ]]; do
    # Wait for the oldest process to finish
    oldest_pid=$(head -n 1 "$pids_file")
    wait "$oldest_pid"
    status=$?
    if [ $status -ne 0 ]; then
      echo "A processing job (PID: $oldest_pid) failed. Check logs in $LOG_DIR" >&2
      # Decide if you want to exit immediately or let others finish
      # exit 1 # Uncomment to exit immediately on first failure
    fi
    # Remove the PID of the completed job
    sed -i '1d' "$pids_file"
    # Add a small sleep to prevent busy-waiting if jobs finish very quickly
    sleep 0.1
  done
done

# Wait for remaining processes to complete
failed=0
while IFS= read -r pid; do
  wait "$pid"
  status=$?
  if [ $status -ne 0 ]; then
    failed=1
    echo "PID $pid failed. Check logs in $LOG_DIR" >&2
  fi
done < <(cat "$pids_file") # Use process substitution to avoid issues with file modification during read


# Cleanup
rm "$pids_file"

if [ $failed -eq 0 ]; then
  echo "All C projects processed successfully. Results in $OUTPUT_DIR"
else
  echo "Some projects failed to process. Check logs in $LOG_DIR"
  exit 1
fi