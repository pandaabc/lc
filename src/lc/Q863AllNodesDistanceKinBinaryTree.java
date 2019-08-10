package lc;

import java.util.*;

public class Q863AllNodesDistanceKinBinaryTree {

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {

        if (root == null || target == null) {
            return new ArrayList<>();
        }

        Stack<TreeNode> stack = new Stack<>();
        findPathToNode(root, target, stack);
        Set<TreeNode> parents = new HashSet<>(stack);
        int cnt = 0;
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            searchK(node, cnt, K, parents, result);
            cnt ++;
        }
        return result;
    }

    public boolean findPathToNode(TreeNode root, TreeNode target, Stack<TreeNode> stack) {

        stack.push(root);
        if (root == target) {
            return true;
        }
        // check left
        if (root.left != null) {
            boolean found = findPathToNode(root.left, target, stack);
            if (!found) {
                stack.pop();
            } else {
                return true;
            }
        }

        if (root.right != null) {
            boolean found = findPathToNode(root.right, target, stack);
            if (!found) {
                stack.pop();
            } else {
                return true;
            }
        }

        return false;
    }

    public void searchK(TreeNode root, int start, int K, Set<TreeNode> parents, List<Integer> results) {

        if (start == K) {
            results.add(root.val);
            return;
        }
        if (root.left != null && !parents.contains(root.left)) {
            searchK(root.left, start + 1, K, parents, results);
        }
        if (root.right != null && !parents.contains(root.right)) {
            searchK(root.right, start + 1, K, parents, results);
        }
    }

}
