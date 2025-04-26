.DEFAULT_GOAL=help
.PHONY: run build clean help
SRC = src
BUILD = build
TESTCASESUPPORT = $(SRC)/testcasesupport
INCLUDEMAIN=true
SRCS := $(shell find $(TESTCASESUPPORT) -name *.c)
OBJS := $(SRCS:%=$(BUILD)/%.o)
CFLAGS = -I $(TESTCASESUPPORT) -DINCLUDEMAIN
LDFLAGS =
CC = g++
TARGET = $(BUILD)/src/testcases/CWE762_Mismatched_Memory_Management_Routines/s01/CWE762_Mismatched_Memory_Management_Routines__calloc_delete_array_01_bad
TARGETS = $(TARGET) $(BUILD)/src/testcases/CWE762_Mismatched_Memory_Management_Routines/s01/CWE762_Mismatched_Memory_Management_Routines__calloc_delete_array_01_good1


run: $(TARGET) ## Run the test case

build: $(TARGETS) ## Build the test case

$(BUILD)/%: $(BUILD)/%.cpp.o $(OBJS) ## Build the binary
	$(CC) $(CFLAGS) $^ $(LDFLAGS) -o $@

$(BUILD)/%.c.o: %.c
	mkdir -p $(dir $@)
	$(CC) $(CFLAGS) -c $< $(LDFLAGS) -o $@

$(BUILD)/%.cpp.o: %.cpp
	mkdir -p $(dir $@)
	$(CC) $(CFLAGS) -c $< $(LDFLAGS) -o $@

clean: ## Clean the test case
	rm -r $(BUILD)

help: ## Show this help
	@grep -E '^[a-zA-Z0-9_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'
