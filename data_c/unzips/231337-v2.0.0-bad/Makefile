.DEFAULT_GOAL=help
.PHONY: run build clean help
TARGET = main/asterisk


run: $(TARGET) ## Run the test case

build: $(TARGET) ## Build the test case

$(TARGET): .installed
	./configure
	echo "1" > .cleancount
	$(MAKE)
	$(MAKE) install

.installed: ## Install the dependencies
	mv $(MAKEFILE_LIST) $(MAKEFILE_LIST).sard
	sh install-dependencies.sh

help: ## Show this help
	@grep -E '^[a-zA-Z0-9_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'
