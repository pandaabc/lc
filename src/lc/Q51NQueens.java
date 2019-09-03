package lc;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q51NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> r0 = new ArrayList<>();
        backtracking(n, new ArrayDeque<>(), 0, r0);

        return r0;
    }

    private void backtracking(int n, Deque<int[]> points, int curIndex, List<List<String>> results) {
        //System.out.println(points.stream().map(e -> e[0] + ":" + e[1]).collect(Collectors.joining(";")));
        // check this
        if (n == points.size()) {
            results.add(convertToResult(new ArrayDeque<>(points), n));
            return;
        }

        for (int i = curIndex; i < n * n; i ++) {
            int[] index = getIndex(i, n);
            if (isOkToPut(points, index[0], index[1])) {
                points.offerLast(new int[]{index[0], index[1]});
                backtracking(n, points, index[0] * (n + 1) + 1, results);
                points.pollLast();
            }
        }

    }

    private int[] getIndex(int curIndex, int n) {
        return new int[]{curIndex/n, curIndex%n};
    }

    private boolean isOkToPut(Deque<int[]> points, int i, int j) {
        for (int[] p : points) {
            if (!isOkToPut(p, i, j)) {
                return false;
            }
        }
        return true;
    }

    private boolean isOkToPut(int[] p, int i, int j) {
        return i - p[0] != j - p[1] && i - p[0] != p[1] - j && i != p[0] && j != p[1];
    }

    private List<String> convertToResult (Deque<int[]> input, int n) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n * n; i ++) {
            sb.append(".");
        }

        for (int i = 0; i < n; i ++) {
            int[] cur = input.pollFirst();
            //System.out.println(Arrays.toString(cur));
            sb.setCharAt(cur[0] * n + cur[1], 'Q');
        }

        List<String> res = new ArrayList<>();
        for (int i = 0; i < n; i ++) {
            res.add(sb.substring(i * n, (i + 1) *n));
        }
        return res;
    }
}
