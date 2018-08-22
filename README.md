boilerpipe
==========

Boilerplate Removal and Fulltext Extraction from HTML pages

NOTE: This is a work-in-progress transmit from Google Code.

The latest stable version of boilerpipe is available at [`https://code.google.com/p/boilerpipe`](https://code.google.com/p/boilerpipe).

## Difference from original

You can switch tokenizer which implements `ITokenizer` interface. For example, Tokenizer using Kuromoji is available.

```java
// for example
String originalDocument = "<html><body>こんにちは、世界</body></html>";
HTMLDocument h = new HTMLDocument(originalDocument.getBytes(), Charset.forName("UTF-8"));
BoilerpipeSAXInput in = new BoilerpipeSAXInput(h.toInputSource());
BoilerpipeHTMLContentHandler handler = new BoilerpipeHTMLContentHandler(new KuromojiTokenizer(Tokenizer.Mode.SEARCH));
BoilerpipeHTMLParser parser = new BoilerpipeHTMLParser(handler);
String boiledDocument = ArticleExtractor.INSTANCE.getText(in.getTextDocument(parser));
```

### Kuromoji installation

To install Kuromoji without depending on [http://www.atilika.org/nexus/content/repositories/atilika/](http://www.atilika.org/nexus/content/repositories/atilika/), do:

```
make -f kuromoji.mk all install
```

which will download and install JAR from [https://github.com/atilika/kuromoji/downloads](https://github.com/atilika/kuromoji/downloads).
