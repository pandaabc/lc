package lc;

import java.util.Arrays;

public class Q294FlipGameII {

    public boolean canWin(String s) {
        return canWin(s.toCharArray(), true);
    }

    private boolean canWin(char[] chars, boolean isSelf) {

        for (int i = 0; i < chars.length - 1; i ++) {

            if (chars[i] == '+' && chars[i + 1] == '+') {
                chars[i] = '-';
                chars[i + 1] = '-';
                boolean res = canWin(chars, !isSelf);
                chars[i] = '+';
                chars[i + 1] = '+';
                if (isSelf && res) {
                    return true;
                }
                if (!isSelf && !res) {
                    return false;
                }

            }

        }
        return !isSelf;
    }

}
