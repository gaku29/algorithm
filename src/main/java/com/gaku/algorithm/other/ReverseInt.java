package com.gaku.algorithm.other;

public class ReverseInt {

    public static void main(String[] args) {

        System.out.println(reverseInt(123));
        System.out.println(reverseInt(123456789));
        System.out.println(reverseInt(-123456789));
        System.out.println(reverseInt(0));

    }

    private static int doReverseInt(int a){
        if (a >= 0)
            return reverseInt(a);

        return -1 * reverseInt(-a);
    }

    private static int reverseInt(int a){

        int ret = 0;

        while (a != 0){
            int y = a % 10;

            a/=10;

            ret = ret * 10 + y;

        }
        return ret;
    }
}
