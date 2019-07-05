package lc;

import java.util.HashMap;
import java.util.Map;

public class Q106ConstructBinarySearchTree {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length == 0 || postorder.length == 0) {
            return null;
        }

        Map<Integer, Integer> ioIdxNum = new HashMap<>();

        for (int i = 0; i < inorder.length; i ++) {
            ioIdxNum.put(inorder[i], i);
        }

        return construct(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1, ioIdxNum);

    }

    private TreeNode construct(int[] inorder, int[] postorder, int i, int j, int ip, int jp,
                               Map<Integer, Integer> ioIdxNum) {

        if (i > j || ip > jp) {
            return null;
        }

        TreeNode parentNode = new TreeNode(postorder[jp]);

        if (i == j || ip == jp) {
            return parentNode;
        }

        int ioIdx = ioIdxNum.get(postorder[jp]);
        System.out.println(i + " " + j + " " + ip + " " + jp + " " + ioIdx);
        int leftCnt = ioIdx - i;
        int rightCnt = j - ioIdx;
        if (leftCnt > 0) {
            parentNode.left = construct(inorder, postorder, i, ioIdx - 1, ip, ip + leftCnt - 1, ioIdxNum);
        }

        if (rightCnt > 0) {
            parentNode.right = construct(inorder, postorder, ioIdx + 1, j, ip + leftCnt, jp - 1, ioIdxNum);
        }

        return parentNode;
    }
}
