package com.gaku.css;

import java.util.ArrayList;
import java.util.List;

public class MyTokenizer implements Tokenizer{

    private List<Token> list = new ArrayList<>();
    int i = 0;

    public MyTokenizer(){
        Token token1 = new MyToken("b", TokenType.BEGIN);
        Token token2 = new MyToken("c", TokenType.BEGIN);
        Token token3 = new MyToken("text1", TokenType.TEXT);
        Token token4 = new MyToken("c", TokenType.END);
        Token token5 = new MyToken("c", TokenType.BEGIN);
        Token token6 = new MyToken("c", TokenType.END);
        Token token7 = new MyToken("d", TokenType.BEGIN);
        Token token8 = new MyToken("textd", TokenType.TEXT);
        Token token9 = new MyToken("d", TokenType.END);
        Token token10 = new MyToken("b", TokenType.END);

        list.add(token1);
        list.add(token2);
        list.add(token3);
        list.add(token4);
        list.add(token5);
        list.add(token6);
        list.add(token7);
        list.add(token8);
        list.add(token9);
        list.add(token10);

    }

    @Override
    public Token nextToken() {
        Token token =  list.get(i);
        i++;
        return token;
    }
}
