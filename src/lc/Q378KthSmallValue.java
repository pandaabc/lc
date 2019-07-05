package lc;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Q378KthSmallValue {

    public int kthSmallest(int[][] matrix, int k) {
        SortedMap<Integer, Integer> numCnt = new TreeMap<>();
        for (int i = 0; i < matrix.length; i ++) {
            for (int j = 0; j < matrix[0].length; j ++) {
                numCnt.compute(matrix[i][j], (k0, v) -> v == null ? 0 : v + 1);
            }
        }
        int sum = 0;
        for (Map.Entry<Integer, Integer> e : numCnt.entrySet()) {
            sum += e.getValue();
            if (sum >= k) {
                return e.getKey();
            }
        }
        return Integer.MIN_VALUE;
    }

}
