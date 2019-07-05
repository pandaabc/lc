package lc;

import java.util.*;
import java.util.stream.Collectors;

public class Q310 {
    // the basic idea of the problem is:
    // each tree is consist of leaves, and internal nodes.
    // if we remove the leaves layer by layer, then we will get to the middle of the tree.
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (edges.length == 0) {
            List<Integer> res = new ArrayList<>();
            res.add(0);
            return res;
        }
        Map<Integer, Set<Integer>> nodes = new HashMap<>();
        // initialize the edges
        for (int i = 0; i < edges.length; i ++) {
            nodes.computeIfAbsent(edges[i][0], k -> new HashSet<>()).add(edges[i][1]);
            nodes.computeIfAbsent(edges[i][1], k -> new HashSet<>()).add(edges[i][0]);
        }
        // remove leaves
        Set<Integer> leaves = new HashSet<>();
        while (nodes.size() > 2) {
            for (Map.Entry<Integer, Set<Integer>> integerSetEntry : nodes.entrySet()) {
                integerSetEntry.getValue().removeAll(leaves);
            }
            leaves = nodes.entrySet().stream().filter(entry -> entry.getValue().size() == 1).map(Map.Entry::getKey).collect(Collectors.toSet());
            nodes = nodes.entrySet().stream()
                    .filter(entry -> entry.getValue().size() > 1)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }

        return nodes.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
    }

}
