FROM gcc:11.2.0
LABEL maintainer="SAMATE, NIST"
WORKDIR /sard
RUN apt-get update && apt-get install -y --no-install-recommends curl make unzip git
RUN git clone https://chromium.googlesource.com/chromium/tools/depot_tools.git --depth 1 /opt/depot_tools
ENV PATH="${PATH}:/opt/depot_tools"
RUN fetch --nohooks --no-history chromium
COPY . .
