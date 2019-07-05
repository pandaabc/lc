package lc;

import java.util.Arrays;
import java.util.Comparator;

public class Q937ReorderLogFiles {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (e1, e2) -> {
            String[] e1a = e1.split(" ", 2);
            String[] e2a = e2.split(" ", 2);

            // if both digit
            if (isDigit(e1a[1]) && isDigit(e2a[1])) {
                return 0;
            }
            // if one of them is digit
            if (isDigit(e1a[1]) && !isDigit(e2a[1])) {
                return -1;
            }
            if (isDigit(e2a[1]) && !isDigit(e1a[1])) {
                return 1;
            }
            // is both is words
            if (!isDigit(e1a[1]) && !isDigit(e2a[1])) {
                int res = e1a[1].compareTo(e2a[1]);
                if (res == 0) {
                    return e1a[0].compareTo(e2a[0]);
                }
                return res;
            }
            return 0;
        });
        return logs;
    }

    public boolean isDigit(String str) {
        return str.charAt(0) >= '0' && str.charAt(0) <= '9';
    }
}
