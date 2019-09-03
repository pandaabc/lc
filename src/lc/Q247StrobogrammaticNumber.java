package lc;

import java.util.*;

public class Q247StrobogrammaticNumber {

    public List<String> findStrobogrammatic(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return new ArrayList<>();
        }
        if (n == 1) {
            return Arrays.asList(new String[]{"0","1","8"});
        }
        if (n == 2) {
            return Arrays.asList(new String[]{"11","69","88","96"});
        }
        Map<Integer, List<String>> numMap = new HashMap<>();
        numMap.put(1, Arrays.asList(new String[]{"0","1","8"}));
        numMap.put(2, Arrays.asList(new String[]{"00", "11","69","88","96"}));
        // add 0, 1, 6, 8, 9 to it (if it is not n)
        for (int i = 3; i <= n; i ++) {

            List<String> l = numMap.get(i - 2);
            List<String> nl = new ArrayList<>();
            for (int j = 0; j < l.size(); j ++) {
                String s = l.get(j);
                nl.add(append(s, '6'));
                nl.add(append(s, '9'));
                nl.add(append(s, '1'));
                nl.add(append(s, '8'));
                if (i != n) {
                    nl.add(append(s, '0'));
                }
            }
            numMap.put(i, nl);
        }
        return numMap.get(n);
    }

    private String append(String a, char c) {

        if (c == '6') {
            return c + a + '9';
        } else if (c == '9') {
            return c + a + '6';
        } else {
            return c + a + c;
        }

    }

}
