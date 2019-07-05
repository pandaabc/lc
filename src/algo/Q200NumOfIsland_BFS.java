package algo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 */
public class Q200NumOfIsland_BFS {

    public int numIslands(char[][] grid) {
        // edge check
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        // BFS
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int count = 0;

        for (int i = 0; i < grid.length; i ++) {
            for (int j = 0; j < grid[0].length; j ++) {

                if (visited[i][j] || grid[i][j] != '1') {
                    continue;
                }

                count ++;
                BFS(grid, visited, i, j);
            }
        }
        return count;
    }

    public void BFS(char[][] grid, boolean[][] visited, int i, int j) {
        Queue<Integer> queueX = new LinkedList<>();
        Queue<Integer> queueY = new LinkedList<>();
        queueX.offer(i);
        queueY.offer(j);
        visited[i][j] = true;

        while (!queueX.isEmpty()) {

            int size = queueX.size();
            for (int x = 0; x < size; x ++) {
                int xt = queueX.poll();
                int yt = queueY.poll();


                if (yt > 0 && grid[xt][yt - 1] == '1' && !visited[xt][yt - 1]) {
                    queueX.offer(xt);
                    queueY.offer(yt - 1);
                    visited[xt][yt - 1] = true;
                }
                if (yt + 1 < grid[0].length && grid[xt][yt + 1] == '1' && !visited[xt][yt + 1]) {
                    queueX.offer(xt);
                    queueY.offer(yt + 1);
                    visited[xt][yt + 1] = true;
                }
                if (xt > 0 && grid[xt - 1][yt] == '1' && !visited[xt - 1][yt]) {
                    queueX.offer(xt - 1);
                    queueY.offer(yt);
                    visited[xt - 1][yt] = true;
                }
                if (xt + 1 < grid.length && grid[xt + 1][yt] == '1' && !visited[xt + 1][yt]) {
                    queueX.offer(xt + 1);
                    queueY.offer(yt);
                    visited[xt + 1][yt] = true;
                }
            }

        }

    }

}
