FROM gcc:4.9.3
LABEL maintainer="SAMATE, NIST"
WORKDIR /sard
RUN echo "deb http://archive.debian.org/debian wheezy main" > /etc/apt/sources.list
RUN echo "deb http://archive.debian.org/debian-security wheezy/updates main" >> /etc/apt/sources.list
RUN apt-get -o Acquire::Check-Valid-Until=false update && apt-get install -y --no-install-recommends curl make unzip
COPY . .
