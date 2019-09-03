package lc;

public class Q124BinaryTreemaxPathSum {

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] max = new int[1];
        max[0] = root.val;
        maxPath(root, max);
        return max[0];
    }

    private int maxPath(TreeNode node, int[] max) {
        if (node == null) {
            return 0;
        }
        int left = Math.max(0, maxPath(node.left, max));
        int right = Math.max(0, maxPath(node.right, max));
        max[0] = Math.max(max[0], left + right + node.val);
        return Math.max(left + node.val, right + node.val);
    }

}
