.DEFAULT_GOAL=help
.PHONY: run build clean help
DIR := $(realpath $(dir $(realpath $(lastword $(MAKEFILE_LIST)))))
STONESOUP := $(DIR)/stonesoup
SS_DEPS = $(STONESOUP)/dependencies
CFLAGS := -fno-stack-protector
CPPFLAGS= -DSTONESOUP_TRACE=1 -I$(SS_DEPS)/include
LDFLAGS := -L$(SS_DEPS)/lib -L$(SS_DEPS)/lib64 -Wl,-R$(STONESOUP)/workspace/install/lib -Wl,-R$(STONESOUP)/workspace/install/lib64 -Wl,-R$(SS_DEPS)/lib -Wl,-R$(SS_DEPS)/lib64 -Wl,-z,execstack
LIBS:= -ldl
TARGET = install/bin/grep


run: $(TARGET) ## Run the test case

build: $(TARGET) ## Build the test case

$(TARGET): .installed
	cd src && ./configure --prefix="$(DIR)/install" --exec-prefix="$(DIR)/install" --with-included-regex CFLAGS="$(CFLAGS)" CPPFLAGS="$(CPPFLAGS)" LDFLAGS="$(LDFLAGS)" LIBS="$(LIBS)"
	$(MAKE) -C src V=1 CC="gcc" CCLD="gcc"
	$(MAKE) -C src install

clean: ## Clean the test case
	rm -r install

.installed: ## Install the dependencies
	sh install-dependencies.sh

help: ## Show this help
	@grep -E '^[a-zA-Z0-9_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'
