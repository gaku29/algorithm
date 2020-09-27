package com.gaku.algorithm.queue;



public class MaxPQ<Key extends Comparable<Key>> {

    private Key[] pq;
    private int N = 0;

    public MaxPQ(int maxN){
        pq = (Key[]) new Comparable[maxN + 1]; // 编号从1开始
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public void insert(Key v){
        pq[++N] = v;
        swim(N);
    }

    public Key delMax(){
        Key max = pq[1];
        exch(1, N--);
        pq[N + 1] = null;
        sink(1);
        return max;
    }

    private void swim(int k){

        while (k > 1 && less(k/2, k)){
            exch(k/2, k);
            k = k/2;
        }
    }

    // 严格小
    private boolean less(int i , int j){
        return pq[i].compareTo(pq[j]) < 0 ? true : false;

    }

    private void sink(int k){

        while (2 * k <= N){ // 有儿子
            int j = 2 * k;
            if (j + 1 <= N){ //有两个儿子
                if (less(j, j + 1)){
                    j = j + 1;
                }
            }

            if (less(k, j)){
                exch(k, j);
                k = j;

            }else{
                break;
            }
        }

    }

    private void exch(int i, int j){
        Key v = pq[i];
        pq[i] = pq[j];
        pq[j] = v;
    }


}
