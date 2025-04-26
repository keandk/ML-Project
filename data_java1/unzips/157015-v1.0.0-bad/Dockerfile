FROM ubuntu:14.04
LABEL maintainer="SAMATE, NIST"
WORKDIR /sard
RUN apt-get update && apt-get install -y --no-install-recommends curl make unzip ant openjdk-6-jdk
ENV JAVA_HOME=/usr/lib/jvm/java-6-openjdk-amd64
ENV PATH=$PATH:$JAVA_HOME/bin
COPY . .
