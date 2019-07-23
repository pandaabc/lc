package lc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q10RegularExpressionMatch {


    public boolean isMatchV2(String s, String p) {

        Boolean[][] matched = new Boolean[s.length() + 1][p.length() + 1];

        return dfs(s, p, 0, 0, matched);

    }

    private boolean dfs(String s, String p, int i, int j, Boolean[][] matched) {

        //System.out.println("i:" + i + "; j:" + j + " - " + Arrays.deepToString(matched));

        if (matched[i][j] != null) {
            return matched[i][j];
        }

        if (s.isEmpty() && p.isEmpty()) {
            matched[i][j] = true;
            return true;
        }

        if (p.isEmpty() && !s.isEmpty()) {
            matched[i][j] = false;
            return false;
        }

        boolean ans = false;
        if (p.charAt(0) == '.' || !s.isEmpty() && s.charAt(0) == p.charAt(0)) {

            if (p.length() > 1 && p.charAt(1) == '*') {
                ans = dfs(s, p.substring(2), i, j + 2, matched) ||
                        !s.isEmpty() && (dfs(s.substring(1), p, i + 1, j, matched) || dfs(s.substring(1), p.substring(2), i + 1, j + 2, matched));
            } else {
                ans = !s.isEmpty() && dfs(s.substring(1), p.substring(1), i + 1, j + 1, matched);
            }

        } else {

            if (p.length() > 1 && p.charAt(1) == '*') {
                ans = dfs(s, p.substring(2), i, j + 2, matched);
            } else {
                ans = false;
            }

        }

        matched[i][j] = ans;

        return ans;
    }



    public boolean isMatch(String s, String p) {
        List<String>  ps = new ArrayList<>();
        Set<String> tested = new HashSet<>();
        tested.add(p);
        ps.add(p);

        for (int i = 0; i < s.length(); i ++) {
            //System.out.println(i + ": " + ps);
            List<String> ps0 = new ArrayList<>();
            if (ps.size() == 0) {
                return false;
            }
            char c = s.charAt(i);

            for (int j = 0; j < ps.size(); j ++) {
                String p0 = ps.get(j);
                if ("".equals(p0)) {
                    continue;
                }
                if (c == p0.charAt(0) || p0.charAt(0) == '.') {
                    if (p0.length() > 1 && p0.charAt(1) == '*') {
                        ps0.add(p0.substring(2));
                        ps0.add(p0);
                    } else {
                        ps0.add(p0.substring(1));
                    }
                }
                if (p0.length() > 1 && p0.charAt(1) == '*' && !tested.contains(p0.substring(2))) {
                    ps.add(p0.substring(2));
                    tested.add(p0.substring(2));
                }
            }

            if (ps0.size() == 0) {
                return false;
            }
            tested.clear();
            tested.addAll(ps0);
            ps = ps0;
        }

        return ps.stream().map(e -> removeStar(e)).anyMatch(e -> "".equals(e));

    }

    private String removeStar(String p) {
        while (p.length() > 1 && p.charAt(1) == '*') {
            p = p.substring(2);
        }
        return p;
    }

}
