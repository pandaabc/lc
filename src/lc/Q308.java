package lc;

public class Q308 {

    int[][] rowSum;
    int[][] matrix;

    public Q308(int[][] matrix) {

        rowSum = new int[matrix.length][matrix[0].length];
        this.matrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i ++) {
            for (int j = 0; j < matrix[i].length; j ++) {
                update(i, j, matrix[i][j]);
            }
        }

    }

    public void update(int row, int col, int val) {

        int diff = val - matrix[row][col];
        matrix[row][col] = val;

        for (int i = col; i < rowSum[row].length; i ++) {
            rowSum[row][i] += diff;
        }

    }

    public int sumRegion(int row1, int col1, int row2, int col2) {

        int sum = 0;
        for (int i = row1; i <= row2; i ++) {
            sum += rowSum[i][col2] - col1 == 0 ? 0 : rowSum[i][col1 - 1];
        }
        return sum;

    }

}
