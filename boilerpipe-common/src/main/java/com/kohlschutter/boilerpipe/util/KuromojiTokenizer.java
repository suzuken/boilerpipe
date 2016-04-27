package com.kohlschutter.boilerpipe.util;


import com.atilika.kuromoji.ipadic.Token;
import com.atilika.kuromoji.ipadic.Tokenizer;

import java.util.List;

/**
 * KuromojiTokenizer provides tokens separeted by Kuromoji (ipadic).
 */
public class KuromojiTokenizer implements ITokenizer{
    private Tokenizer.Mode mode;

    public KuromojiTokenizer(Tokenizer.Mode mode) {
        this.mode = mode;
    }

    public String[] tokenize(CharSequence text) {
        Tokenizer tokenizer = new Tokenizer();
        List<Token> tokens = tokenizer.tokenize(text.toString());
        String[] array = new String[tokens.size()];
        int idx = 0;
        for (Token token : tokens)
            array[idx++] = token.getSurface();
        return array;
    }
}
