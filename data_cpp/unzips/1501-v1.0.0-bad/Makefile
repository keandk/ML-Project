.DEFAULT_GOAL=help
.PHONY: run build clean help
SRC = src
BUILD = build
OBJS = $(subst /,\, $(patsubst $(SRC)/%, $(BUILD)/%.obj, $(wildcard $(SRC)/*.cpp)))
CFLAGS = /I$(TESTCASESUPPORT) /W3 /MT /GS /RTC1 /bigobj /EHsc /nologo
LDFLAGS =
CC = cl
LD = LINK
TARGET = $(BUILD)\Figure3-4-windows.exe


run: $(TARGET) ## Run the test case

build: $(TARGET) ## Build the test case

$(TARGET): $(OBJS) ## Build the binary
	$(LD) $^ /OUT:$@

$(BUILD)\\%.obj: $(basename $(SRC)\\%) ## Build object files
	-mkdir "$(dir $@)"
	$(CC) $(CFLAGS) /c $< /Fo$@

clean: ## Clean the test case
	rmdir /Q /S $(BUILD)

help: ## Show this help
	@grep -E '^[a-zA-Z0-9_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'
