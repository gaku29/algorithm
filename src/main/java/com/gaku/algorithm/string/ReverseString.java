package com.gaku.algorithm.string;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReverseString {

    public static void main(String[] args) {

        String s = "   I am a    student.   ";
        System.out.println(reverseWords2(s));

    }

    public static String reverseWords(String s){
        s = s.trim();
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }

    public static String reverseWords2(String s){
        StringBuilder sb = trimSpaces(s);

        reverse(sb, 0, sb.length() - 1);

        reverseEachWord(sb);

        return sb.toString();
    }

    private static void reverseEachWord(StringBuilder sb) {

        int n = sb.length();
        int start = 0, end = 0;

        while (start < n){
            while (end < n && sb.charAt(end) != ' '){
                end++;
            }

            reverse(sb, start, end - 1);
            start = end + 1;
            end++;
        }

    }

    private static void reverse(StringBuilder sb, int left, int right) {
        while (left < right){
            char temp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, temp);
        }
    }

    private static StringBuilder trimSpaces(String s) {
        int left = 0, right = s.length() - 1;
        while (left <= right && s.charAt(left) == ' '){
            ++left;
        }
        while (left <= right && s.charAt(right) == ' '){
            --right;
        }

        StringBuilder sb = new StringBuilder();
        while (left <= right){
            char c = s.charAt(left);
            if (c != ' '){
                sb.append(c);
            }else if (sb.charAt(sb.length() - 1) != ' '){
                sb.append(c);
            }

            ++left;
        }
        return sb;

    }
}
