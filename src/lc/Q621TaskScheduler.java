package lc;

import java.util.*;
import java.util.stream.Collectors;

public class Q621TaskScheduler {
    public int leastInterval(char[] tasks, int n) {

        if (tasks == null || tasks.length == 0) {
            return 0;
        }
        Map<Character, Integer> cnt = new HashMap<>();
        for (int i = 0; i < tasks.length; i ++) {
            cnt.compute(tasks[i], (k, v) -> v == null ? 1 : v + 1);
        }
        List<Integer> orderedCnt = cnt.values().stream().sorted(Comparator.<Integer>naturalOrder().reversed()).collect(Collectors.toList());
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < orderedCnt.size(); i ++) {
            int c = orderedCnt.get(i);
            if (queue.size() < n) {
                queue.offer(c);
            } else {
                int c0 = queue.poll();
                queue.offer(c + c0);
            }
        }
        // get the max
        int max = queue.stream().max(Comparator.naturalOrder()).orElse(-1);
        long space = queue.stream().filter(e -> e < max).count();
        return max * n -  (int)space;
    }
}
