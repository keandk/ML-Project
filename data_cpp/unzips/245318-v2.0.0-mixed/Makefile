.DEFAULT_GOAL=help
.PHONY: run build clean help
SRC = src
BUILD = build
TESTCASESUPPORT = $(SRC)\testcasesupport
rwildcard = $(wildcard $1$2) $(foreach d,$(wildcard $1*),$(call rwildcard,$d/,$2))
SRCS := $(call rwildcard,$(SRC),*.cpp) $(call rwildcard,$(SRC),*.cc) $(call rwildcard,$(SRC),*.c)
OBJS := $(subst /,\,$(SRCS:%=$(BUILD)/%.obj))
INCLUDEMAIN=true
CFLAGS = /I$(TESTCASESUPPORT) /DINCLUDEMAIN /W3 /MT /GS /RTC1 /bigobj /EHsc /nologo
LDFLAGS =
CC = cl
LD = LINK
TARGET = $(BUILD)\CWE78_OS_Command_Injection__char_environment_w32_spawnv_74a.exe


run: $(TARGET) ## Run the test case

build: $(TARGET) ## Build the test case

$(TARGET): $(OBJS) ## Build the binary
ifeq ($(INCLUDEMAIN),true)
	$(LD) $^ /OUT:$@
endif

$(BUILD)\\%.c.obj: %.c ## Build object files for C
	if not exist "$(dir $@)" mkdir "$(dir $@)"
	$(CC) $(CFLAGS) /c $< /Fo$@

$(BUILD)\\%.cpp.obj: %.cpp ## Build object files for C++
	if not exist "$(dir $@)" mkdir "$(dir $@)"
	$(CC) $(CFLAGS) /c $< /Fo$@

clean: ## Clean the test case
	rmdir /Q /S $(BUILD)

help: ## Show this help
	@grep -E '^[a-zA-Z0-9_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'
