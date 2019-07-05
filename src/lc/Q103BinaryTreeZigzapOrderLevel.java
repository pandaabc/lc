package lc;

import java.util.*;

public class Q103BinaryTreeZigzapOrderLevel {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            int size = queue.size();
            List<Integer> levelList = new ArrayList<>();
            while (size > 0) {

                TreeNode node = queue.poll();
                levelList.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                size --;

            }
            res.add(levelList);

        }

        for (int i = 0; i < res.size(); i ++) {

            if (i % 2 == 1) {
                reverseList(res.get(i));
            }

        }

        return res;

    }

    private void reverseList(List<Integer> list) {
        for (int i = 0; i < list.size()/2; i ++) {
            int tmp = list.get(i);
            list.set(i, list.get(list.size() - 1 - i));
            list.set(list.size() - 1 - i, tmp);
        }
    }

}
