package com.gaku.algorithm.backtrack;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个数组，给定一个target
 * 找出数组中的哪些元素之和等于target，把这些元素全部找出
 */
public class Target {

    public static void main(String[] args) {
        Target instance = new Target();

        System.out.println(instance.find(new double[]{2.2, 3.0, 2.8,2.90,2.00000}, 5.0));


    }


    public List<List<Double>> find(double[] a, double target){

        if (a == null || a.length == 0){
            return new LinkedList<>();
        }

        List<List<Double>> res = new LinkedList<>();

//        backtrack(0, new boolean[a.length], res, a, target);
        backtrack2(0, new LinkedList<>(), res, a, target);

        return res;




    }

    private void backtrack2(int i, List<Double> one, List<List<Double>> res, double[] a, double target){

        if (i >= a.length){

            double sum = 0;
            for(double d : one){
                sum += d;
            }
            if (sum == target){
                res.add(new LinkedList<>(one));
            }

        }else{
            one.add(a[i]);
            backtrack2(i + 1, one, res, a, target);
            one.remove(one.size() - 1);
            backtrack2(i + 1, one, res, a, target);
        }

    }

    private void backtrack(int i, boolean[] flags, List<List<Double>> res, double[] a, double target){

        if (i >= a.length){

            double sum = 0;
            List<Double> one = new LinkedList<>();
            for(int j = 0; j < flags.length; j++){
                if (flags[j]){
                    sum += a[j];
                    one.add(a[j]);
                }
            }
            if (sum == target){
                res.add(one);
            }

        }else{
            flags[i] = true;
            backtrack(i + 1, flags, res, a, target);
            flags[i] = false;
            backtrack(i + 1, flags, res, a, target);
        }
    }


}
