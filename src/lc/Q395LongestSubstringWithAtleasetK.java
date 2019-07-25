package lc;

import java.util.*;
import java.util.stream.Collectors;

public class Q395LongestSubstringWithAtleasetK {
    public int longestSubstring(String s, int k) {
        Map<Character, Integer> cnt = new HashMap<>();
        Map<Character, List<Integer>> idx = new HashMap<>();
        for (int i = 0; i < s.length(); i ++) {
            cnt.compute(s.charAt(i), (key, val) -> val == null ? 1 : val + 1);
            idx.computeIfAbsent(s.charAt(i), key -> new ArrayList<>()).add(i);
        }
        List<Integer> allIdx = idx.entrySet().stream().filter(entry -> cnt.get(entry.getKey()) < k).map(entry -> entry.getValue()).flatMap(List::stream).collect(Collectors.toList());
        if (allIdx.isEmpty()) {
            return s.length();
        }
        if (allIdx.size() == s.length()) {
            return 0;
        }
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < allIdx.size(); i ++) {
            int start = i == 0 ? 0 : allIdx.get(i - 1) + 1;
            if (allIdx.get(i) > start) {
                results.add(longestSubstring(s.substring(start, allIdx.get(i)), k));
            }
        }
        if (allIdx.get(allIdx.size() - 1) + 1 < s.length()) {
            results.add(longestSubstring(s.substring(allIdx.get(allIdx.size() - 1) + 1), k));
        }
        return results.stream().mapToInt(e -> e).max().orElse(0);
    }
}
