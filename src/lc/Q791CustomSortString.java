package lc;

public class Q791CustomSortString {

    public String customSortString(String S, String T) {
        if (T == null || T.isEmpty()) {
            return T;
        }
        int[] count = new int[26];
        for (int i = 0; i < T.length(); i ++) {
            char c = T.charAt(i);
            count[c - 'a'] += 1;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i ++) {
            char c = S.charAt(i);
            int cnt = count[c - 'a'];
            for (int j = 0; j < cnt; j ++) {
                sb.append(c);
            }
            count[c - 'a'] = 0;
        }

        for (int i = 0; i < 26; i ++) {
            if (count[i] > 0) {
                char c = (char) (i + 'a');
                for (int j = 0; j < count[i]; j ++) {
                    sb.append(c);
                }
            }
        }

        return sb.toString();
    }
}
