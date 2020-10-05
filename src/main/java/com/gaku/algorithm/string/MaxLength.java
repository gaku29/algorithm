package com.gaku.algorithm.string;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/swap-for-longest-repeated-character-substring/
 */
public class MaxLength {

    public static void main(String[] args) {
        MaxLength instance = new MaxLength();

        int max = instance.maxRepOpt1("aaabaaa");
        System.out.println(max);


    }

    public int maxRepOpt1(String text) {

        Map<Character, List<Segment>> map = new HashMap();

        int i = 0, j = 0;

        while (j <= text.length() - 1) {
            if (text.charAt(i) == text.charAt(j)) {
                j++;
            } else {
                Segment seg = new Segment(i, j - 1);
                List<Segment> list = map.get(text.charAt(i));
                if (list == null) {
                    list = new LinkedList();
                    list.add(seg);
                    map.put(text.charAt(i), list);
                } else {
                    list.add(seg);
                }
                i = j;
            }

        }

        Segment seg1 = new Segment(i, j - 1);
        List<Segment> list = map.get(text.charAt(i));
        if (list == null) {
            list = new LinkedList();
            list.add(seg1);
            map.put(text.charAt(i), list);
        } else {
            list.add(seg1);
        }

        int max = 0;

        for (Map.Entry<Character, List<Segment>> entry : map.entrySet()) {

            Character c = entry.getKey();
            List<Segment> segmentList = entry.getValue();

            if (segmentList.size() == 1) {
                Segment s = segmentList.get(0);
                int temp = s.end - s.begin + 1;
                if (temp > max) {
                    max = temp;
                }

            } else if (segmentList.size() == 2) {
                Segment s1 = segmentList.get(0);
                Segment s2 = segmentList.get(1);

                if (s2.begin - s1.end == 2){
                    int temp = s2.end - s2.begin + 1 + s1.end - s1.begin + 1 ;
                    if (temp > max){
                        max = temp;
                    }

                }else {
                    int temp1 = s1.end - s1.begin + 1 + 1;
                    if (temp1 > max) {
                        max = temp1;
                    }

                    int temp2 = s2.end - s2.begin + 1 + 1;
                    if (temp2 > max) {
                        max = temp2;
                    }
                }



            } else if (segmentList.size() >= 3) {

                for(int x = 0; x <= segmentList.size() - 2; x++){
                    Segment s1 = segmentList.get(x);
                    Segment s2 = segmentList.get(x + 1);

                    if (s2.begin - s1.end == 2){
                        int temp = s2.end - s1.begin + 1;
                        if (temp > max){
                            max = temp;
                        }
                    }else {


                        int temp1 = s1.end - s1.begin + 1;
                        if (temp1 > max) {
                            max = temp1;
                        }

                        int temp2 = s2.end - s2.begin + 1;
                        if (temp2 > max) {
                            max = temp2;
                        }
                    }


                }

            }


        }

        return max;


    }

    private static class Segment {
        int begin;
        int end;

        Segment(int b, int e) {
            this.begin = b;
            this.end = e;
        }

        @Override
        public String toString() {
            return begin + "  " + end;
        }
    }
}
