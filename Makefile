# Makefile para compilar e executar o programa Java

# Comandos
JAVAC = javac
JAVA = java
MKDIR = mkdir -p
CP = cp -r

# Diretórios
SRC_DIR = ./src
BIN_DIR = ./bin
LIB_DIR = ./lib
RES_DIR = $(SRC_DIR)/res
BIN_RES_DIR = $(BIN_DIR)/res
IMG_DIR = $(RES_DIR)/img  # Diretório de imagens de origem
BIN_IMG_DIR = $(BIN_RES_DIR)/img  # Diretório de destino para imagens
FONT_DIR = $(RES_DIR)/fonts  # Diretório de fontes de origem
BIN_FONT_DIR = $(BIN_RES_DIR)/fonts  # Diretório de destino para fontes

# Arquivos
MAIN_CLASS = App

# Encontre todos os arquivos JAR no diretório "lib"
JARS = $(wildcard $(LIB_DIR)/*.jar)

# Classpath com todos os arquivos JAR no diretório "lib"
CLASSPATH = $(BIN_DIR):$(JARS)

# Extensões de imagem
IMG_EXTENSIONS = jpg jpeg png gif bmp
IMG_PATTERN = $(IMG_EXTENSIONS:%=-o -iname '*.%')

.PHONY: build exec run copy-resources

build:
	$(MKDIR) $(BIN_DIR)
	$(JAVAC) -d $(BIN_DIR) -sourcepath $(SRC_DIR) $(SRC_DIR)/$(MAIN_CLASS).java

copy-images: build
	$(MKDIR) $(BIN_IMG_DIR)
	find $(IMG_DIR) -type f \( -iname '*.jpg' $(IMG_PATTERN) \) -exec $(CP) {} $(BIN_IMG_DIR) \; 2>/dev/null || true

copy-fonts: build
	$(MKDIR) $(BIN_FONT_DIR)
	find $(FONT_DIR) -type f \( -iname '*.ttf' \) -exec $(CP) {} $(BIN_FONT_DIR) \; 2>/dev/null || true

exec: copy-resources
	$(JAVA) -cp $(CLASSPATH) $(MAIN_CLASS)

run: exec

copy-resources: copy-images copy-fonts

clean:
	rm -rf $(BIN_DIR)
