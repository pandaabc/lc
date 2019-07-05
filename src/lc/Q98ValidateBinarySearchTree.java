package lc;

public class Q98ValidateBinarySearchTree {

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isValid(root.left, root.val, (long)Integer.MIN_VALUE - 1) && isValid(root.right, (long)Integer.MAX_VALUE + 1, root.val);
    }

    private boolean isValid(TreeNode node, long max, long min) {
        if (node == null) {
            return true;
        }
        if (node.val >= max || node.val <= min) {
            return false;
        }
        return isValid(node.left, node.val, min) && isValid(node.right, max, node.val);
    }

}
