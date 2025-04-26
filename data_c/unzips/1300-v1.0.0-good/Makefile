.DEFAULT_GOAL=help
.PHONY: run build clean help
SRC = src
BUILD = build
CFLAGS = -I $(SRC)/include -include sys/types.h
LDFLAGS =
CC = gcc
TARGET = $(BUILD)/ge-ok


run: $(TARGET) ## Run the test case

build: $(TARGET) ## Build the test case

$(TARGET): $(SRC)/main-ok.c $(SRC)/recipient-ok.c $(SRC)/util-ok.c
	mkdir -p $(dir $@)
	$(CC) $(CFLAGS) $^ $(LDFLAGS) -o $@

clean: ## Clean the test case
	rm -r $(BUILD)

help: ## Show this help
	@grep -E '^[a-zA-Z0-9_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'
