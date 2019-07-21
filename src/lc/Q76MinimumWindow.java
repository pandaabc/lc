package lc;

import java.util.HashMap;
import java.util.Map;

public class Q76MinimumWindow {

    public String minWindow(String s, String t) {
        if (s == null || t == null || s.isEmpty() || t.isEmpty()) {
            return "";
        }

        Map<Character, Integer> tCharCount = new HashMap<>();
        Map<Character, Integer> sCount = new HashMap<>();

        for (int i = 0; i < t.length(); i ++) {
            tCharCount.compute(t.charAt(i), (k, v) -> v == null ? 1 : v + 1);
            sCount.computeIfAbsent(t.charAt(i), k -> 0);
        }

        int matched = 0;
        int p0 = 0;
        String res = null;

        for (int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if (sCount.containsKey(c)) {
                int cCnt = sCount.compute(c, (k, v) -> v + 1);
                if (cCnt == tCharCount.get(c)) {
                    matched ++;
                }
                if (matched == tCharCount.size()) {
                    if (res == null || i - p0 + 1 < res.length()) {
                        res = s.substring(p0, i + 1);
                    }
                    while (p0 < i && matched == tCharCount.size()) {
                        char p = s.charAt(p0);
                        if (sCount.containsKey(p)) {
                            int pCnt = sCount.compute(p, (k, v) -> v - 1);
                            if (pCnt == tCharCount.get(p) - 1) {
                                matched --;
                            }
                        }

                        if (matched == tCharCount.size() && i - p0 < res.length()){
                            res = s.substring(p0 + 1, i + 1);
                        }
                        p0 ++;
                    }
                }
            }

        }

        return res;
    }

}
