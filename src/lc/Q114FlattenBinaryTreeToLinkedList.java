package lc;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q114FlattenBinaryTreeToLinkedList {

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offerFirst(root);

        TreeNode pre = new TreeNode(0);

        while (!queue.isEmpty()) {

            TreeNode n = queue.pollFirst();
            pre.right = n;
            if (n.right != null) {
                queue.offerFirst(n.right);
            }
            if (n.left != null) {
                queue.offerFirst(n.left);
            }
            n.left = null;
            n.right = null;
            pre = n;

        }
    }

}
