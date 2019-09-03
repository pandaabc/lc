package lc;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Q480SlidingWindowMedian {

    public double[] medianSlidingWindow(int[] nums, int k) {
        StringBuilder sb = new StringBuilder();

        int smallSize = 0;
        int largeSize = 0;
        List<Double> res = new ArrayList<>();
        TreeMap<Integer, Integer> largeCnt = new TreeMap<>();
        TreeMap<Integer, Integer> smallCnt = new TreeMap<>();

        for (int i = 0; i < nums.length; i ++) {
            int num = nums[i];

            if (smallCnt.size() == 0 || smallCnt.lastKey() >= num) {
                smallCnt.compute(num, (key, val) -> val == null ? 1 : val + 1);
                smallSize ++;
            } else {
                largeCnt.compute(num, (key, val) -> val == null ? 1 : val + 1);
                largeSize ++;
            }

            if (largeSize + smallSize > k) {
                int n0 = nums[i - k];
                if (largeCnt.containsKey(n0)) {
                    removeNumber(largeCnt, n0);
                    largeSize --;
                } else {
                    removeNumber(smallCnt, n0);
                    smallSize --;
                }
            }

            while (largeSize > smallSize) {
                int n1 = largeCnt.firstKey();
                smallCnt.compute(n1, (key, val) -> val == null ? 1 : val + 1);
                removeNumber(largeCnt, n1);
                largeSize --;
                smallSize ++;
            }

            while (largeSize < smallSize - 1) {
                int n1 = smallCnt.lastKey();
                largeCnt.compute(n1, (key, val) -> val == null ? 1 : val + 1);
                removeNumber(smallCnt, n1);
                largeSize ++;
                smallSize --;
            }

            //System.out.println("index: " + i + ", num: " + num);
            //System.out.println(largeCnt);
            //System.out.println(smallCnt);
            if (largeSize + smallSize == k) {
                if (k % 2 == 1) {
                    res.add(smallCnt.lastKey() * 1.);
                } else {
                    res.add(smallCnt.lastKey() / 2. + largeCnt.firstKey() / 2.);
                }
            }
        }

        return res.stream().mapToDouble(e -> e).toArray();

    }

    private void removeNumber(TreeMap<Integer, Integer> map, int num) {
        map.compute(num, (k, v) -> v - 1);
        if (map.get(num) == 0) {
            map.remove(num);
        }
    }
}
