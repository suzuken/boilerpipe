VERSION := 0.7.7
ARCHIVE := kuromoji-$(VERSION).tar.gz
ARCHIVE_URL := https://github.com/downloads/atilika/kuromoji/$(ARCHIVE)
ARCHIVE_DIR := kuromoji-$(VERSION)
JAR_FILE := kuromoji-$(VERSION)/lib/kuromoji-$(VERSION).jar

TARGET := lib/kuromoji-$(VERSION).jar

all: $(TARGET)

$(TARGET): $(JAR_FILE)
	mkdir -p $(dir $@)
	cp $< $@

$(JAR_FILE): $(ARCHIVE_DIR)

$(ARCHIVE_DIR): $(ARCHIVE)
	tar xzf $<

$(ARCHIVE):
	curl -L $(ARCHIVE_URL) -o $@

install: $(TARGET)
	mvn install:install-file \
		-Dfile=$< \
		-DgroupId=org.atilika.kuromoji \
		-DartifactId=kuromoji \
		-Dversion=$(VERSION) \
		-Dpackaging=jar \
		-DgeneratePom=true

clean:
	rm -rf $(TARGET) $(ARCHIVE_DIR)

.PHONY: all install clean
