.DEFAULT_GOAL=help
.PHONY: run build clean help
DIR := $(realpath $(dir $(realpath $(lastword $(MAKEFILE_LIST)))))
STONESOUP := $(DIR)/stonesoup
SS_DEPS = $(STONESOUP)/dependencies
CFLAGS := -fno-stack-protector
CPPFLAGS= -DSTONESOUP_TRACE=1 -I$(SS_DEPS)/include
LDFLAGS := -L$(SS_DEPS)/lib -L$(SS_DEPS)/lib64 -Wl,-R$(STONESOUP)/workspace/install/lib -Wl,-R$(STONESOUP)/workspace/install/lib64 -Wl,-R$(SS_DEPS)/lib -Wl,-R$(SS_DEPS)/lib64 -Wl,-z,execstack
LIBS := -ldl
PKG_CONFIG_PATH=$(SS_DEPS)/lib:$(SS_DEPS)/lib/pkgconfig:$(SS_DEPS)/lib64:$(SS_DEPS)/lib64/pkgconfig
TARGET = install/bin/wireshark


run: $(TARGET) ## Run the test case

build: $(TARGET) ## Build the test case

$(TARGET): .installed
	cd src && ./configure --prefix="$(DIR)/install" --enable-wireshark --with-gtk3=yes --without-lua --without-qt --with-gcrypt --with-gnutls --with-libsmi --with-pcap --with-zlib --without-portaudio --without-libcap --without-krb5 --with-cres --with-adns --with-geoip CFLAGS="$(CFLAGS)" CPPFLAGS="$(CPPFLAGS)" LDFLAGS="$(LDFLAGS)" LIBS="$(LIBS)" PKG_CONFIG_PATH="$(PKG_CONFIG_PATH)"
	$(MAKE) -C src
	$(MAKE) -C src install

clean: ## Clean the test case
	rm -r install

.installed: ## Install the dependencies
	sh install-dependencies.sh

help: ## Show this help
	@grep -E '^[a-zA-Z0-9_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'
