package com.gaku.algorithm.tree.trie;

/**
 * 前缀树（字典树、单词查找树）
 */
public class Trie {


    private TrieNode root;

    public Trie(){
        this.root = new TrieNode();
    }

    public void insert(String word){
        if (word == null || word.isEmpty()){
            return;
        }

        TrieNode node = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);

            if (!node.contains(c)){
                node.put(c);
            }
            node = node.get(c);
        }
        node.setEnd(true);

    }

    public boolean search(String word){
        TrieNode node = searchPrefix(word);
        return  (node != null) && node.isEnd();
    }

    public boolean startWith(String prefix){
        TrieNode node = searchPrefix(prefix);
        return  node != null;

    }

    private TrieNode searchPrefix(String prefix){

        if (prefix == null || prefix.isEmpty()){
            return null;
        }

        TrieNode node = root;
        for(int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if (node.contains(c)){
                node = node.get(c);

            }else{
                return null;
            }
        }

        return node;


    }

    private static class TrieNode{

        private static final int SIZE = 26;

        private boolean end;
        private TrieNode[] linkes;

        public TrieNode() {
            this.end = false;
            this.linkes = new TrieNode[SIZE];
        }

        public boolean contains(char c){
            return this.linkes[c - 'a'] != null;
        }

        public TrieNode get(char c){
            return this.linkes[c - 'a'];
        }

        public void put(char c){
            this.linkes[c - 'a'] = new TrieNode();
        }

        public boolean isEnd() {
            return end;
        }

        public void setEnd(boolean end) {
            this.end = end;
        }
    }
}
