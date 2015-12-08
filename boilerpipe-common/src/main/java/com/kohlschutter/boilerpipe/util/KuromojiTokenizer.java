package com.kohlschutter.boilerpipe.util;

import org.atilika.kuromoji.Token;
import org.atilika.kuromoji.Tokenizer;

import java.util.List;

/**
 * KuromojiTokenizer provides tokens separeted by Kuromoji.
 */
public class KuromojiTokenizer implements ITokenizer{
    private Tokenizer.Mode mode;

    public KuromojiTokenizer(Tokenizer.Mode mode) {
        this.mode = mode;
    }

    public String[] tokenize(CharSequence text) {
        Tokenizer tokenizer = Tokenizer.builder().mode(Tokenizer.Mode.SEARCH).build();
        List<Token> tokens = tokenizer.tokenize(text.toString());
        String[] array = new String[tokens.size()];
        int idx = 0;
        for (Token token : tokens)
            array[idx++] = token.getSurfaceForm();
        return array;
    }
}
