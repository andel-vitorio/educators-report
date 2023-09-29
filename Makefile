# Makefile para compilar e executar o programa Java

# Comandos
JAVAC = javac
JAVA = java

# Diretórios
SRC_DIR = ./src
BIN_DIR = ./bin
LIB_DIR = ./lib

# Arquivos
MAIN_CLASS = App

# Encontre todos os arquivos JAR no diretório "lib"
JARS = $(wildcard $(LIB_DIR)/*.jar)

# Classpath com todos os arquivos JAR no diretório "lib"
CLASSPATH = $(BIN_DIR):$(JARS)

.PHONY: build exec run

build:
	@mkdir -p $(BIN_DIR)
	$(JAVAC) -d $(BIN_DIR) -sourcepath $(SRC_DIR) $(SRC_DIR)/$(MAIN_CLASS).java

exec: build
	$(JAVA) -cp $(CLASSPATH) $(MAIN_CLASS)

run: exec

clean:
	@rm -rf $(BIN_DIR)
