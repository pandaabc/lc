package lc;

import java.util.*;

public class Q97InterleavingString_DP_BT {

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        if (s1.length() == 0) {
            return s2.equals(s3);
        }
        if (s2.length() == 0) {
            return s1.equals(s3);
        }
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        return isInterLeaveBT(s1, s2, s3, -1, -1, new HashMap<>());
    }

    public boolean isInterLeaveBT(String s1, String s2, String s3, int i1, int i2, Map<String, Boolean> tried) {
        String key = i1 + "-" + i2;
        //System.out.println(key);
        if (tried.containsKey(key)) {
            return tried.get(key);
        }
        if (i1 + 1 < s1.length() && s1.charAt(i1 + 1) == s3.charAt(i1 + i2 + 2)) {
            boolean valid = isInterLeaveBT(s1, s2, s3, i1 + 1, i2, tried);
            tried.put((i1 + 1) + "-" + i2, valid);
            if (valid) {
                return true;
            }
        }
        if (i2 + 1 < s2.length() && s2.charAt(i2 + 1) == s3.charAt(i1 + i2 + 2)) {
            boolean valid = isInterLeaveBT(s1, s2, s3, i1, i2 + 1, tried);
            tried.put(i1 + "-" + (i2 + 1), valid);
            if (valid) {
                return true;
            }
        }
        return i1 + i2 + 2 == s3.length();
    }

    public boolean isInterleaveTLE(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        if (s1.length() == 0) {
            return s2.equals(s3);
        }
        if (s2.length() == 0) {
            return s1.equals(s3);
        }
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        Queue<int[]> possibleList = new LinkedList<>();
        if (s1.charAt(0) == s3.charAt(0)) {
            possibleList.add(new int[]{0, -1});
        }
        if (s2.charAt(0) == s3.charAt(0)) {
            possibleList.add(new int[]{-1, 0});
        }
        for (int i = 1; i < s3.length(); i ++) {
            int size = possibleList.size();
            if (size == 0) {
                return false;
            }
            while (size > 0) {
                int[] localPos = possibleList.poll();
                if (localPos[0] + 1 < s1.length() && s1.charAt(localPos[0] + 1) == s3.charAt(i)) {
                    possibleList.add(new int[]{localPos[0] + 1, localPos[1]});
                }
                if (localPos[1] + 1 < s2.length() && s2.charAt(localPos[1] + 1) == s3.charAt(i)) {
                    possibleList.add(new int[]{localPos[0], localPos[1] + 1});
                }
            }
        }
        return true;
    }

}
