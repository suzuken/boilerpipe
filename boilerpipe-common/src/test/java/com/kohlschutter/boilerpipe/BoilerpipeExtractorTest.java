package com.kohlschutter.boilerpipe;

import com.atilika.kuromoji.ipadic.Tokenizer;
import com.kohlschutter.boilerpipe.extractors.ArticleExtractor;
import com.kohlschutter.boilerpipe.sax.BoilerpipeHTMLContentHandler;
import com.kohlschutter.boilerpipe.sax.BoilerpipeHTMLParser;
import com.kohlschutter.boilerpipe.sax.BoilerpipeSAXInput;
import com.kohlschutter.boilerpipe.sax.HTMLDocument;
import com.kohlschutter.boilerpipe.util.KuromojiTokenizer;
import org.junit.Test;

import java.net.URL;
import java.nio.charset.Charset;

import static org.junit.Assert.*;

public class BoilerpipeExtractorTest {

    @Test
    public void testInitializeExtractor() throws Exception {
        assertEquals(ArticleExtractor.INSTANCE.getText("<html><body>body</body></html>"), "");
    }

    @Test
    public void testInitializeContentHandlerWithKuromoji() throws Exception {
        String body = "<html><title>こんにちは</title><body><h1>こんにちは、世界</h1></body></html>";
        try {
            HTMLDocument h = new HTMLDocument(body.getBytes("UTF-8"), Charset.forName("UTF-8"));
            BoilerpipeHTMLContentHandler handler = new BoilerpipeHTMLContentHandler(new KuromojiTokenizer(Tokenizer.Mode.SEARCH));
            BoilerpipeHTMLParser p = new BoilerpipeHTMLParser(handler);
            BoilerpipeSAXInput in = new BoilerpipeSAXInput(h.toInputSource());
            String boiled = ArticleExtractor.INSTANCE.getText(in.getTextDocument(p));
            assertEquals("こんにちは", in.getTextDocument(p).getTitle());
            assertEquals(boiled, "");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}