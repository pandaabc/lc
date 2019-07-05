package lc;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 *
 * Note
 */
public class Q230 {

    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        findKth(root, k, list);
        return list.get(k-1);
    }

    /**
     * int[]: int[0] the cur rank
     * int[1]: left size
     * int[2]: right size
     * int[3]: parent left size
     * * @param node
     * @param k
     * @return
     */
    private void findKth(lc.TreeNode node, int k, List<Integer> list) {
        if (list.size() == k) {
            return;
        }

        if (node == null) {
            return;
        }

        findKth(node.left, k, list);
        list.add(node.val);
        findKth(node.right, k, list);
    }



}
