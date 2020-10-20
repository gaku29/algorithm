package com.gaku.css;

public class MyToken implements Token{

    private String val;
    private TokenType type;

    public MyToken(String val, TokenType type) {
        this.val = val;
        this.type = type;
    }

    @Override
    public String val() {
        return val;
    }

    @Override
    public TokenType type() {
        return type;
    }

    @Override
    public String toString() {
        return "MyToken{" +
                "val='" + val + '\'' +
                ", type=" + type +
                '}';
    }
}
