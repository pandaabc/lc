package algo;

import lc.TrieNodeV2;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Q139WorkBreakI_Trie_DP {

    public boolean wordBreakWithTrie(String s, List<String> wordDict) {

        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return false;
        }

        TrieNodeV2 dummy = new TrieNodeV2('a');
        wordDict.forEach(e -> constructTrieNodes(dummy, e, 0));

        boolean[] wordMark = new boolean[s.length()];

        for (int i = 0; i < s.length(); i ++) {
            if (i == 0 || i > 0 && wordMark[i - 1]) {
                for (int j = i + 1; j <= s.length(); j ++) {
                    TrieNodeV2 node = dummy;
                    char thisC = s.charAt(j);
                    while (node.getChildren()[thisC - 'a'] != null) {
                        node = node.getChildren()[thisC - 'a'];
                        if (node.isLeaf()) {
                            wordMark[j] = true;
                        }
                    }
                }
            }
        }

        return wordMark[s.length() - 1];
    }

    private void constructTrieNodes(TrieNodeV2 parentNode, String word, int i) {
        if (i == word.length()) {
            parentNode.setLeaf(true);
            parentNode.setWord(word);
            return;
        }
        char c = word.charAt(i);
        if (parentNode.getChildren()[c - 'a'] == null) {
            parentNode.getChildren()[c - 'a'] = new TrieNodeV2(c);
        }
        constructTrieNodes(parentNode.getChildren()[c - 'a'], word, i + 1);
    }

    public boolean wordBreakWithDP(String s, List<String> wordDict) {

        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return false;
        }
        Set<String> wordSet = wordDict.stream().collect(Collectors.toSet());

        boolean[] wordMark = new boolean[s.length()];

        for (int i = 0; i < s.length(); i ++) {
            if (i == 0 || i > 0 && wordMark[i - 1]) {
                for (int j = i + 1; j <= s.length(); j ++) {
                    if (wordSet.contains(s.substring(i, j))) {
                        //System.out.println(s.substring(i,j));
                        wordMark[j - 1] = true;
                    }
                }
            }
        }

        return wordMark[s.length() - 1];
    }

}
