package lc;

import java.util.stream.IntStream;

/**
 * Union find
 */
public class Q684RedundantConnection {

    public int[] findRedundantConnection(int[][] edges) {
        int[] roots = IntStream.rangeClosed(0, edges.length).toArray();
        for (int i = 0; i < edges.length; i ++) {
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
