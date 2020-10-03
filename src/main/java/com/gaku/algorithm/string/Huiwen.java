package com.gaku.algorithm.string;

/**
 * 回文字符串
 */
public class Huiwen {


    public static void main(String[] args) {

        String a = "aaaaaaaaaa";


        String ret = "";

        for(int i = 0; i < a.length(); i++){
            String temp = get(a, i);

            if (temp.length() > ret.length()){
                ret = temp;
            }


            String temp1 = get1(a, i);

            if (temp1.length() > ret.length()){
                ret = temp1;
            }
        }

        System.out.println(ret);

    }


    private static String get(String a, int pos){

        int i = pos - 1;
        int j = pos + 1;

        while(i >= 0 && j <= a.length() - 1){

            if (a.charAt(i) == a.charAt(j)){
                i--;
                j++;
            }else{
                break;
            }
        }

        return a.substring(i+1, j);

    }

    private static String get1(String a, int pos){
        int i = pos;
        int j = pos + 1;

        while(i >= 0 && j <= a.length() - 1){

            if (a.charAt(i) == a.charAt(j)){
                i--;
                j++;
            }else{
                break;
            }
        }

        return a.substring(i+1, j);
    }
}
