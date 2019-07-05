package lc;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Number of Connected Components in an Undirected Graph
 * Union Find
 */
public class Q323 {

    public int countComponents(int n, int[][] edges) {
        if (n <= 1) return n;
        int[] components = IntStream.range(0, n).toArray();
        for (int i = 0; i < edges.length; i ++) {
            unionFind(edges[i], components);
        }
        return (int)Arrays.stream(components).map(e -> getRt(e, components)).distinct().count();
    }

    private void unionFind(int[] edge, int[] components) {

        int p1 = edge[0];
        int p2 = edge[1];
        // find
        int r1 = getRt(p1, components);
        int r2 = getRt(p2, components);

        if (r1 != r2) {
            components[r1] = r2;
        }

    }

    private int getRt(int p1, int[] components) {
        int r1 = p1;

        while (components[r1] != r1) {
            components[r1] = components[components[r1]];// this step compress the path a little bit;
            r1 = components[r1];
        }
        return r1;
    }

    private void unionFind(int[] edge, int[] components, Map<Integer, Set<Integer>> componentMap) {

        // find components
        int area0 = components[edge[0]];
        int area1 = components[edge[1]];
        // union
        if (area0 != area1) {
            if (componentMap.get(area0).size() > componentMap.get(area1).size()) {
                union(area1, area0, components, componentMap);
            } else {
                union(area0, area1, components, componentMap);
            }
        }

    }

    // merge a1  to a0
    private void union(int a0, int a1, int[] components, Map<Integer, Set<Integer>> componentMap) {

        componentMap.get(a1).forEach(e -> components[e] = a0);
        componentMap.get(a0).addAll(componentMap.get(a1));
        componentMap.remove(a1);

    }

}
