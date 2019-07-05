package lc;

import java.util.*;

public class Q1048LongestStringChain {

    public int longestStrChain(String[] words) {
        Map<Integer, Set<String>> wordsByLength = new HashMap<>();
        for (String word : words) {
            wordsByLength.computeIfAbsent(word.length(), k -> new HashSet<>()).add(word);
        }
        System.out.println(wordsByLength);
        Map<String, Integer> maxLength = new HashMap<>();
        if (wordsByLength.get(1) != null) {
            wordsByLength.get(1).forEach(e -> maxLength.computeIfAbsent(e, k -> 1));
        }
        //System.out.println(maxLength);
        for (int i = 2; i <= 16; i ++) {
            Set<String> sortedWords = wordsByLength.get(i);
            if (sortedWords != null) {
                for (String str : sortedWords) {
                    int cnt = 1;
                    for (int j = 0; j < str.length(); j ++) {
                        String cur = str.substring(0, j) + str.substring(j + 1);
                        cnt = Math.max(cnt, maxLength.get(cur) == null ? 1 : maxLength.get(cur) + 1);
                    }
                    maxLength.put(str, cnt);
                }
            }
            //System.out.println(maxLength);
        }
        return maxLength.values().stream().max(Comparator.naturalOrder()).orElse(0);
    }


}
