package lc;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Use quick select method
 * Or priority Queue
 */
public class Q215KthLargestNumber {
    public int findKthLargest(int[] nums, int k) {

        return findKthWithQuickSelect(nums, k, 0, nums.length - 1);
    }

    public int findKthLargestWithPriorityQueue(int[] nums, int k) {

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            queue.offer(num);
            if (queue.size() > k) {
                queue.poll();
            }
        }

        return queue.poll();

    }


    public int findKthWithQuickSelect(int[] nums, int k, int s, int e) {
        //System.out.println(k + " s: " + s + " " + e + " " + Arrays.toString(nums));
        // pick a random number between the range s and e
        int mid = (s + e) / 2;
        int pivot = nums[mid];

        int end = e;
        int pidx = s;

        for (int i = s; i <= end; i ++) {
            while (nums[i] < pivot && i <= end) {
                if (i < end) {
                    swap(nums, i, end);
                }
                end --;
            }
            if (nums[i] > pivot) {
                if (i != pidx) {
                    swap(nums, i, pidx);
                }
                pidx ++;
                continue;
            }

            if (nums[i] == pivot) {
                continue;
            }
        }

        if (pidx <= k - 1 && end >= k - 1) {
            return pivot;
        }

        if (pidx > k - 1) {
            return findKthWithQuickSelect(nums, k, s, pidx - 1);
        }
        return findKthWithQuickSelect(nums, k, end + 1, e);

    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
