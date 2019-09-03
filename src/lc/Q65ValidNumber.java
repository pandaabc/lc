package lc;

public class Q65ValidNumber {

    public boolean isNumber(String s) {
        if (s == null || s.trim().isEmpty()) {
            return false;
        }
        s = s.trim();
        if (s.charAt(0) == '+' || s.charAt(0) == '-') {
            s = s.substring(1);
        }
        // split by e
        if (!checkEAndDot(s)) {
            return false;
        }

        String[] sSplit = s.split("e");
        if (sSplit.length > 2 || sSplit.length == 0) {
            return false;
        }
        return isValidNum(sSplit[0]) && (sSplit.length > 1 ? isInt(sSplit[1], true) : true);

    }

    private boolean isInt(String s, boolean signed) {

        //System.out.println("in isint " + s);
        for (int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if (i == 0 && signed && s.length() > 1 && (c == '+' || c == '-')) {
                continue;
            }
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    private boolean isValidNum(String s) {
        //System.out.println("in validnum " + s);
        String[] split = s.split("\\.");
        if (split.length > 2 || split.length == 0) {
            return false;
        }
        return isInt(split[0], false) && (split.length > 1 ? isInt(split[1], false) : true);

    }

    private boolean checkEAndDot(String s) {
        if (s.isEmpty()) {
            return false;
        }
        int dCnt = 0;
        int eCnt = 0;
        if (s.charAt(0) == 'e' || s.charAt(s.length() - 1) == 'e') {
            return false;
        }
        for (int i = 0; i < s.length(); i ++) {
            if (s.charAt(i) == 'e') {
                eCnt ++;
            } else if (s.charAt(i) == '.') {
                dCnt ++;
            }
        }
        return dCnt < 2 && eCnt < 2;
    }

}
