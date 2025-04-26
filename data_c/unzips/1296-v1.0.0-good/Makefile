.DEFAULT_GOAL=help
.PHONY: run build clean help
SRC = src
BUILD = build
CFLAGS = -I $(SRC)/include -DBIND_8_COMPAT
LDFLAGS = -lresolv
CC = gcc
TARGET = $(BUILD)/iquery-ok


run: $(TARGET) ## Run the test case

build: $(TARGET) $(BUILD)/create ## Build the test case

$(TARGET): $(SRC)/iquery-ok.c
	mkdir -p $(dir $@)
	$(CC) $(CFLAGS) $^ $(LDFLAGS) -o $@

$(BUILD)/create: $(SRC)/create_iquery.c
	mkdir -p $(dir $@)
	$(CC) $(CFLAGS) $^ $(LDFLAGS) -o $@
	$@

clean: ## Clean the test case
	rm -r $(BUILD)

help: ## Show this help
	@grep -E '^[a-zA-Z0-9_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'
