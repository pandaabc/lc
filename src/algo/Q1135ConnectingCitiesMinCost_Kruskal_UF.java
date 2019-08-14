package algo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * The idea of Kruskal is that, to first sort all the connection length, then try to connect them.
 * this way, each time the connection is gauranteed to be minimum.
 * Union find is used to help make sure the new connection is not added when connection exist
 */
public class Q1135ConnectingCitiesMinCost_Kruskal_UF {
    public int minimumCost(int N, int[][] connections) {
        if (connections == null || connections.length == 0) {
            return -1;
        }
        Arrays.sort(connections, Comparator.comparing(e -> e[2]));
        int[] parents = IntStream.rangeClosed(0, N).toArray();
        int cost = 0;
        for (int i = 0; i < connections.length; i ++) {
            int[] c = connections[i];
            int p1 = find(c[0], parents);
            int p2 = find(c[1], parents);
            if (p1 != p2) {
                parents[p1] = p2;
                cost += c[2];
            }
        }
        Set<Integer> p = new HashSet<>();
        for (int i = 1; i <= N; i ++) {
            int p1 = find(i, parents);
            p.add(p1);
        }
        return p.size() == 1 ? cost : -1;
    }

    private int find(int i, int[] parents) {
        while(i != parents[i]) {
            parents[i] = parents[parents[i]];
            i = parents[i];
        }
        return i;
    }
}
