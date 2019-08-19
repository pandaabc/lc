package lc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * this question equals to:
 * Find out the number of islands (union find)
 * Then... simply use the total stones minus the number of island.
 * As each island will remains last one to be not able to be removed.
 */
public class Q947MostStonesRemoved {

    public int removeStones(int[][] stones) {
        Map<Integer, Set<Integer>> rows = new HashMap<>();
        Map<Integer, Set<Integer>> cols = new HashMap<>();

        int len = 0;
        for (int i = 0; i < stones.length; i ++) {
            rows.computeIfAbsent(stones[i][0], k -> new HashSet<>()).add(stones[i][1]);
            cols.computeIfAbsent(stones[i][1], k -> new HashSet<>()).add(stones[i][0]);
            len = Math.max(len, stones[i][1] + 1);
        }

        Set<Integer> tried = new HashSet<>();
        //System.out.println(len);
        int numIs = 0;
        for (int i = 0; i < stones.length; i ++) {
            if (!tried.contains(getIdx(stones[i][0], stones[i][1], len))) {
                dfs(stones, stones[i][0], stones[i][1], tried, len, rows, cols);
                //System.out.println(tried);
                numIs ++;
            }
        }
        return stones.length - numIs;
    }

    private int dfs(int[][] stones, int i, int j, Set<Integer> tried, int length, Map<Integer, Set<Integer>> rows, Map<Integer, Set<Integer>> cols) {
        int idx = getIdx(i, j, length);
        if (tried.contains(idx)) {
            return 0;
        }
        tried.add(idx);
        Set<Integer> rowIdx = rows.get(i);
        Set<Integer> colIdx = cols.get(j);

        int num = 1;
        for (int r : rowIdx) {
            num += dfs(stones, i, r, tried, length, rows, cols);
        }
        for (int c : colIdx) {
            num += dfs(stones, c, j, tried, length, rows, cols);
        }
        return num;
    }

    private int getIdx (int i, int j, int length) {
        return i * length + j;
    }

    private int[] revertIdx (int idx, int length) {
        return new int[]{idx / length, idx % length};
    }
}
