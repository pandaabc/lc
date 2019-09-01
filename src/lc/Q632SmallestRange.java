package lc;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.IntStream;

public class Q632SmallestRange {
    public int[] smallestRange(List<List<Integer>> nums) {
        if (nums == null || nums.size() == 0) {
            return null;
        }
        TreeMap<Integer, Deque<Integer>> map = new TreeMap<>();
        int[] p = new int[nums.size()];
        IntStream.range(0, nums.size()).forEach(e -> map.computeIfAbsent(nums.get(e).get(0), k -> new ArrayDeque<>()).offerLast(e));
        int[] res = new int[]{map.firstKey(), map.lastKey()};

        while(true) {
            //System.out.println(Arrays.toString(p));
            //System.out.println(map);
            int idx = map.firstEntry().getValue().pollFirst();
            int p0 = p[idx] += 1;
            if (p0 >= nums.get(idx).size()) {
                break;
            }
            int num = nums.get(idx).get(p0);
            map.computeIfAbsent(num, k -> new ArrayDeque<>()).add(idx);
            if (map.firstEntry().getValue().isEmpty()) {
                map.remove(map.firstKey());
            }
            if (map.lastKey() - map.firstKey() < res[1] -  res[0]) {
                res = new int[]{map.firstKey(), map.lastKey()};
            }
        }
        return res;
    }
}
