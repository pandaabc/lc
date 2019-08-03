package algo;

import java.util.*;

public class Q1057CampusBikes_BucketSort {

    public int[] assignBikes(int[][] workers, int[][] bikes) {
        List<int[]>[] dist = new ArrayList[1000 * 1000 * 2 + 1];
        for (int i = 0; i < workers.length; i ++) {
            for (int j = 0; j < bikes.length; j ++) {
                int dis = Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);
                if (dist[dis] == null) {
                    dist[dis] = new ArrayList<>();
                }
                dist[dis].add(new int[]{i, j});
            }
        }
        //System.out.println(Arrays.deepToString(dist));
        Set<Integer> a = new HashSet<>();
        Set<Integer> b = new HashSet<>();
        int[] res = new int[workers.length];
        int k = 0;
        for (int i = 0; i < dist.length; i ++) {
            if (dist[i] != null) {
                for (int j = 0; j < dist[i].size(); j ++) {
                    if (!a.contains(dist[i].get(j)[0]) && !b.contains(dist[i].get(j)[1])) {
                        res[dist[i].get(j)[0]] = dist[i].get(j)[1];
                        a.add(dist[i].get(j)[0]);
                        b.add(dist[i].get(j)[1]);
                        k ++;

                    }
                }
            }
            if (k == res.length) {
                break;
            }
        }
        return res;
    }

}
