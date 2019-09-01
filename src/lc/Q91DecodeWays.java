package lc;

public class Q91DecodeWays {
    public int numDecodings(String s) {

        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }

        int[] combinations = new int[s.length()];
        combinations[0] = 1;
        for (int i = 1; i < s.length(); i ++) {
            int pre = Integer.parseInt(s.substring(i - 1, i + 1));
            if (pre <= 26 && pre > 0) {
                int cntBefore2 = i - 2 >= 0 ? combinations[i - 2] : 1;
                combinations[i] = (s.charAt(i) == '0' || s.charAt(i - 1) == '0' ? 0 : combinations[i - 1]) + cntBefore2;
            } else if (s.charAt(i) == '0'){
                return 0;
            } else {
                combinations[i] = combinations[i - 1];
            }
        }
        return combinations[s.length() - 1];
    }
}
