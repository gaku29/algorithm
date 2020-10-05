package com.gaku.algorithm.string;

import java.util.HashMap;
import java.util.Map;

/**
 * 状态机
 * https://leetcode-cn.com/problems/string-to-integer-atoi/
 * https://leetcode-cn.com/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof/
 *
 */
public class StateMachine {

    public static void main(String[] args) {

    }

    public int myAtoi(String str){
        Automaton automaton = new Automaton();
        for(int i = 0 ; i < str.length(); i++){
            automaton.get(str.charAt(i));
        }

        return (int) (automaton.sign * automaton.ans);
    }

    private static class Automaton{
        // 1 正数  -1 负数
        int sign = 1;
        long ans = 0;
        private String state = "start";

        private Map<String, String[]> table = new HashMap<String, String[]>(){
            {
                put("start", new String[]{"start", "signed", "in_number", "end"});
                put("signed", new String[]{"end", "end", "in_number", "end"});
                put("in_number", new String[]{"end", "end", "in_number", "end"});
                put("end", new String[]{"end", "end", "end", "end"});
            }
        };

        public void get(char c){
            state = table.get(state)[getCol(c)];
            if("in_number".endsWith(state)){
                ans = ans * 10 + c - '0';
                ans = sign == 1 ? Math.min(ans, (long)Integer.MAX_VALUE) : Math.min(ans, -(long)Integer.MIN_VALUE);
            }else if ("signed".equals(state)){
                sign = c == '+' ? 1 : -1;
            }

        }

        private int getCol(char c){
            if (c == ' '){
                return 0;
            }
            if (c == '+' || c == '-'){
                return 1;
            }

            if (Character.isDigit(c)){
                return 2;
            }

            return 3;
        }

    }


}
