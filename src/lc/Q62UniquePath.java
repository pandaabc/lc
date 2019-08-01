package lc;

public class Q62UniquePath {

    public int uniquePaths(int m, int n) {
        int[][] ups = new int[m][n];
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                if (i == 0 && j == 0) {
                    ups[0][0] = 1;
                    continue;
                }
                int fromUp = i == 0 ? 0 : ups[i - 1][j];
                int fromLeft = j == 0 ? 0 : ups[i][j - 1];
                ups[i][j] = fromUp + fromLeft;
            }
        }
        return ups[m - 1][n - 1];
    }

}
