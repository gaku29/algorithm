package com.gaku.algorithm.tree.rb;


import static org.junit.Assert.*;

public class RedBlackBSTTest {

    @org.junit.Test
    public void put() {

        RedBlackBST<Integer, Integer> redBlackBST = new RedBlackBST<Integer, Integer>();
        redBlackBST.put(1, 1);
        redBlackBST.put(2, 2);
        redBlackBST.put(3, 3);
        redBlackBST.put(4, 4);
        redBlackBST.put(5, 5);


        RedBlackBST<Integer, Integer> redBlackBST1 = new RedBlackBST<Integer, Integer>();
        redBlackBST1.put(5, 5);
        redBlackBST1.put(4, 4);
        redBlackBST1.put(3, 3);
        redBlackBST1.put(2, 2);
        redBlackBST1.put(1, 1);

        RedBlackBST<Integer, Integer> redBlackBST2 = new RedBlackBST<Integer, Integer>();
        redBlackBST2.put(2,2);
        redBlackBST2.put(3,3);
        redBlackBST2.put(1,1);
        redBlackBST2.put(5,5);
        redBlackBST2.put(4,4);

        System.out.println("ddd");




    }
}