package algo;

import java.util.PriorityQueue;
import java.util.Random;

public class Q973KClosestPointsToOrigin_QuickSelect {

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

    public int[][] kClosestQuickSelect(int[][] points, int K) {
        int[][] pInfo = new int[points.length][2];
        for (int i = 0; i < points.length; i ++) {
            pInfo[i][0] = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            pInfo[i][1] = i;
        }
        quickSelect(0, points.length - 1, K - 1, pInfo);

        int[][] res = new int[K][2];
        for (int i = 0; i < K && i < points.length; i ++) {
            res[i] = points[pInfo[i][1]];
        }
        return res;
    }

    private void quickSelect(int i0, int i1, int k, int[][] pInfo) {

        int i_target = new Random().nextInt(i1 - i0 + 1) + i0;
        int num_target =  pInfo[i_target][0];
        switchLocation(i0, i_target, pInfo);

        int p0 = i0;
        int p1 = i1;
        int i = p0 + 1;
        while (p0 <= p1 && i <= p1) {
            if (pInfo[i][0] < num_target) {
                switchLocation(p0, i, pInfo);
                p0 ++;
            } else if (pInfo[i][0] > num_target) {
                switchLocation(p1, i, pInfo);
                p1 --;
            } else {
                i ++;
            }
        }

        if (p0 > k) {
            quickSelect(i0, p0 - 1, k, pInfo);
        } else if (p1 < k) {
            quickSelect(p1 + 1, i1, k, pInfo);
        }

    }

    private void switchLocation(int i0, int i1, int[][] pInfo) {
        int[] temp = pInfo[i0];
        pInfo[i0] = pInfo[i1];
        pInfo[i1] = temp;
    }

}
