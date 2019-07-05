package lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Q350IntersectionTwoArrays {

    /**
     * Note. this solution is not efficient.  we dont have to convert both array to map... just one map.
     * then the other one can stream through the map to determine the match
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        Map<Integer, Long> nums1Map = Arrays.stream(nums1).boxed().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        Map<Integer, Long> nums2Map = Arrays.stream(nums2).boxed().collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        List<Integer> nums = new ArrayList<>();
        for (Map.Entry<Integer, Long> entry1 : nums1Map.entrySet()) {
            if (nums2Map.containsKey(entry1.getKey())) {
                long cnt = Math.min(entry1.getValue(), nums2Map.get(entry1.getKey()));
                for (long i = 0; i < cnt; i ++) {
                    nums.add(entry1.getKey());
                }
            }
        }

        if (nums.isEmpty()) {
            return new int[0];
        }
        return nums.stream().mapToInt(e -> e).toArray();
    }

}
