package lc;

public class Q53MaximumSubarray {

    public int maxSubArray(int[] nums) {

        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i ++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        int curMin = nums[0];
        int curArrayMax = nums[0];
        for (int i = 1; i < sum.length; i ++) {
            curArrayMax = Math.max(sum[i], Math.max(sum[i] - curMin, curArrayMax));
            if (sum[i] < curMin) {
                curMin = sum[i];
            }
        }
        return curArrayMax;
    }

    public int maxSubArrayDQ(int[] nums) {
        int[] res = dq(nums, 0, nums.length - 1);
        return res[1];
    }

    private int[] dq(int[] nums, int i, int j) {

        if (i == j) {
            return new int[]{nums[i], nums[i], nums[i], nums[i]};
        }
        if (j - i == 1) {
            int s = nums[i] + nums[j];
            int l = Math.max(s, nums[i]);
            int r = Math.max(s, nums[j]);
            int m = Math.max(l, r);
            return new int[]{l, m, r, s};
        }
        int mid = i + (j - i) / 2;
        return merge(dq(nums, i, mid), dq(nums, mid + 1, j));
    }

    private int[] merge(int[] left, int[] right) {
        int l = Math.max(left[0], left[3] + right[0]);
        int r = Math.max(right[2], left[2] + right[3]);
        int s = left[3] + right[3];
        int m = Math.max(left[2] + right[0], Math.max(Math.max(l, r), Math.max(left[1], right[1])));
        m = Math.max(m, s);
        return new int[]{l, m, r, s};
    }


}
