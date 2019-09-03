package algo;

/**
 * Typical method to resolve two text issues.  For example the below questions:
 * edit distance - https://leetcode.com/problems/edit-distance/
 * regex matching - https://leetcode.com/problems/regular-expression-matching/
 * wildcard matching - https://leetcode.com/problems/wildcard-matching/
 * shortest common supersequence (solution involves a LCS step) - https://leetcode.com/problems/shortest-common-supersequence
 * Longest Palindrome Subsequence (could be solved using LCS) - https://leetcode.com/problems/longest-palindromic-subsequence/
 */
public class Q1143LongestCommonSubsequence_Dynamic_Program {

    public int longestCommonSubsequence(String text1, String text2) {

        if (text1 == null || text2 == null || text1.isEmpty() || text2.isEmpty()) {
            return 0;
        }

        int[][] lcs = new int[text1.length() + 1][text2.length() + 1];

        for (int i = 0; i < text1.length(); i ++) {
            for (int j = 0; j < text2.length(); j ++) {
                int i0 = i + 1;
                int j0 = j + 1;
                if (text1.charAt(i) == text2.charAt(j)) {
                    lcs[i0][j0] = lcs[i][j] + 1;
                } else {
                    lcs[i0][j0] = Math.max(lcs[i][j0], lcs[i0][j]);
                }
            }
        }

        return lcs[text1.length()][text2.length()];
    }

}
