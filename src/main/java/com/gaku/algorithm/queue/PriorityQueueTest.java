package com.gaku.algorithm.queue;

public class PriorityQueueTest {

    public static void main(String[] args) {
        int[] a = {2,8,3,8,6,10,1, -1};


//        PriorityQueue<Integer> pq = new PriorityQueue<>();
//
//        for(int one : a){
//            pq.add(one);
//        }
//
//
//        while(!pq.isEmpty()){
//            System.out.println(pq.remove());
//        }
//
//
//        PriorityQueue<Integer> pq1 = new PriorityQueue<>(3);
//
//        for(int one : a){
//            pq1.add(one);
//            if (pq1.size() > 3){
//                pq1.remove();
//            }
//
//        }
//
//        while(!pq1.isEmpty()){
//            System.out.println(pq1.remove());
//        }

//        PriorityQueue<Holder> pq2 = new PriorityQueue<>();
//
//        for(int one : a){
//            pq2.add(new Holder(one, String.valueOf(one)));
//            if (pq2.size() > 3){
//                pq2.remove();
//            }
//        }
//
//        while (!pq2.isEmpty()){
//            System.out.println(pq2.remove());
//        }

        MaxPQ<Holder> pq3 = new MaxPQ<>(4);
        for(int one : a){
            pq3.insert(new Holder(one, String.valueOf(one)));
            if (pq3.size() > 3){
                pq3.delMax();
            }
        }

        while (!pq3.isEmpty()){
            Holder holder = pq3.delMax();
            System.out.println(holder);
        }







    }

    static class Holder implements Comparable<Holder>{
        Integer key;
        String val;

        public Holder(Integer key, String val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public String toString() {
            return "Holder{" +
                    "key=" + key +
                    ", val='" + val + '\'' +
                    '}';
        }

        @Override
        public int compareTo(Holder o) {
            return this.key - o.key;
        }
    }
}
