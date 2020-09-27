package com.gaku.algorithm.search;



public class BinarySearch {


    public static void main(String[] args) {

        int[] a = {1,2,3,4,5,6,7,8,9,10};

        System.out.println(binarySearch(a, 6));
    }

    private static int binarySearch(int[] a, int b){
        if (a == null || a.length == 0){
            return -1;
        }

        return binarySearch(a, b, 0, a.length - 1);

    }

    private static int binarySearch(int[] a, int b, int low , int high){
        if (low > high){
            return -1;
        }

        int mid = (low + high) / 2;

        if (a[mid] == b){
            return mid;
        }else if (a[mid] > b){
            return binarySearch(a, b, low, mid - 1);
        }else {
            return binarySearch(a, b, mid + 1, high);
        }

    }


}
