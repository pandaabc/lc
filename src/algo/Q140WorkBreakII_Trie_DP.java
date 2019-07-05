package algo;

import lc.ListNode;

import java.util.*;
import java.util.stream.Collectors;

public class Q140WorkBreakII_Trie_DP {

    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();

        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return res;
        }

        Map<String, List<String>> wordsByString = new HashMap<>();
        Set<String> wordSet = wordDict.stream().collect(Collectors.toSet());
        return searchWords(s, wordsByString, wordSet);
    }

    private List<String> searchWords(String s, Map<String, List<String>> map, Set<String> words) {

        List<String> res = new ArrayList<>();
        for (String word : words) {
            if (s.equals(word)) {
                res.add(word);
            } else if (s.startsWith(word)) {

                String post = s.substring(word.length());
                List<String> list = null;
                if (map.containsKey(post)) {
                    list = map.get(post);
                } else {
                    list = searchWords(s.substring(word.length()), map, words);
                    map.put(s.substring(word.length()), list);
                }
                res.addAll(list.stream().map(e -> word + " " + e).collect(Collectors.toSet()));

            }
        }

        return res;

    }

}
