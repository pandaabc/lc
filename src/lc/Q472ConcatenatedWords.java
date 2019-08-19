package lc;

import java.util.*;

public class Q472ConcatenatedWords {

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        if (words == null || words.length <= 1) {
            return new ArrayList<>();
        }
        Arrays.sort(words);
        TrieNodeV2 dummy = new TrieNodeV2('a');
        for (int i = 0; i < words.length; i ++) {
            buildTrieNodes(dummy, words[i], 0);
        }
        Set<String> buildableWords = new HashSet<>();
        List<String> res = new ArrayList<>();
        for (int i = 0; i < words.length; i ++) {
            buildableWords.remove(words[i]);
            if (isConcatable(words[i], dummy, buildableWords, words[i])) {
                res.add(words[i]);
            }
        }
        return res;
    }

    private boolean isConcatable (String word, TrieNodeV2 dummy, Set<String> buildableWords, String origin) {

        if (buildableWords.contains(word)) {
            return true;
        }

        TrieNodeV2 node = dummy;
        for (int i = 0; i < word.length(); i ++ ){
            char c = word.charAt(i);
            node = node.getChildren()[c - 'a'];
            if (node == null) {
                return false;
            }
            if (node.isLeaf() && i != word.length() - 1) {
                boolean valid = isConcatable(word.substring(i + 1), dummy, buildableWords, origin);
                if (valid) {
                    buildableWords.add(word);
                    return true;
                }
            } else if (node.isLeaf()  && i == word.length() - 1 && !origin.equals(node.getWord())) {
                buildableWords.add(word);
                return true;
            }
        }
        return false;

    }

    private void buildTrieNodes(TrieNodeV2 p, String word, int i) {
        if (i == word.length()) {
            p.setLeaf(true);
            p.setWord(word);
            return;
        }
        char c = word.charAt(i);
        if (p.getChildren()[c - 'a'] == null) {
            p.getChildren()[c - 'a'] = new TrieNodeV2(c);
        }
        buildTrieNodes(p.getChildren()[c - 'a'], word, i + 1);
    }

}
