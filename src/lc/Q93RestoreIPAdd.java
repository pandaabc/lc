package lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q93RestoreIPAdd {

    public List<String> restoreIpAddresses(String s) {
        if (s == null || s.length() < 4 || s.length() > 12) {
            return new ArrayList<>();
        }
        return restoreIP(s, 0, 4, new HashMap<>(), new HashMap<>());
    }

    private List<String> restoreIP(String s, int i, int cnt, Map<String, List<String>> tried, Map<String, Boolean> validMap) {
        List<String> res = new ArrayList<>();
        String key = i + "-" + cnt;
        if (tried.containsKey(key)) {
            return tried.get(key);
        }
        if (i >= s.length()) {
            return res;
        }
        if (cnt == 1) {
            String num = s.substring(i);
            if (validMap.computeIfAbsent(num, k -> isValid(k))) {
                res.add(num);
            }
            tried.put(key, res);
            return res;
        }

        for (int i0 = i + 1; i0 <= s.length() && i0 - i <= 3; i0 ++) {
            String prefix = s.substring(i, i0);
            if (validMap.computeIfAbsent(prefix, k -> isValid(k))) {
                List<String> post = restoreIP(s, i0, cnt - 1, tried, validMap);
                if (!post.isEmpty()) {
                    post.stream().forEach(e -> res.add(prefix + "." + e));
                }
            }
        }
        tried.put(key, res);

        return res;
    }

    private boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        int val = Integer.valueOf(s);
        if (val > 255 || s.length() > 1 && s.charAt(0) == '0') {
            return false;
        }
        return true;
    }

}
