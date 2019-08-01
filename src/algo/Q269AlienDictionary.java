package algo;

import java.util.HashSet;
import java.util.Set;

public class Q269AlienDictionary {

    public String alienOrder(String[] words) {
        if (words ==  null || words.length == 0) {
            return "";
        }

        Set<Character>[] pres = new HashSet[26];
        Set<Character> allC = new HashSet<>();

        for (int i = 0; i < words.length; i ++) {
            construct(i == 0 ? null : words[i - 1], words[i], pres, allC);
        }

        StringBuilder sb = new StringBuilder();

        while (!allC.isEmpty()) {
            boolean found = false;
            for (char c : allC) {
                if (pres[c - 'a'] == null || pres[c - 'a'].isEmpty()) {
                    sb.append(c);
                    for (char c1 : allC) {
                        if (pres[c1 - 'a'] != null) {
                            pres[c1 - 'a'].remove(c);
                        }
                    }
                    allC.remove(c);
                    found = true;
                    break;
                }
            }
            if (!found) {
                return "";
            }
        }

        return sb.toString();

    }

    private void construct(String pre, String cur, Set<Character>[] pres, Set<Character> allC) {

        for (int i = 1; i < cur.length(); i ++) {
            char c = cur.charAt(i);
            char p = cur.charAt(i - 1);
            if(pres[c - 'a'] == null) {
                pres[c - 'a'] = new HashSet<>();
            }
            pres[c - 'a'].add(p);
            allC.add(c);
            allC.add(p);
        }

        if (pre != null) {
            for (int i = 0; i < pre.length() && i < cur.length(); i ++) {
                char c = cur.charAt(i);
                char p = pre.charAt(i);
                if (c != p) {
                    if(pres[c - 'a'] == null) {
                        pres[c - 'a'] = new HashSet<>();
                    }
                    pres[c - 'a'].add(p);
                    break;
                }
            }
        }

    }

}
