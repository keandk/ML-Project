.DEFAULT_GOAL=help
.PHONY: run build clean help
SRC = src
BUILD = build
TESTCASESUPPORT = $(SRC)\testcasesupport
OBJS = $(subst /,\, $(patsubst $(SRC)/%.c, $(BUILD)/%.obj, $(wildcard $(SRC)/testcasesupport/*.c $(SRC)/testcases/*/*/*.c $(SRC)/testcases/*/*.c)))
INCLUDEMAIN=true
CFLAGS = /I$(TESTCASESUPPORT) /DINCLUDEMAIN /W3 /MT /GS /RTC1 /bigobj /EHsc /nologo
LDFLAGS =
CC = cl
LD = LINK
TARGET = $(BUILD)\CWE78_OS_Command_Injection__wchar_t_listen_socket_w32_spawnlp_44.exe


run: $(TARGET) ## Run the test case

build: $(TARGET) ## Build the test case

$(TARGET): $(OBJS) ## Build the binary
ifeq ($(INCLUDEMAIN),true)
	$(LD) $^ /OUT:$@
endif

$(BUILD)\\%.obj: $(SRC)\%.c ## Build object files
	if not exist "$(dir $@)" mkdir "$(dir $@)"
	$(CC) $(CFLAGS) /c $< /Fo$@

clean: ## Clean the test case
	rmdir /Q /S $(BUILD)

help: ## Show this help
	@grep -E '^[a-zA-Z0-9_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'
