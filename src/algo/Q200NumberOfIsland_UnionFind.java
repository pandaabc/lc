package algo;


import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 */
public class Q200NumberOfIsland_UnionFind {

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int[] islandNum = new int[grid.length * grid[0].length];
        for (int i = 0; i < islandNum.length; i ++) {
            islandNum[i] = -1;
        }
        int x = grid[0].length;
        for (int i = 0; i < grid.length; i ++) {
            for (int j = 0; j < grid[0].length; j ++) {

                if (grid[i][j] == '1') {
                    int idx = i * x + j;
                    islandNum[idx] = idx;
                    if (i > 0 && grid[i-1][j] == '1') {
                        union(i - 1, j, i, j, islandNum, x);
                    }
                    if (j > 0 && grid[i][j-1] == '1') {
                        union(i, j - 1, i, j, islandNum, x);
                    }
                }

            }
        }

        Set<Integer> roots = new HashSet<>();
        for (int i = 0; i < islandNum.length; i ++) {
            if (islandNum[i] >= 0) {
                roots.add(findRoot(i, islandNum));
            }
        }

        return roots.size();
    }

    public int findRoot(int i, int j, int[] islandNnum, int x) {
        int idx = i * x + j;
        return findRoot(idx, islandNnum);
    }

    public int findRoot(int idx, int[] islandNnum) {
        while (islandNnum[idx] != idx) {
            islandNnum[idx] = islandNnum[islandNnum[idx]];  // this is the key step for path compression !!!
            idx = islandNnum[idx];
        }
        return idx;
    }

    public void union(int i0, int j0, int i1, int j1, int[] islandNum, int x) {

        int root0 = findRoot(i0, j0, islandNum, x);
        int root1 = findRoot(i1, j1, islandNum, x);
        islandNum[root0] = root1;

    }

}
