.DEFAULT_GOAL=help
.PHONY: run build clean help
DIR := $(realpath $(dir $(realpath $(lastword $(MAKEFILE_LIST)))))
SS_DEPS := $(DIR)/stonesoup/dependencies
TARGET = install/jena-core-2.11.0.jar


run: $(TARGET) ## Run the test case

build: $(TARGET) ## Build the test case

$(TARGET): .installed
	mkdir -p \
	src/jena-jdbc/jena-jdbc-driver-remote/src/main/resources \
	src/jena-jdbc/jena-jdbc-driver-tdb/src/main/resources \
	src/jena-jdbc/jena-jdbc-driver-mem/src/main/resources \
	src/jena-jdbc/jena-jdbc-driver-bundle/src/main/java \
	src/jena-jdbc/jena-jdbc-driver-bundle/src/main/resources
	cd src && ant \
	-Dstonesoup.database.postgres.required=no \
	-Dstonesoup.hibernate.postgres.required=no \
	-Dstonesoup.hibernate.mysql.required=no \
	-Dstonesoup.database.mysql.required=no \
	-Dstonesoup.socket.required=yes -lib $(SS_DEPS)/java/stonesoup/lttng/lttng-stonesoup-0.1.jar \
	-Dstonesoup.socket.lib.dir="$(SS_DEPS)/java/stonesoup/socket" \
	-Dstonesoup.hibernate.mysql.lib.dir="$(SS_DEPS)/java/stonesoup/hibernate/mysql" \
	-Dstonesoup.hibernate.postgres.lib.dir="$(SS_DEPS)/java/stonesoup/hibernate/postgres" \
	-Dstonesoup.database.mysql.lib.dir="$(SS_DEPS)/java/stonesoup/database/mysql" \
	-Dstonesoup.database.postgres.lib.dir="$(SS_DEPS)/java/stonesoup/database/postgres" \
	-Dbin.lib.dir="$(DIR)/install" \
	-Ddependencies.dir="$(SS_DEPS)/java/jena" \
	-Dstonesoup.lttng.lib.dir="$(SS_DEPS)/java/stonesoup/lttng" \
	-Dstonesoup.lttng.dummy.lib.dir="$(SS_DEPS)/java/stonesoup/lttng-dummy" jar

clean: ## Clean the test case
	rm -r install

.installed: ## Install the dependencies
	sh install-dependencies.sh

help: ## Show this help
	@grep -E '^[a-zA-Z0-9_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'
