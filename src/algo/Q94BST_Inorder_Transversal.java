package algo;

import com.sun.source.tree.Tree;
import lc.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;


public class Q94BST_Inorder_Transversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        pushLeft(root, queue);

        while (!queue.isEmpty()) {
            TreeNode node = queue.pollFirst();
            list.add(node.val);
            pushLeft(node.right, queue);
        }
        return list;
    }

    private void pushLeft(TreeNode node, Deque<TreeNode> queue) {

        while (node != null) {
            queue.offerFirst(node);
            node = node.left;
        }

    }
}
