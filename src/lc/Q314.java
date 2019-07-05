package lc;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
 *
 * If two nodes are in the same row and column, the order should be from left to right.
 */
public class Q314 {

    // Note: use TreeMap is also fine.
    public List<List<Integer>> verticalOrder(TreeNode root) {

        if (root == null) {
            return Collections.EMPTY_LIST;
        }

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> indexQueue = new LinkedList<>();
        int min = 0;
        int max = 0;

        Map<Integer, List<Integer>> map = new HashMap<>();

        nodeQueue.offer(root);
        indexQueue.offer(0);

        while (nodeQueue.size() > 0) {
            TreeNode node = nodeQueue.poll();
            int index = indexQueue.poll();
            if (node.left != null) {
                nodeQueue.offer(node.left);
                indexQueue.offer(index - 1);
                min = Math.min(min, index - 1);
            }
            if (node.right != null) {
                nodeQueue.offer(node.right);
                indexQueue.offer(index + 1);
                max = Math.max(max, index + 1);
            }
            map.computeIfAbsent(index, k -> new ArrayList<>()).add(node.val);
        }

        return IntStream.rangeClosed(min, max).mapToObj(map::get).collect(Collectors.toList());

    }

}

