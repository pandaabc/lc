package lc;

import java.util.*;

public class Q815BusRoutes {

    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (S == T) {
            return 0;
        }
        if (routes == null || routes.length == 0) {
            return -1;
        }

        Map<Integer, Set<Integer>> stopsByRt = new HashMap<>();
        Map<Integer, Set<Integer>> rtsByStops = new HashMap<>();
        for (int i = 0; i < routes.length; i ++) {
            for (int j = 0; j < routes[i].length; j ++) {
                stopsByRt.computeIfAbsent(i, k -> new HashSet<>()).add(routes[i][j]);
                rtsByStops.computeIfAbsent(routes[i][j], k -> new HashSet<>()).add(i);
            }
        }
        Set<Integer> triedRt = new HashSet<>();
        Set<Integer> triedSt = new HashSet<>();

        Queue<Integer> stops = new LinkedList<>();
        stops.offer(S);
        triedSt.add(S);
        int steps = 0;

        while (!stops.isEmpty()) {
            int size = stops.size();
            for (int i = 0; i < size; i ++) {
                int stop = stops.poll();
                if (stop == T) {
                    return steps;
                }
                Set<Integer> rts = rtsByStops.get(stop);
                for (int r : rts) {
                    if (triedRt.contains(r)) {
                        continue;
                    }
                    triedRt.add(r);
                    Set<Integer> stps = stopsByRt.get(r);
                    for (int s : stps) {
                        if (!triedSt.contains(s)) {
                            triedSt.add(s);
                            stops.add(s);
                        }
                    }
                }
            }
            steps ++;
        }

        return -1;

    }

}
