package lc;

import java.util.*;

public class Q857MinCostToHireKWorkers {

    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        if (quality == null || quality.length < K) {
            return 0;
        }

        PriorityQueue<int[]> qualities = new PriorityQueue<>((e1, e2) -> Integer.compare(e1[0], e2[0]));
        PriorityQueue<double[]> unitwage = new PriorityQueue<>((e1, e2) -> Double.compare(e2[0], e1[0]));
        Set<Integer> removed = new HashSet<>();

        for (int i = 0; i < quality.length; i ++) {
            qualities.offer(new int[]{quality[i], i});
            unitwage.offer(new double[]{wage[i] * 1. / quality[i], i});
        }

        double minPay = Double.MAX_VALUE;
        Set<Integer> candidates = new HashSet<>();
        int totalQua = 0;
        while (!unitwage.isEmpty()) {
            double[] unitw = unitwage.poll();
            int index = (int)unitw[1];
            if (!candidates.contains(index)) {
                candidates.add(index);
                totalQua += quality[index];
            }
            while (candidates.size() < K && !qualities.isEmpty()) {
                int[] q = qualities.poll();
                if (q[1] == index || removed.contains(q[1])) {
                    continue;
                }
                candidates.add(q[1]);
                totalQua += q[0];
            }
            if (candidates.size() < K) {
                return minPay;
            }
            minPay = Math.min(minPay, totalQua * unitw[0]);
            removed.add(index);
            candidates.remove(index);
            totalQua -= quality[index];
        }
        return minPay;
    }



}
