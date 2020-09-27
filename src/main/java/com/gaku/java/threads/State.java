package com.gaku.java.threads;

public class State {


    private int state = 0;

    public int getState(){
        return state;
    }

    public void nextState(){
        state = (++state) % 3;
    }
}
