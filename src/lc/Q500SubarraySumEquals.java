package lc;

import java.util.HashMap;
import java.util.Map;

public class Q500SubarraySumEquals {

    public int subarraySum(int[] nums, int k) {

        Map<Integer, Integer> sums = new HashMap<>();
        sums.put(0, 1);
        int sum = 0;
        int cnt = 0;
        for (int i = 0; i < nums.length; i ++) {

            sum += nums[i];
            cnt += sums.getOrDefault(sum - k, 0);
            sums.compute(sum, (key, v) -> v == null ? 1 : v + 1);

        }
        return cnt;

    }

}
