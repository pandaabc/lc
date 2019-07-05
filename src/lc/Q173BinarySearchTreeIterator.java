package lc;

import java.util.Stack;

public class Q173BinarySearchTreeIterator {

    Stack<TreeNode> nodes = new Stack<>();
    public Q173BinarySearchTreeIterator(TreeNode root) {
        addNodes(root);
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode node = nodes.pop();
        int val = node.val;
        if (node.right != null) {
            addNodes(node.right);
        }
        return val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !nodes.isEmpty();
    }

    private void addNodes(TreeNode node) {
        while (node != null) {
            nodes.push(node);
            node = node.left;
        }
    }

}
