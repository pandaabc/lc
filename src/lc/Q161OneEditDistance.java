package lc;

public class Q161OneEditDistance {

    public boolean isOneEditDistance(String s, String t) {

        if (s == null && t == null) {
            return true;
        }

        return isOneEditDistance(s, t, false);

    }

    private boolean isOneEditDistance(String s, String t, boolean edited) {

        for (int i = 0; i < Math.max(s.length(), t.length()); i ++) {

            if (i < s.length() && i < t.length() && s.charAt(i) == t.charAt(i)) {
                continue;
            }
            if (edited) {
                return false;
            }
            if (i >= s.length()) {
                return isOneEditDistance("", t.substring(i + 1), true);
            } else if (i >= t.length()) {
                return isOneEditDistance(s.substring(i + 1), "", true);
            } else {
                return isOneEditDistance(s.substring(i + 1), t.substring(i + 1), true) || isOneEditDistance(s.substring(i), t.substring(i + 1), true) || isOneEditDistance(s.substring(i + 1), t.substring(i), true);
            }

        }

        return edited;

    }

}
