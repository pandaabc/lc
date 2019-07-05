package lc.fail;

import java.util.*;

/**
 *
 * The logic is too complecated and may not work.
 *
 */
public class Q316 {

    public String removeDuplicateLetters(String s) {
        SortedMap<Character, List<Integer>> map = new TreeMap<>();
        for (int i = 0; i < s.length(); i ++) {
            map.computeIfAbsent(s.charAt(i), k -> new ArrayList<>()).add(i);
        }
        SortedMap<Integer, Character> indexMap = new TreeMap<>();
        int curIndex = -1;
        for (Map.Entry<Character, List<Integer>> entry : map.entrySet()) {
            if (entry.getValue() == null) {
                continue;
            }
            int i = Collections.binarySearch(entry.getValue(), curIndex);
            int index = entry.getValue().get(-i-1);
            curIndex = index;
            entry.setValue(null);
            indexMap.put(index, entry.getKey());
            determineIndexBeforeCur(map, index, entry.getKey(), indexMap);
        }
        return indexMap.values().stream().map(e -> e.toString()).reduce("", (x, y) -> x + y);
    }

    public void determineIndexBeforeCur(SortedMap<Character, List<Integer>> map, int index, char c, SortedMap<Integer, Character> indexMap) {

        map.subMap(c, (char) ('z'+1)).entrySet().stream().filter(e -> e.getValue() != null).forEach(
                e -> {
                    if (e.getValue().get(0) > index
                            || e.getValue().get(0) < index && e.getValue().get(e.getValue().size() - 1) > index) {
                        return;
                    }
                    // if all the index is smaller than the current index, then we need to set it.
                    indexMap.put(e.getValue().get(0), e.getKey());
                    e.setValue(null);
                    determineIndexBeforeCur(map, index, e.getKey(), indexMap);
                }
        );

    }
}
