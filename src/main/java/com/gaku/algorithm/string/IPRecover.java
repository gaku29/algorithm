package com.gaku.algorithm.string;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 复原ip地址
 * https://leetcode-cn.com/problems/restore-ip-addresses/
 */
public class IPRecover {

    public static void main(String[] args) {
        IPRecover instance = new IPRecover();

        System.out.println(instance.restoreIpAddresses("25525511135"));

    }

    public List<String> restoreIpAddresses(String s) {


        return restore(s, 3);


    }

    private List<String> restore(String s, int pointNum){

        if (s != null && s.length() > 0 && pointNum == 0){
            List<String> abc = new ArrayList<>();
            if (valid(s)){
                abc.add(s);
            }
            return abc;
        }


        if ( (s == null || s.isEmpty()) && pointNum >= 0 ){
            return new ArrayList<>();
        }

        if (s != null && s.length() > 0 && pointNum < 0){
            return new ArrayList<>();
        }

        List<String> ret = new LinkedList<>();

        int i = 1;
        while (i < s.length() ){
            String temp = s.substring(0, i);
            if (valid(temp)){
                List<String> ret1 = restore(s.substring(i, s.length()), pointNum - 1);
                if (!ret1.isEmpty()){
                    for(String one : ret1){
                        ret.add(temp + "." + one );
                    }

                }
            }
            i++;

        }


        return ret;




    }

    private boolean valid(String s) {
        if (s == null || s.isEmpty()){
            return false;
        }

        if (s.length() > 3){
            return false;
        }

        if (s.length() >= 2 && s.charAt(0) =='0'){
            return false;
        }

        if (Integer.valueOf(s) > 255){
            return false;
        }

        return true;

    }
}
