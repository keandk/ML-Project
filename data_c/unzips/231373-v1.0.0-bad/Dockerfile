FROM gcc:5.1
LABEL maintainer="SAMATE, NIST"
WORKDIR /sard
RUN echo "deb http://archive.debian.org/debian wheezy main" > /etc/apt/sources.list
RUN echo "deb http://archive.debian.org/debian-security wheezy/updates main" >> /etc/apt/sources.list
RUN apt-get -o Acquire::Check-Valid-Until=false update && apt-get install -y --no-install-recommends curl make unzip libglib2.0-dev perl-modules bison flex gtk2.0 libpcap-dev libtool
COPY . .
