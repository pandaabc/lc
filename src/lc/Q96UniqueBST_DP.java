package lc;

public class Q96UniqueBST_DP {
    public int numTrees(int n) {
        if (n <= 1) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i ++) {
            int sum = 0;
            for (int j = 0; j < i; j ++) {
                sum += dp[j] * dp[i - j - 1]; // minus 1 because we need to exclude parent node
            }
            dp[i] = sum;
        }
        return dp[n];
    }
}
