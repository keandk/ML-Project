.DEFAULT_GOAL=help
.PHONY: run build clean help
DIR := $(realpath $(dir $(realpath $(lastword $(MAKEFILE_LIST)))))
STONESOUP := $(DIR)/stonesoup
CFLAGS := -fno-stack-protector
CPPFLAGS= -DSTONESOUP_TRACE=1 -I$(STONESOUP)/dependencies/include
LDFLAGS := -L$(STONESOUP)/dependencies/lib -L$(STONESOUP)/dependencies/lib64 -Wl,-R$(STONESOUP)/workspace/install/lib -Wl,-R$(STONESOUP)/workspace/install/lib64 -Wl,-R$(STONESOUP)/dependencies/lib -Wl,-R$(STONESOUP)/dependencies/lib64 -Wl,-z,execstack
LIBS:= -ldl
TARGET = install/bin/ffmpeg


run: $(TARGET) ## Run the test case

build: $(TARGET) ## Build the test case

$(TARGET): .installed
	cd src && CPPFLAGS="$(CPPFLAGS)" ./configure --prefix="$(DIR)/install" --enable-pic --disable-static --enable-shared --disable-yasm --disable-doc --enable-pthreads --disable-w32threads --disable-os2threads --enable-zlib --enable-openssl --disable-asm --extra-cflags="$(CFLAGS)" --extra-ldflags="$(LDFLAGS)" --extra-libs="$(LIBS)"
	mv src/config.mak src/config.mak.bak
	awk -v compiler="gcc" '/CC=gcc/{gsub(/gcc/, compiler)};{print}' src/config.mak.bak > src/config.mak && true
	$(MAKE) -C src V=1
	$(MAKE) -C src install V=1

clean: ## Clean the test case
	$(MAKE) -C src clean
	rm -r install

.installed: ## Install the dependencies
	sh install-dependencies.sh

help: ## Show this help
	@grep -E '^[a-zA-Z0-9_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'
