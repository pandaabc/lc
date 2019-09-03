package algo;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

/**
 * the key is to move 2 steps each time
 */
public class MazeGeneration {

    public static void main(String[] args) {
        MazeGeneration mg = new MazeGeneration();
        int[][] mz = mg.generateMaze(10, 10, new int[]{0, 0}, new int[]{9, 9});
        for (int i = 0; i < mz.length; i ++ ){
            System.out.println(Arrays.toString(mz[i]));
        }
    }

    public int[][] generateMaze(int M, int N, int[] start, int[] end) {
        int[][] maze = new int[M][N];
        maze[start[0]][start[1]] = 1;
        buildMaze(maze, start[0], start[1]);
        return maze;
    }

    private void buildMaze(int[][] maze, int i, int j) {


        int dice = new Random().nextInt(4);
        for (int r = 0; r < 4; r ++) {
            int dir = (r + dice) % 4;
            int[] nextStep = canMove(maze, i, j, dir);
            if (nextStep != null) {
                buildMaze(maze, nextStep[0], nextStep[1]);
            }
        }

    }

    private int[] canMove(int[][] maze, int i, int j, int dir) {

        // left
        if (dir == 0 && j >= 2 && maze[i][j - 2] == 0) {
            maze[i][j - 1] = 1;
            maze[i][j - 2] = 1;
            return new int[]{i, j - 2};
        }

        // right
        if (dir == 1 && j < maze[0].length - 2 && maze[i][j + 2] == 0) {
            maze[i][j + 1] = 1;
            maze[i][j + 2] = 1;
            return new int[]{i, j + 2};
        }

        // up
        if (dir == 2 && i >= 2 && maze[i - 2][j] == 0) {
            maze[i - 1][j] = 1;
            maze[i - 2][j] = 1;
            return new int[]{i - 2, j};
        }

        // down
        if (dir == 3 && i < maze.length - 2 && maze[i + 2][j] == 0) {
            maze[i + 1][j] = 1;
            maze[i + 2][j] = 1;
            return new int[]{i + 2, j};
        }

        return null;
    }

}
