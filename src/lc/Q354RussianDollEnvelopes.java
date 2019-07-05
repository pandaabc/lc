package lc;

import java.util.Arrays;

public class Q354RussianDollEnvelopes {

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        Arrays.sort(envelopes, (e1, e2) -> Integer.compare(e1[0], e2[0]));
        int[] max = new int[envelopes.length];
        max[0] = 1;
        int res = 1;
        for (int i = 1; i < max.length; i ++) {
            int m = 1;
            for (int j = i - 1; j >= 0; j --) {
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
                    m = Math.max(m, max[j] + 1);
                }
            }
            max[i] = m;
            res = Math.max(res, m);
        }
        return res;
    }

}
