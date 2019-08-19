package lc;

import java.util.*;

public class Q1066CampusBikesII {

    public int assignBikes(int[][] workers, int[][] bikes) {
        return minCost(workers, bikes, 0, new HashSet<>());
    }

    private int minCost(int[][] workers, int[][] bikes, int i, Set<Integer> assigned) {
        //System.out.println(i + " - " + assigned);
        if (i >= workers.length) {
            return 0;
        }
        int cost = Integer.MAX_VALUE;
        int[] worker = workers[i];
        for (int j = 0; j < bikes.length; j ++) {
            if (!assigned.contains(j)) {
                int locCost = Math.abs(worker[0] - bikes[j][0]) + Math.abs(worker[1] - bikes[j][1]);
                Set<Integer> newSet = new HashSet<>(assigned);
                newSet.add(j);
                locCost += minCost(workers, bikes, i + 1, newSet);
                cost = Math.min(cost, locCost);
            }
        }
        return cost;
    }

    /**
     * Idea:
     * for each worker, we can calculate the min cost for all the combinations.  Then when we discussion the next worker,
     * we can use the previous info for it.
     * Complexity: M * N * Cmn
     * @param workers
     * @param bikes
     * @return
     */
    private int minCostV2(int[][] workers, int[][] bikes) {

        Map<Set<Integer>, Integer> possibilities = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        set.add(-1);
        possibilities.put(set, 0);

        for (int i = 0; i < workers.length; i ++) {
            Map<Set<Integer>, Integer> newP = new HashMap<>();
            for (int j = 0; j < bikes.length; j ++) {

                for (Map.Entry<Set<Integer>, Integer> entry : possibilities.entrySet()) {
                    if (!entry.getKey().contains(j)) {
                        Set<Integer> newSet = new HashSet<>(entry.getKey());
                        newSet.add(j);
                        int c = entry.getValue() + calculateDis(workers[i], bikes[j]);
                        newP.computeIfPresent(newSet, (k, v) -> Math.min(v, c));
                    }
                }

            }

            possibilities = newP;
        }
        return possibilities.values().stream().min(Comparator.naturalOrder()).orElse(-1);

    }

    private int calculateDis(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }

}
