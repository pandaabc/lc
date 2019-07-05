package lc;

/**
 * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 */
public class Q222CountCompleteTree {

    public int countNodes(TreeNode root) {

        if (root ==  null) {
            return 0;
        }

        int leftD = findLeftDepth(root);
        int rightD = findRightDepth(root);

        if (leftD == rightD) {
            return (int)Math.pow(2, leftD) - 1;
        }

        return countNodes(root.left) + countNodes(root.right) + 1;

    }

    private int findLeftDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + findLeftDepth(node.left);
    }

    private int findRightDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + findRightDepth(node.right);
    }


}
