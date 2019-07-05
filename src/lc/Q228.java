package lc;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 */
public class Q228 {
    public List<String> summaryRanges(int[] nums) {

        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        int start = 0;
        int cur = 0;
        for (int i = 0; i < nums.length; i ++){
            if (i == 0) {
                start = nums[i];
                cur = start;
                continue;
            }
            if (cur == nums[i] - 1) {
                cur = nums[i];
            } else {
                res.add(formRange(start, cur));
                start = nums[i];
                cur = nums[i];
            }
        }

        res.add(formRange(start, cur));
        return res;

    }

    private String formRange(int start, int end) {
        if (start == end) {
            return String.valueOf(start);
        }
        return String.valueOf(start) + "->" + String.valueOf(end);
    }
}
