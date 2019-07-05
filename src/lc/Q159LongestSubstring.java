package lc;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class Q159LongestSubstring {

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> characterCountMap = new HashMap<>();
        int idx0 = 0;
        int maxLen = 0;

        for (int i = 0; i < s.length(); i ++) {

            characterCountMap.compute(s.charAt(i), (k, v) -> v == null ? 1 : v + 1);
            while (characterCountMap.size() > 2) {
                characterCountMap.compute(s.charAt(idx0), (k, v) -> v - 1);
                if (characterCountMap.get(s.charAt(idx0)) == 0) {
                    characterCountMap.remove(s.charAt(idx0));
                }
                idx0 ++;
            }

            maxLen = Math.max(maxLen, characterCountMap.values().stream().reduce(0, (a, b) -> a + b));

        }

        return maxLen;

    }

}
