VERSION := 0.7.7
ARCHIVE := kuromoji-$(VERSION).tar.gz
ARCHIVE_URL := https://github.com/downloads/atilika/kuromoji/$(ARCHIVE)
ARCHIVE_DIR := kuromoji-$(VERSION)
JAR_FILE := kuromoji-$(VERSION)/lib/kuromoji-$(VERSION).jar

all: $(ARCHIVE)

$(ARCHIVE):
	curl -L $(ARCHIVE_URL) -o $@

install: $(JAR_FILE)
	mvn install:install-file \
		-Dfile=$(JAR_FILE) \
		-DgroupId=org.atilika.kuromoji \
		-DartifactId=kuromoji \
		-Dversion=$(VERSION) \
		-Dpackaging=jar \
		-DgeneratePom=true

$(JAR_FILE): $(ARCHIVE_DIR)

$(ARCHIVE_DIR): $(ARCHIVE)
	tar xzf $<

clean:
	rm -rf $(ARCHIVE) $(ARCHIVE_DIR)

.PHONY: all install clean
