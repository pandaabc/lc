package algo;

import java.util.*;

public class Q743NetworkDelayTime_Dijkstra {

    public int networkDelayTime(int[][] times, int N, int K) {

        if (times == null || times.length == 0) {
            return -1;
        }
        Map<Integer, Set<int[]>> graph = new HashMap<>();
        for (int i = 0; i < times.length; i ++) {
            graph.computeIfAbsent(times[i][0], k -> new HashSet<>()).add(times[i]);
            //graph.computeIfAbsent(times[i][1], k -> new HashSet<>()).add(times[i]);
        }
        // int[] node will contain: 0: totalCost; 1: lastNode
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparing(e -> e[0]));
        Set<Integer> tried = new HashSet<>();
        queue.offer(new int[]{0, K});
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            if (tried.contains(pos[1])) {
                continue;
            }
            tried.add(pos[1]);
            if (tried.size() == N) {
                return pos[0];
            }
            Set<int[]> cons = Optional.ofNullable(graph.get(pos[1])).orElse(new HashSet<>());
            for (int[] c : cons) {
                int nextP = c[1];
                if (!tried.contains(nextP)) {
                    queue.offer(new int[]{pos[0] + c[2], nextP});
                }
            }
        }
        return -1;
    }

}
