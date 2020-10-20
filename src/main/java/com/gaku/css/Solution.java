package com.gaku.css;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 解析xml
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();

        Tokenizer tokenizer = new MyTokenizer();

        TreeNode treeNode = solution.buildXml1(tokenizer);

        System.out.println(treeNode);


    }

    public TreeNode buildXml1(Tokenizer tokenizer){

        Token token = tokenizer.nextToken();
        if (token == null){
            return null;
        }
        TreeNode root = new TreeNode(token);
        dfs(root, tokenizer);
        return root;
    }

    private void dfs(TreeNode node , Tokenizer tokenizer){
        Token token = tokenizer.nextToken();

        if (token == null){
            return;
        }

        TokenType type = token.type();

        if (TokenType.BEGIN.equals(type)){
            TreeNode child = new TreeNode(token);
            node.addChild(child);
            dfs(child, tokenizer);
        } else {
            if (TokenType.TEXT.equals(type)) {
                TreeNode child = new TreeNode(token);
                node.addChild(child);
                // 前进一步
                Token token1 = tokenizer.nextToken();
                return ;
            }else{
                return;
            }
        }

        dfs(node, tokenizer);// <-----------差这行代码
    }




    public TreeNode buildXml(Tokenizer tokenizer){

        TreeNode root = null;

        Stack<TreeNode> stack = new Stack();

        while (tokenizer.nextToken() != null){
            Token token = tokenizer.nextToken();

            switch (token.type()){
                case BEGIN:
                    if (stack.isEmpty()){
                        TreeNode newNode = new TreeNode(token);
                        if (root == null){
                            root = newNode;
                        }
                        stack.push(newNode);
                    }else{
                        TreeNode newNode = new TreeNode(token);
                        TreeNode topNode = stack.peek();
                        topNode.addChild(newNode);
                        stack.push(newNode);
                    }
                    break;

                case TEXT:
                    TreeNode newNode = new TreeNode(token);
                    if (stack.isEmpty()){
                        throw new IllegalStateException("xml format wrong");
                    }else {
                        TreeNode topNode = stack.peek();
                        topNode.addChild(newNode);
                    }
                    break;

                case END:
                    if (stack.isEmpty()){
                        throw new IllegalStateException("xml format wrong");
                    }else{
                        stack.pop();
                    }
                    break;
            }
        }

        if (!stack.isEmpty()){
            throw new IllegalStateException("xml format wrong");
        }

        return root;

    }

    static class TreeNode{

        private Token token;

        List<TreeNode> children = new LinkedList<>();

        public TreeNode(Token token){
            this.token = token;
        }

        public void addChild(TreeNode node){
            this.children.add(node);
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "token=" + token +
                    ", children=" + children +
                    '}';
        }
    }
}
