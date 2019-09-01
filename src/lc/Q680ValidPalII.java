package lc;

/**
 * This is a greedy method.
 * We only replace character when the char is different.  That actually make sense...
 */
public class Q680ValidPalII {

    public boolean validPalindrome(String s) {

        return isPal(s, 0, s.length() - 1, false);

    }

    public boolean isPal (String s, int i, int j, boolean skipped) {

        if (i >= j) {
            return true;
        }

        if (s.charAt(i) != s.charAt(j)) {
            return skipped ? false : isPal(s, i + 1, j, true) || isPal(s, i, j - 1, true);
        }

        return isPal(s, i + 1, j - 1, skipped);

    }
}
