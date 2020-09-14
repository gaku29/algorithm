package com.gaku.algorithm.mergingandfindingset;

public class MergeFindSet {

    private int[] a;

    public MergeFindSet(int n) {
        this.a = new int[n];
        for (int i = 0; i < n; i++){
            a[i] = i;
        }
    }

    public int find(int x){
        if (a[x] == x){
            return x;
        }

        return find(a[x]);
    }

    public void merge(int i , int j){
        a[find(i)] = find(j);
    }
}
