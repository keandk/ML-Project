FROM ubuntu:16.04
LABEL maintainer="SAMATE, NIST"
WORKDIR /sard
RUN apt-get update && apt-get install -y --no-install-recommends curl make unzip build-essential libgtk+-3-dev bison flex
RUN mkdir -p stonesoup
RUN ln -s $PWD/stonesoup /opt/stonesoup
COPY . .
