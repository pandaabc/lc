package lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Logic to solve this question:
 * 1. First find out if there is any point with multiple parents.
 * 2. if there is no mulitple parents, then we just follow Q684 to resolve it.  (find redundant cicle)
 * 3. if there is multiple parents, we just test to see if we remove one, whether we get the ok tree.
 * if yes, then the one we removed is the answer, if not then the other one is the answer
 */
public class Q685RedundantConnectionII {

    public int[] findRedundantDirectedConnection(int[][] edges) {

        Map<Integer, Integer> parent = new HashMap<>();
        List<Integer> doubleP = new ArrayList<>();
        for (int i = 0; i < edges.length; i ++) {
            if (parent.containsKey(edges[i][1])) {
                doubleP.add(i);
                doubleP.add(parent.get(edges[i][1]));
                break;
            }
            parent.put(edges[i][1], i);
        }

        int[] res = findRedundantDirectedConnectionInternal(edges, doubleP.isEmpty() ? -1 : doubleP.get(0));

        if (doubleP.isEmpty()) {
            return res;
        }
        if (res != null) {
            return edges[doubleP.get(1)];
        }
        return edges[doubleP.get(0)];
    }

    public int[] findRedundantDirectedConnectionInternal(int[][] edges, int notConsider) {
        int[] roots = IntStream.rangeClosed(0, edges.length).toArray();
        for (int i = 0; i < edges.length; i ++) {
            if (notConsider >= 0 && i == notConsider) {
                continue;
            }
            boolean unionRes = union(roots, edges[i][0], edges[i][1]);
            if (!unionRes) {
                return edges[i];
            }
        }
        return null;
    }

    private boolean union(int[] roots, int i, int j) {

        int pi = find(roots, i);
        int pj = find(roots, j);
        if (pi == pj) {
            return false;
        }
        roots[pj] = pi;
        return true;

    }

    private int find(int[] roots, int i) {
        while (roots[i] != i) {
            roots[i] = roots[roots[i]];
            i = roots[i];
        }
        return i;
    }
}
