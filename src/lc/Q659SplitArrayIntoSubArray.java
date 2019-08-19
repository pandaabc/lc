package lc;

import java.util.*;
import java.util.stream.Collectors;

public class Q659SplitArrayIntoSubArray {

    public boolean isPossible(int[] nums) {
        PriorityQueue<LinkedList<Integer>> queue
                = new PriorityQueue<>((e1, e2) -> {
                    int res = Integer.compare(e1.getLast(), e2.getLast());
                    if (res == 0) {
                        Integer.compare(e1.size(), e2.size());
                    }
                    return res;
        });

        for (int i = 0; i < nums.length; i ++) {
            if (i == 0) {
                LinkedList<Integer> l = new LinkedList<>();
                l.add(nums[i]);
            } else {
                boolean check = true;
                while (check) {
                    LinkedList<Integer> l = queue.poll();
                    int lastNum = l.getLast();
                    if (lastNum == nums[i] - 1) {
                        l.add(nums[i]);
                        queue.offer(l);
                        check = false;
                    } else if (lastNum == nums[i]) {
                        LinkedList<Integer> n = new LinkedList<>();
                        n.add(nums[i]);
                        queue.offer(n);
                        check = false;
                    } else if (l.size() >= 3){
                        boolean isQueueFine = checkQueue(queue);
                        if (isQueueFine) {
                            return false;
                        }
                        LinkedList<Integer> m = new LinkedList<>();
                        m.add(nums[i]);
                        queue.offer(m);
                        check = false;
                    } else {
                        return false;
                    }
                }
            }
        }

        return checkQueue(queue);
    }

    private boolean checkQueue(PriorityQueue<LinkedList<Integer>> queue) {
        while (queue.size() > 0) {
            LinkedList<Integer> n = queue.poll();
            if (n.size() < 3) {
                return false;
            }
        }
        return true;
    }


    public boolean isPossibleM2(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int i = 0; i < nums.length; i ++) {
            counts.compute(nums[i], (k, v) -> v == null ? 1 : v + 1);
        }

        System.out.println(counts);

        int lastNum = nums[0];
        List<Integer> length = new ArrayList<>();
        for (int i = 0; i < nums.length; i ++) {
            if (i == 0 || length.isEmpty()) {
                for (int j = 0; j < counts.get(nums[i]); j ++) {
                    length.add(1);
                }
            } else if (lastNum == nums[i]) {
                continue;
            } else if (lastNum == nums[i] - 1) {
                int cnt = counts.get(nums[i]);
                if (cnt >= length.size()) {
                    length = length.stream().map(e -> e + 1).collect(Collectors.toList());
                    for (int j = 0; j < cnt - length.size(); j ++) {
                        length.add(1);
                    }
                } else {
                    Collections.sort(length);
                    List<Integer> l1 = new ArrayList<>();
                    for (int j = 0; j < length.size(); j ++) {
                        if (j < cnt) {
                            l1.add(length.get(j) + 1);
                        } else if (length.get(j) < 3) {
                            return false;
                        }
                    }
                    length = l1;
                }
            } else {
                if (length.stream().anyMatch(e -> e < 3)) {
                    return false;
                }
                length.clear();
                for (int j = 0; j < counts.get(nums[i]); j ++) {
                    length.add(1);
                }
            }
            lastNum = nums[i];
        }

        return length.stream().noneMatch(e -> e < 3);
    }
}
