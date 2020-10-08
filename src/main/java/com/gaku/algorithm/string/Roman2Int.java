package com.gaku.algorithm.string;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/roman-to-integer/
 */
public class Roman2Int {

    public static void main(String[] args) {
        String t = "IV";
        System.out.println(romanToInt(t));
    }

    public static int romanToInt(String s) {

        Map<String, Integer> map = new HashMap<>();
        map.put("M", 1000);
        map.put("CM", 900);
        map.put("D", 500);
        map.put("CD", 400);
        map.put("C", 100);
        map.put("XC", 90);
        map.put("L", 50);
        map.put("XL", 40);
        map.put("X", 10);
        map.put("IX", 9);
        map.put("V", 5);
        map.put("IV", 4);
        map.put("I", 1);

        int ret = 0;

        for(int i = 0; i <= s.length() - 1; i++){

            if (i+ 1 <= s.length() - 1){
                char c = s.charAt(i);
                String temp = s.substring(i, i+2);
                Integer a = map.get(temp);
                if (a != null){
                    ret += a;
                    i++;
                }else {
                    ret += map.get(c + "");
                }


            }else {
                char c = s.charAt(i);
                ret += map.get(c +"");

            }


        }

        return ret;

    }
}
