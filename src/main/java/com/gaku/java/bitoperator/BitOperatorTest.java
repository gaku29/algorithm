package com.gaku.java.bitoperator;

public class BitOperatorTest {

    public static void main(String[] args) {

        System.out.println((1<<2) - 1);

        int[] array = {-128, 127, 0, 3, -3, Integer.MIN_VALUE, Integer.MAX_VALUE};
        for(int a : array){
            System.out.println("当前a的值：" +a);
            System.out.println(Integer.toBinaryString(a)  + "   " + a);
            System.out.println(Integer.toBinaryString(a<<1)  + "   " + (a<<1));
            System.out.println(Integer.toBinaryString(a>>1)  + "   " + (a>>1));
            System.out.println(Integer.toBinaryString(a>>>2)  + "   " + (a>>>2));
            System.out.println("==============================================================");
        }




    }
}
