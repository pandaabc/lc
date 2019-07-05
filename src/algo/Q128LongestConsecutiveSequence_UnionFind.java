package algo;

import java.util.*;

public class Q128LongestConsecutiveSequence_UnionFind {

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] grp = new int[nums.length];
        Map<Integer, Integer> indexByNumberMap = new HashMap<>();
        for (int i = 0; i < grp.length; i ++) {
            grp[i] = i;
            indexByNumberMap.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i ++) {
            if (indexByNumberMap.containsKey(nums[i] - 1)) {
                union(i, indexByNumberMap.get(nums[i] - 1), grp);
            }
            if (indexByNumberMap.containsKey(nums[i] + 1)) {
                union(i, indexByNumberMap.get(nums[i] + 1), grp);
            }
        }
        Map<Integer, Set<Integer>> intByGrp = new HashMap<>();
        for (int i = 0; i < grp.length; i ++) {
            int rt = findRoot(i, grp);
            intByGrp.computeIfAbsent(rt, k -> new HashSet<>()).add(nums[i]);
        }
        return intByGrp.values().stream().map(e -> e.size()).mapToInt(e -> e).max().orElse(0);
    }


    public int findRoot(int i, int[] grp) {

        while (grp[i] != i) {
            grp[i] = grp[grp[i]];
            i = grp[i];
        }

        return i;
    }

    public void union(int i0, int i1, int[] grp) {
        grp[findRoot(i0, grp)] = grp[findRoot(i1, grp)];
    }

}
