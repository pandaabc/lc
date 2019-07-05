package lc;

import java.util.Map;

class TrieNode {
    private Map<Character, TrieNode> childTrieNodes;
    private boolean isLeaf;
    private char c;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    private String word;

    public TrieNode(char c) {
        this.c = c;
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public Map<Character, TrieNode> getChildTrieNodes() {
        return childTrieNodes;
    }

    public void setChildTrieNodes(Map<Character, TrieNode> childTrieNodes) {
        this.childTrieNodes = childTrieNodes;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }


}
