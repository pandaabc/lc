package lc;

import java.util.PriorityQueue;

public class Q973KClosestPointsToOrigin {

    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(K, (e1, e2) -> Integer.compare(e2[0] * e2[0] + e2[1] * e2[1], e1[0] * e1[0] + e1[1] * e1[1]));
        for (int[] point : points) {
            queue.add(point);
            if (queue.size() > K) {
                queue.poll();
            }
        }
        int[][] res = new int[queue.size()][2];
        int i = 0;
        while (queue.size() > 0) {
            res[i] = queue.poll();
            i ++;
        }
        return res;
    }

}
