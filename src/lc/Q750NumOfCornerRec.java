package lc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Q750NumOfCornerRec {

    public int countCornerRectangles(int[][] grid) {

        if (grid == null || grid.length < 2 || grid[0].length < 2) {
            return 0;
        }

        Set<Integer>[][] rows = new HashSet[grid.length][grid[0].length];
        Set<Integer>[][] cols = new HashSet[grid.length][grid[0].length];

        int cnt = 0;
        for (int i = 0; i < grid.length; i ++) {
            for (int j = 0; j < grid[0].length; j ++) {

                rows[i][j] = new HashSet<>();
                cols[i][j] = new HashSet<>();

                if (grid[i][j] == 1 && i > 0) {
                    cols[i][j].addAll(cols[i - 1][j]);
                } else if (grid[i][j] == 0 && i > 0) {
                    cols[i][j] = cols[i - 1][j];
                }

                if (grid[i][j] == 1 && j > 0) {
                    rows[i][j].addAll(rows[i][j - 1]);
                } else if (grid[i][j] == 0 && j > 0) {
                    rows[i][j] = rows[i][j - 1];
                }

                if (grid[i][j] == 1) {

                    for (int j0 : rows[i][j]) {
                        Set<Integer> col0 = cols[i][j0];
                        for (int i0 : cols[i][j]) {
                            if (col0.contains(i0)) {
                                cnt ++;
                            }
                        }
                    }

                    rows[i][j].add(j);
                    cols[i][j].add(i);
                }
            }
        }

        //System.out.println(Arrays.deepToString(rows));
        //System.out.println(Arrays.deepToString(cols));
        return cnt;

    }

    public int countCornerRectangles2(int[][] grid) {

        if (grid == null || grid.length < 2 || grid[0].length < 2) {
            return 0;
        }

        Map<Integer, Integer> cntMap = new HashMap<>();
        int cnt = 0;
        for (int i = 0; i < grid.length; i ++) {
            for (int j = 0; j < grid[0].length; j ++) {
                for (int m = j + 1; m < grid[0].length; m ++) {
                    int key =  grid.length * j + m;
                    if (grid[i][j] == 0 || grid[i][m] == 0) {
                        cntMap.computeIfAbsent(key, k -> -1);
                    } else {
                        int c = cntMap.compute(key, (k, val) -> val == null ? 0 : val + 1);
                        cnt += c;
                    }

                }
            }
        }

        //System.out.println(Arrays.deepToString(rows));
        //System.out.println(Arrays.deepToString(cols));
        return cnt;
    }

}
