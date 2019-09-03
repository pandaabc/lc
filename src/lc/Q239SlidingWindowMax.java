package lc;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.IntStream;

public class Q239SlidingWindowMax {
    public int[] maxSlidingWindow(int[] nums, int k) {

        Deque<Integer> queue = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i ++) {
            addNumToQueue(nums, i, queue, k);
            if (i >= k - 1) {
                list.add(nums[queue.peekFirst()]);
            }
        }

        return IntStream.range(0, list.size()).map(list::get).toArray();

    }

    private void addNumToQueue (int[] nums, int i, Deque<Integer> queue, int k) {

        int n = nums[i];
        while (!queue.isEmpty() && nums[queue.peekLast()] <= n) {
            queue.pollLast();
        }
        queue.offerLast(i);
        if (i - queue.peekFirst() + 1 > k) {
            queue.pollFirst();
        }

    }
}
