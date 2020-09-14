package com.gaku.algorithm.tree.trie;

import org.junit.Test;

import static org.junit.Assert.*;


public class TrieTest {

    @Test
    public void insert() {
    }

    @Test
    public void search() {

        Trie trie = new Trie();
        trie.insert("hello");
        assertTrue(trie.search("hello"));
        assertTrue(trie.startWith("hello"));
        assertTrue(trie.startWith("hell"));
        assertFalse(trie.startWith("hellr"));


    }
}