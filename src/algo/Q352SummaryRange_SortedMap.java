package algo;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class Q352SummaryRange_SortedMap {

    class SummaryRanges {

        private SortedMap<Integer, Integer> map;

        /** Initialize your data structure here. */
        public SummaryRanges() {
            this.map = new TreeMap<>();
        }

        public void addNum(int val) {
            map.computeIfAbsent(val, k -> k);
            findRange(val);
        }

        public int[][] getIntervals() {

            int cur = map.firstKey() - 1;
            List<int[]> ranges = new ArrayList<>();
            for (int k : map.keySet()) {
                if (k > cur) {
                    int s = k;
                    int e = findRange(s);
                    ranges.add(new int[]{s, e});
                    cur = e;
                }
            }

            int[][] res = new int[ranges.size()][2];
            for (int i = 0; i < res.length; i ++) {
                res[i] = ranges.get(i);
            }
            return res;
        }

        private int findRange(int i) {
            int end = map.get(i);
            if (map.containsKey(end + 1)) {
                end = findRange(end + 1);
            }
            map.put(i, end);
            return end;
        }

    }

}
