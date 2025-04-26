#!/usr/bin/env sh

if [ -f ./installed ]; then
    exit 0
fi

dependencies="dspace-sate6-fixed-v6.2.zip"

for dep in $dependencies; do
    curl --proto '=https' --tlsv1.2 -sSfL "https://samate.nist.gov/SARD/downloads/dependencies/$dep" --output dependency.zip
    unzip -n dependency.zip
    rm dependency.zip
done

touch .installed
