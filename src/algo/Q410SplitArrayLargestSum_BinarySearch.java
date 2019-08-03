package algo;

import java.util.Arrays;

public class Q410SplitArrayLargestSum_BinarySearch {
    public int splitArray(int[] nums, int m) {
        long sum = Arrays.stream(nums).mapToLong(e -> (long)e).sum();
        long p0 = 0;
        long p1 = sum;
        while (p0 < p1) {
            long mid = (p0 + p1) / 2;
            if (isPossible(nums, mid, m)) {
                p1 = mid;
            } else {
                p0 = mid + 1;
            }
        }
        return (int)p0 - 1;
    }

    private boolean isPossible(int[] nums, long max, int splits) {
        int locSum = 0;
        int locSplits = 1;
        for (int i = 0; i < nums.length; i ++) {
            locSum += nums[i];
            if (locSum > max) {
                locSum = nums[i];
                locSplits ++;
            }
            if (locSum > max) {
                return false;
            }
        }
        return locSplits <= splits;
    }
}
