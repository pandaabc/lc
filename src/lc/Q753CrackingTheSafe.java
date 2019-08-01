package lc;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Q753CrackingTheSafe {
    public String crackSafe(int n, int k) {
        CSNode parent = new CSNode(0, k, n);
        Queue<CSNode> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        while(parent.children.size() > 0) {

            if (queue.size() == 0) {
                sb.append(0);
                queue.offer(parent.children.get(0));
            } else {

                int s = queue.size();
                Set<Integer> candidates = new HashSet<>();
                for (int i = 0; i < s; i ++) {
                    CSNode node = queue.poll();
                    if (i == 0) {
                        candidates.addAll(node.children.keySet());
                    } else {

                    }
                }

            }

        }
    }
}


class CSNode{
    int k;
    int val;
    Map<Integer, CSNode> children;
    public CSNode(int val, int k, int level) {
        this.k = k;
        this.val = val;
        if (level > 0) {
            this.children = IntStream.range(0, k).boxed().collect(Collectors.toMap(e -> e, e -> new CSNode(e, k, level - 1)));
        }
    }
}
