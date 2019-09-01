package lc;

import java.util.ArrayList;
import java.util.List;

public class Q257BinaryTreePath {
    class Solution {
        public List<String> binaryTreePaths(TreeNode root) {
            List<String> result = new ArrayList<>();
            getAllPath(root, new StringBuilder(), result);
            return result;
        }

        private void getAllPath(TreeNode node, StringBuilder sb, List<String> result) {

            if (node == null) {
                return;
            }

            int sbLen = sb.length();
            if (sbLen > 0) {
                sb.append("->");
            }
            sb.append(node.val);

            if (node.left == null && node.right == null) {
                result.add(sb.toString());
            }

            if (node.left != null) {
                getAllPath(node.left, sb, result);
            }

            if (node.right != null) {
                getAllPath(node.right, sb, result);
            }

            sb.delete(sbLen, sb.length());

        }
    }
}
