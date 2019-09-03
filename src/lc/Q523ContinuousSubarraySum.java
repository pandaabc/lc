package lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q523ContinuousSubarraySum {

    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length <= 1) {
            return false;
        }
        Map<Integer, List<Integer>> modsMap = new HashMap<>();
        modsMap.computeIfAbsent(0, key -> new ArrayList<>()).add(-1);
        for (int i = 0; i < nums.length; i ++) {
            if (i > 0) {
                nums[i] += nums[i - 1];
            }
            if (k != 0) {
                nums[i] %= k;
            }
            modsMap.computeIfAbsent(nums[i], key -> new ArrayList<>()).add(i);
            if (i - modsMap.get(nums[i]).get(0) > 1) {
                return true;
            }
        }

        return false;

    }

}
