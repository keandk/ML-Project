.DEFAULT_GOAL=help
.PHONY: run build clean help
SRC = src
BUILD = build
TESTCASESUPPORT = $(SRC)/testcasesupport
OBJS = $(patsubst $(SRC)/%.c, $(BUILD)/%.o, $(wildcard $(TESTCASESUPPORT)/*.c $(SRC)/testcases/*/*/*.c $(SRC)/testcases/*/*.c))
INCLUDEMAIN=true
CFLAGS = -I $(TESTCASESUPPORT) -DINCLUDEMAIN
LDFLAGS =
CC = gcc
TARGET = $(BUILD)/CWE78_OS_Command_Injection__wchar_t_environment_execlp_54a


run: $(TARGET) ## Run the test case

build: $(TARGET) ## Build the test case

$(TARGET): $(OBJS) ## Build the binary
ifeq ($(INCLUDEMAIN),true)
	$(CC) $(CFLAGS) $^ $(LDFLAGS) -o $@
endif

$(BUILD)/%.o: $(SRC)/%.c ## Build object files
	mkdir -p $(dir $@)
	$(CC) $(CFLAGS) -c $< $(LDFLAGS) -o $@

clean: ## Clean the test case
	rm -r $(BUILD)

help: ## Show this help
	@grep -E '^[a-zA-Z0-9_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'
