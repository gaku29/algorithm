package com.gaku.algorithm.string;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/permutation-i-lcci/
 */
public class Pailie {

    public static void main(String[] args) {
        String[]  r = permutation("qwe");
        System.out.println(Arrays.toString(r));
    }


    public static String[] permutation(String S) {

        if (S == null || S.length() == 0){
            return new String[0];
        }

        if (S.length() == 1){
            String[] x = new String[1];
            x[0] = S;
            return x;
        }

        List<String> list  = new LinkedList();

        for(int i = 0; i <= S.length() - 1; i++){
            char   c = S.charAt(i);
            String temp = S.substring(0, i) + S.substring(i + 1, S.length());

            String[] ttt = permutation(temp);
            for(String t : ttt){
                list.add(String.valueOf(c) + t);
            }
        }

        return  list.toArray(new String[0]);



    }


}
