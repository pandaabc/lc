package lc;

public class Q875SmallestSubtreeWithAllDN {

    public TreeNode subtreeWithAllDeepest(TreeNode root) {

        if (root ==  null) {
            return root;
        }
        int ld = getDepth(root.left, 0);
        int rd = getDepth(root.right, 0);
        if (ld == rd) {
            return root;
        }
        return ld > rd ? subtreeWithAllDeepest(root.left) : subtreeWithAllDeepest(root.right);

    }

    private int getDepth(TreeNode node, int depth) {
        if (node == null) {
            return depth;
        }
        return Math.max(getDepth(node.left, depth + 1), getDepth(node.right, depth + 1));
    }

}
