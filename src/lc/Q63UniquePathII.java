package lc;

public class Q63UniquePathII {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0){
            return 0;
        }

        for (int i = 0; i < obstacleGrid.length; i ++) {
            for (int j = 0; j < obstacleGrid[0].length; j ++) {

                if (obstacleGrid[i][j] == 1) {
                    obstacleGrid[i][j] = 0;
                } else {
                    if (i == 0 && j == 0) {
                        obstacleGrid[i][j] = 1;
                        continue;
                    }
                    int cnt = 0;
                    if (i > 0) {
                        cnt += obstacleGrid[i - 1][j];
                    }
                    if (j > 0) {
                        cnt += obstacleGrid[i][j - 1];
                    }
                    obstacleGrid[i][j] = cnt;
                }

            }
        }

        return obstacleGrid[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
    }

}
