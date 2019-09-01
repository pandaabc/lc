package lc;

import java.util.*;

public class Q1143LongestCommonSubsequence {

    public int longestCommonSubsequence(String text1, String text2) {
        Map<Character, List<Integer>> charLoc = new HashMap<>();
        TreeMap<Integer, Integer> sequenceMap = new TreeMap<>();
        for (int i = 0; i < text2.length(); i ++) {
            charLoc.computeIfAbsent(text2.charAt(i), k -> new ArrayList<>()).add(i);
        }

        for (int i = 0; i < text1.length(); i ++) {

            char c = text1.charAt(i);
            List<Integer> idx = charLoc.get(c);
            if (idx == null) {
                continue;
            }
            for (int j = 0; j < idx.size(); j ++) {
                int id = idx.get(j);
                Map.Entry<Integer, Integer> lastEntry = sequenceMap.lowerEntry(id);
                int cur = lastEntry == null ? 1 : lastEntry.getValue() + 1;
                int pre = sequenceMap.containsKey(id) ? sequenceMap.get(id) : 0;
                sequenceMap.put(id, Math.max(cur, pre));
            }

        }

        return sequenceMap.values().stream().max(Comparator.naturalOrder()).orElse(-1);
    }
}
