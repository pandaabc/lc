package lc;

import java.util.*;

public class Q438FindAlAnagramsInAStr {

    public List<Integer> findAnagrams(String s, String p) {

        List<Integer> res = new ArrayList<>();
        if (s == null || s.isEmpty()) {
            return res;
        }
        Map<Character, Integer> cntMap = new HashMap<>();
        for (int i = 0; i < p.length(); i ++) {
            cntMap.compute(p.charAt(i), (k, v) -> v == null ? 1 : v + 1);
        }
        int notInPs = 0;
        int pL = p.length();
        int nonZc = cntMap.size();
        for (int i = 0; i < s.length(); i ++) {

            char c = s.charAt(i);
            if (cntMap.containsKey(c)) {
                int val = cntMap.compute(c, (k, v) -> v - 1);
                if (val == 0) {
                    nonZc --;
                } else if (val == - 1) {
                    nonZc ++;
                }
            } else {
                notInPs ++;
            }
            if (i >= pL) {
                char c1 = (char)s.charAt(i - pL);
                if (cntMap.containsKey(c1)) {
                    int val = cntMap.compute(c1, (k, v) -> v + 1);
                    if (val == 0) {
                        nonZc --;
                    } else if (val == 1){
                        nonZc ++;
                    }
                } else {
                    //System.out.println(c1 + " " + i);
                    notInPs --;
                }
            }
            if (i >= pL - 1 && notInPs == 0 && nonZc == 0) {
                res.add(i - pL + 1);
            }
        }
        return res;
    }
}
