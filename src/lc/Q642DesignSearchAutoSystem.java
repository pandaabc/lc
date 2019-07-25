package lc;

import java.util.*;
import java.util.stream.Collectors;

public class Q642DesignSearchAutoSystem {

    class AutocompleteSystem {

        LocTrieNode head;
        LocTrieNode curNode;
        StringBuilder sb = new StringBuilder();
        int size = 3;

        public AutocompleteSystem(String[] sentences, int[] times) {
            head = new LocTrieNode('a');
            for (int i = 0; i < sentences.length; i ++){
                setUpTrieNodes(head, sentences[i], 0, times[i]);
            }
            curNode = head;
        }

        public List<String> input(char c) {
            if (c == '#') {
                curNode.setLeaf(true);
                curNode.setWord(sb.toString());
                curNode.setCnt(curNode.getCnt() + 1);
                sb = new StringBuilder();
                curNode = head;
                return new ArrayList<>();
            }
            sb.append(c);
            LocTrieNode child = getChildNode(curNode, c);
            curNode = child;
            PriorityQueue<LocTrieNode> queue = new PriorityQueue<>(locComparator());
            getAllWordsInRank(child, queue);

            List<String> res = new ArrayList<>();
            while (!queue.isEmpty()) {
                res.add(queue.poll().getWord());
            }
            for (int i = 0; i < res.size()/2; i ++) {
                String tmp = res.get(i);
                res.set(i, res.get(res.size() - i - 1));
                res.set(res.size() - i - 1, tmp);
            }
            return res;
        }

        public void setUpTrieNodes(LocTrieNode parent, String word, int i, int cntChange) {

            if (i == word.length()) {
                parent.setLeaf(true);
                parent.setWord(word);
                parent.setCnt(parent.getCnt() + cntChange);
                return;
            }
            if (parent.getChildrend() == null) {
                parent.setChildrend(new LocTrieNode[27]);
            }

            char c = word.charAt(i);
            LocTrieNode child = getChildNode(parent, c);
            setUpTrieNodes(child, word, i + 1, cntChange);
        }

        private LocTrieNode getChildNode(LocTrieNode parent, char c) {
            //System.out.println(parent + " " + c);
            int idx = c == ' ' ? 26 : c - 'a';
            LocTrieNode child = parent.getChildrend()[idx];
            if (child == null) {
                child = new LocTrieNode(c);
                parent.getChildrend()[idx] = child;
            }
            return child;
        }

        private void getAllWordsInRank (LocTrieNode node, PriorityQueue<LocTrieNode> queue) {
            //System.out.println("cur: " + node.c + "; word: " + node.word);
            if (node.isLeaf) {
                queue.offer(node);
                //System.out.println("put word:" + node.word);
                //System.out.println("top word:" + queue.peek().getWord());
                if (queue.size() > size) {
                    queue.poll();
                }
            }

            for (int i = 0; i < node.childrend.length; i ++) {
                if (node.getChildrend()[i] != null) {
                    getAllWordsInRank(node.getChildrend()[i], queue);
                }
            }

        }

        private Comparator<LocTrieNode> locComparator() {
            return (e1, e2) -> {
                int res = Integer.compare(e1.getCnt(), e2.getCnt());
                //System.out.println(e1 + ", " + e2 + ", " + res);
                if (res == 0) {
                    String s2 = e1.getWord();
                    String s1 = e2.getWord();
                    return s1.compareTo(s2);
                }
                return res;
            };
        }

    }

}

class LocTrieNode {
    LocTrieNode[] childrend;
    char c;
    boolean isLeaf;
    String word;
    int cnt;
    public LocTrieNode(char c){
        this.c =  c;
    }
    public LocTrieNode[] getChildrend() {
        return childrend;
    }

    public void setChildrend(LocTrieNode[] childrend) {
        this.childrend = childrend;
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
}
