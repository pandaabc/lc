package lc;

public class TrieNodeV2 {
    private TrieNodeV2[] children = new TrieNodeV2[26];
    private boolean isLeaf;
    private char c;
    private String word;

    public TrieNodeV2(char c) {
        this.c = c;
    }

    public TrieNodeV2[] getChildren() {
        return children;
    }

    public void setChildren(TrieNodeV2[] children) {
        this.children = children;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

}
