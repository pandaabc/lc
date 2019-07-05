package lc;

import java.util.Arrays;

public class Q152MaxProductSubarray {

    public int maxProduct(int[] nums) {

        if(nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }

        int[] idx = new int[nums.length];
        int[] pro = new int[nums.length];

        for (int i = 0; i < nums.length; i ++) {
            int product = nums[i];
            int j = i - 1;
            int maxPro = product;
            int maxInx = i;
            while (j >= 0) {
                product *= pro[j];
                if (product > maxPro) {
                    maxPro = product;
                    maxInx = idx[j];
                }
                j = idx[j] - 1;
            }
            pro[i] = maxPro;
            idx[i] = maxInx;
            System.out.println(Arrays.toString(pro));
            System.out.println(Arrays.toString(idx));
        }
        return Arrays.stream(pro).max().getAsInt();
    }

}
