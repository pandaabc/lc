package lc;

public class Q270ClosestBinarySearchTree {

    public int closestValue(TreeNode root, double target) {

        return findClosest(root, target, Double.MAX_VALUE, 0);

    }

    public int findClosest(TreeNode node, double target, double diff, int val) {

        if (node == null) {
            return val;
        }
        double newDiff = Math.abs(node.val - target);
        if (diff > newDiff) {
            diff = newDiff;
            val = node.val;
        }
        if (target < node.val) {
            val = findClosest(node.left, target, diff, val);
        } else {
            val = findClosest(node.right, target, diff, val);
        }
        return val;
    }

}
