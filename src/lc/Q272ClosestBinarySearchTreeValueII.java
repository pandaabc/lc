package lc;

import com.sun.source.tree.Tree;

import java.util.*;

public class Q272ClosestBinarySearchTreeValueII {

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        if (k == 0) {
            return new ArrayList<>();
        }
        Stack<TreeNode> largers = new Stack<>();
        Stack<TreeNode> smallers = new Stack<>();
        List<Integer> res = new ArrayList<>();
        Set<TreeNode> tried = new HashSet<>();
        addInitialNodes(largers, smallers, target, root, tried);

        while (res.size() < k) {
            TreeNode cur = getNode(largers, smallers, target);
            res.add(cur.val);
            tried.add(cur);
            //addMoreNodes(largers, smallers, target, cur, tried);
        }

        return res;
    }

    private void addMoreLargers(Stack<TreeNode> largers, TreeNode node) {

        TreeNode next = node.right;
        while(next != null) {
            largers.push(next);
            next = next.left;
        }

    }

    private void addMoreSmallers(Stack<TreeNode> smallers, TreeNode node) {

        TreeNode next = node.left;
        while(next != null) {
            smallers.push(next);
            next = next.right;
        }

    }

    private TreeNode getNode(Stack<TreeNode> largers, Stack<TreeNode> smallers, double target) {
        if (largers.peek().val - target < target - smallers.peek().val) {
            return largers.pop();
        } else {
            return smallers.pop();
        }
    }

    private void addInitialNodes(Stack<TreeNode> largers, Stack<TreeNode> smallers, double target, TreeNode node, Set<TreeNode> tried) {
        while (node != null && !tried.contains(node)) {
            if (node.val > target) {
                largers.push(node);
                addInitialNodes(largers, smallers, target, node.left, tried);
            } else if (node.val < target) {
                smallers.push(node);
                addInitialNodes(largers, smallers, target, node.right, tried);
            } else if (node.val == target) {
                smallers.push(node);
            }
        }
    }

}
