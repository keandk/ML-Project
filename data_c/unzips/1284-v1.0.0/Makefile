.DEFAULT_GOAL=help
.PHONY: run build clean help
SRC = src
BUILD = build
CFLAGS = -I $(SRC)/include
LDFLAGS =
CC = gcc
TARGET = $(BUILD)/obo-ok


run: $(TARGET) ## Run the test case

build: $(TARGET) ## Build the test case

$(TARGET): $(SRC)/call_fb_realpath.c $(SRC)/realpath-ok.c
	mkdir -p $(dir $@)
	$(CC) $(CFLAGS) $^ $(LDFLAGS) -o $@

setup:
	mkdir -p /tmp/foo/bar/foo/bar/foo/bar/foo/bar
	touch /tmp/foo/bar/foo/bar/foo/bar/foo/bar/abcdefghi
	ln -s /tmp/foo/bar/foo/bar/foo/bar/foo/bar/abcdefghi /tmp/foo/bar/foo/bar/foo/bar/foo/bar/abcdefgh

clean: ## Clean the test case
	rm -r $(BUILD)

help: ## Show this help
	@grep -E '^[a-zA-Z0-9_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'
