package lc;

import java.util.ArrayList;
import java.util.List;

public class Q386LexicographicalNumberSort {

    class Solution {
        public List<Integer> lexicalOrder(int n) {
            List<Integer> result = new ArrayList<>();
            getOrder(0, n, result);
            return result;
        }

        public void getOrder(int prefix, int max, List<Integer> list) {

            int s = prefix == 0 ? 1 : 0;
            for (int i = s; i <= 9; i ++) {
                int cur = prefix * 10 + i;
                while (cur <= max) {
                    list.add(cur);
                    getOrder(cur, max, list);
                }
            }
        }
    }

}
