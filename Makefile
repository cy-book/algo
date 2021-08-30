PACKAGE := hz.xhxh.algo
CODE_FILE := $(shell find src/main/java -name "*.java")
SRC_DIR := ./src/main/java
RESOURCE_DIR := ./src/main/resources/*
MAIN := hz.xhxh.algo.Main
OUT_DIR := ./target/classes


build:
	@javac -d $(OUT_DIR) $(CODE_FILE) 
	@cp -r $(RESOURCE_DIR) $(OUT_DIR)

run: build
	@java -cp $(OUT_DIR) $(MAIN)

package: build
	@jar -c -f $(PACKAGE).jar -e $(MAIN) -C $(OUT_DIR) .
	@mv $(PACKAGE).jar target

clean:
	@rm -rf target

