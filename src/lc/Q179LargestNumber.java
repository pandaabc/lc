package lc;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Q179LargestNumber {

    public String largestNumber(int[] nums) {
        return Arrays.stream(nums).boxed().map(String::valueOf).sorted(comparator).collect(Collectors.joining());
    }

    Comparator<String> comparator = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            if (o1.length() > o2.length()) {
                o2 = fill(o2, o1);
            } else if (o1.length() < o2.length()) {
                o1 = fill(o1, o2);
            }
            for (int i = 0; i < o1.length() && i < o2.length(); i ++) {
                if (o1.charAt(i) > o2.charAt(i)) {
                    return 1;
                } else if (o1.charAt(i) < o2.charAt(i)) {
                    return -1;
                }
            }
            return 0;
        }

        public String fill(String s, String l) {

            StringBuilder sb = new StringBuilder(s);

            int p = 0;
            for (int i = s.length(); i < l.length(); i ++) {
                if (p == s.length()) {
                    p = 0;
                }
                sb.append(s.charAt(p));
                p ++;
            }

            return sb.toString();

        }
    };

}
