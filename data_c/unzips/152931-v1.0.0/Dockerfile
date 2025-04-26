FROM ubuntu:16.04
LABEL maintainer="SAMATE, NIST"
WORKDIR /sard
RUN apt-get update && apt-get install -y --no-install-recommends curl make unzip build-essential intltool pkg-config libgtk2.0-dev
RUN mkdir -p stonesoup
RUN ln -s $PWD/stonesoup /opt/stonesoup
COPY . .
