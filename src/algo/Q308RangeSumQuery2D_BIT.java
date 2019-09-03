package algo;

public class Q308RangeSumQuery2D_BIT {
    int[][] bits;
    int[][] matrix;
    public Q308RangeSumQuery2D_BIT(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        bits = new int[matrix.length][matrix[0].length + 1];
        this.matrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i ++) {
            for (int j = 0; j < matrix[0].length; j ++) {
                update(i, j, matrix[i][j]);
            }
        }
        //System.out.println(Arrays.deepToString(bits));
    }

    public void update(int row, int col, int val) {
        int[] bit = bits[row];
        int delta = val - matrix[row][col];
        matrix[row][col] = val;
        //System.out.println(val +": "+delta);
        col += 1;
        while (col < bit.length) {
            bit[col] += delta;
            col += col & (- col);
        }
        //System.out.println(Arrays.toString(bit));
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {

        int sum = 0;
        for (int i = row1; i <= row2; i ++) {
            sum += getSum(i, col2) - getSum(i, col1) + matrix[i][col1];
        }
        return sum;
    }

    public int getSum(int row, int col) {
        int[] bit = bits[row];
        col += 1;
        int sum = 0;
        while (col > 0) {
            sum += bit[col];
            col -= col & (- col);
        }
        return sum;
    }
}
