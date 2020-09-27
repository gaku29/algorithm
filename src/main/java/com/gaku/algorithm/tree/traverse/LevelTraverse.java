package com.gaku.algorithm.tree.traverse;

import java.util.*;

public class LevelTraverse {


    static  class TreeNode{
        String val;
        TreeNode left;
        TreeNode right;

        public TreeNode(String val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {

        TreeNode a = new TreeNode("A");
        TreeNode b = new TreeNode("B");
        TreeNode c = new TreeNode("C");
        TreeNode d = new TreeNode("D");
        TreeNode e = new TreeNode("E");
        TreeNode f = new TreeNode("F");

        a.left = b;
        a.right =c;
        b.left = d;
        b.right = e;
        c.left = f;

        System.out.println(traverse(a));
        System.out.println(traverseByLevel(a));



    }



    static  List<String> traverse(TreeNode root){
        if (root == null){
            return new ArrayList<String>();
        }

        List<String> ret = new LinkedList<String>();

        Queue<TreeNode> queue =  new LinkedList<TreeNode>();
        queue.add(root);

        while (!queue.isEmpty()){
            TreeNode node = queue.poll();

            if (node.left != null){
                queue.offer(node.left);
            }

            if(node.right != null){
                queue.offer(node.right);
            }


            ret.add(node.val);
        }

        return ret;

    }

    static List<List<String>> traverseByLevel(TreeNode root){
        if (root == null){
            return new ArrayList<List<String>>();
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        queue.offer(null);

        List<List<String>> ret = new LinkedList<List<String>>();
        List<String> one = new ArrayList<String>();

        while (!queue.isEmpty()){

            TreeNode node = queue.poll();

            if (node == null){

                ret.add(one);
                one = new ArrayList<String>();
                if (!queue.isEmpty()){
                    queue.add(null);
                }

            }else {
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }

                one.add(node.val);
            }



        }

        return ret;
    }
}
