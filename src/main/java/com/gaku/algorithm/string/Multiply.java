package com.gaku.algorithm.string;


import java.util.LinkedList;
import java.util.List;

/***
 * https://leetcode-cn.com/problems/multiply-strings/
 */
public class Multiply {

    public static void main(String[] args) {



        Multiply multiply = new Multiply();
        System.out.println(multiply.multiply("1234" , "5678"));
        System.out.println(1234 * 5678);
    }


    public String multiply(String num1, String num2) {

        String sum = "0";

        for(int i = num2.length() - 1; i >= 0; i--){
            char c = num2.charAt(i);
            String ret = multiply(num1, c);

            int count =  num2.length() - 1 - i;
            for(int j = 0; j < count; j++){
                ret = ret + "0";
            }

            sum = add(sum, ret);
        }

        return sum;

    }

    private String add(String num1, String num2){

        Node node1 = toNodeList(num1);
        Node node2 = toNodeList(num2);

        Node result = add(node1, node2);

        return toStr(result);
    }

    private String toStr(Node node){
        StringBuilder sb = new StringBuilder();
        while (node != null){
            sb.append((char)('0' + node.val));
            node = node.next;
        }
        return sb.reverse().toString();
    }

    private Node add(Node node1, Node node2){

        Node head = null;
        Node tail = null;

        int flag = 0;

        while (node1 != null && node2 != null){
            int temp = node1.val + node2.val + flag;
            flag = temp / 10;

            Node node = new Node(temp % 10);

            if (head == null){
                head = node;
                tail = node;
            }else {
                tail.next = node;
                tail = tail.next;
            }

            node1 = node1.next;
            node2 = node2.next;

        }

        Node p = null;
        if (node1 == null){
            p = node2;
        }
        if (node2 == null){
            p = node1;
        }

        while(p != null){
            int temp = p.val + flag;
            flag = temp / 10;
            Node node = new Node(temp % 10);

            if (head == null){
                head = node;
                tail = node;
            }else {
                tail.next = node;
                tail = tail.next;
            }

            p = p.next;
        }

        if (flag > 0){
            Node node = new Node(flag);

            if (head == null){
                head = node;
                tail = node;
            }else {
                tail.next = node;
                tail = tail.next;
            }
        }

        return head;

    }


    private Node toNodeList(String a){
        Node head = null;
        Node tail = null;
        for(int i = a.length() - 1; i >= 0; i--){


            Node node = new Node( a.charAt(i) - '0');

            if (head == null){
                head = node;
                tail = node;
            }else {
                tail.next = node;
                tail = tail.next;
            }


        }

        return head;

    }

    private static class Node{
        int val;
        Node next;

        Node(int val){
            this.val = val;
        }
    }



    private String multiply(String num1, char c){
        int  flag = 0;

        StringBuilder sb = new StringBuilder();


        for(int i = num1.length() - 1; i >= 0; i--){
            int  a = num1.charAt(i) - '0';
            int b = c - '0';

            int  result = a * b + flag;

            flag = result / 10;

            sb.append((char)('0' + result % 10));
        }

        if (flag != 0){
            sb.append((char)('0' + flag));
        }

        return sb.reverse().toString();
    }


}
