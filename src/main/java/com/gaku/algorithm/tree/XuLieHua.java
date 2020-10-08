package com.gaku.algorithm.tree;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的序列化和反序列化
 * https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
 */
public class XuLieHua {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(6);

        XuLieHua instance = new XuLieHua();
        String s = instance.serialize(root);
        System.out.println(s);

        TreeNode node = instance.deserialize(s);

        System.out.println("");
    }

    public String serialize(TreeNode root){
        List<String> result = change2List(root);
        return result.toString();
    }

    private List<String> change2List(TreeNode root) {
        if (root == null){
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if (node != null){
                result.add(String.valueOf(node.val));
                queue.offer(node.left);
                queue.offer(node.right);
            }else {
                result.add(null);
            }
        }
        return result;
    }

    public TreeNode deserialize(String data){
        List<String> dataList = convert(data);

        if (dataList.isEmpty()){
            return null;
        }

        String first = dataList.remove(0);
        TreeNode root = new TreeNode(Integer.valueOf(first.trim()));

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            TreeNode node = queue.poll();

            int size = dataList.size();

            if (size == 0){

            }else if (size == 1){
                String temp = dataList.remove(0);
                if (!temp.equals("null")){
                    node.left = new TreeNode(Integer.valueOf(temp));
                    queue.offer(node.left);
                }
            }else {
                String temp = dataList.remove(0);
                if (!temp.equals("null")){
                    node.left = new TreeNode(Integer.valueOf(temp));
                    queue.offer(node.left);
                }

                temp = dataList.remove(0);
                if (!temp.equals("null")){
                    node.right = new TreeNode(Integer.valueOf(temp));
                    queue.offer(node.right);
                }
            }
        }


        return root;

    }

    private List<String> convert(String data) {
        if (data == null || data.length() <= 2){
            return new ArrayList<>();
        }

        data = data.substring(1, data.length() - 1);
        String[] dataArray = data.split(",");

        List<String> ret = new LinkedList<>();
        for(String one : dataArray){
            ret.add(one.trim());
        }
        return ret;
    }
}
