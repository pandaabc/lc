package lc;

import java.util.HashMap;
import java.util.Map;

public class Q337HouseRobber {

    public int rob(TreeNode root) {
        Map<TreeNode, int[]> allPossible = new HashMap<>();
        return rob(root, false, allPossible);
    }

    public int rob(TreeNode node, boolean parentRob, Map<TreeNode, int[]> allPossible) {

        if (node == null) {
            return 0;
        }
        int idx = parentRob ? 1 : 0;
        if (allPossible.containsKey(node) && allPossible.get(node)[idx] != -1) {
            return allPossible.get(node)[idx];
        }

        // parent robbed, then we cant rob here
        int total = 0;
        if (parentRob) {
            total += rob(node.left, false, allPossible);
            total += rob(node.right, false, allPossible);
        } else {
            int opt1 = node.val + rob(node.left, true, allPossible) + rob(node.right, true, allPossible);
            int opt2 = rob(node.left, false, allPossible) + rob(node.right, false, allPossible);
            total = Math.max(opt1, opt2);
        }

        if (!allPossible.containsKey(node)) {
            allPossible.put(node, new int[]{-1, -1});
        }
        allPossible.get(node)[idx] = total;
        return total;

    }

}
