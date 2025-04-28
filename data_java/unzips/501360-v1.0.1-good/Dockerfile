FROM ubuntu:xenial

# INSTALL DEPENDENCIES
RUN apt-get update -yq
RUN apt-get install -yq --no-install-recommends openjdk-8-jdk maven ant git wget xz-utils

# RETRIEVE AND EXTRACT THE SOURCE CODE
WORKDIR /
RUN wget https://samate.nist.gov/SATE6/dspace-6.2-sate6.tar.xz
RUN tar --no-same-owner -Jxf dspace-6.2-sate6.tar.xz
RUN ln -s dspace-6.2-buggy sources_buggy
RUN ln -s dspace-6.2-fixed sources_fixed

# COMPILE THE SOURCE CODE
WORKDIR /sources_buggy
RUN mvn package

WORKDIR /sources_fixed
RUN mvn package

WORKDIR /

