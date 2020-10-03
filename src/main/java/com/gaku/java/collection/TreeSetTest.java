package com.gaku.java.collection;

import java.util.TreeSet;

public class TreeSetTest {

    public static void main(String[] args) {
        // 是个set， 还排了个序
        TreeSet<Integer> treeSet = new TreeSet();

        treeSet.add(3);
        treeSet.add(2);
        treeSet.add(4);
        treeSet.add(2);

        System.out.println(treeSet);

        System.out.println(treeSet.pollFirst());
        System.out.println(treeSet.pollLast());
        System.out.println(treeSet);
    }
}
