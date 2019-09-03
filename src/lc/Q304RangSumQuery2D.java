package lc;

public class Q304RangSumQuery2D {

    int[][] sum;
    public Q304RangSumQuery2D(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        sum = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i ++) {
            for (int j = 0; j < matrix[0].length; j ++) {
                sum[i][j] = matrix[i][j] + getMinorRegion(i - 1, j) + getMinorRegion(i, j - 1) - getMinorRegion(i - 1, j - 1);
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (sum == null || sum.length == 0 || sum[0].length == 0) {
            return 0;
        }
        return sum[row2][col2] - getMinorRegion(row1 - 1, col2) - getMinorRegion(row2, col1 - 1) + getMinorRegion(row1 - 1, col1 - 1);
    }

    private int getMinorRegion(int i, int j) {
        if (i < 0 || j < 0) {
            return 0;
        }
        return sum[i][j];
    }

}
