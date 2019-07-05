package lc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Q311 {

    /**
     * Given two sparse matrices A and B, return the result of AB.
     *
     * You may assume that A's column number is equal to B's row number.
     *
     * For sparse matrices, we just need to store the location of the index that with numbers, then do mulitpliation.
     * @param A
     * @param B
     * @return
     */
    public int[][] multiply(int[][] A, int[][] B) {
        int[][] res = new int[A.length][B[0].length];
        // using hash map to store the locations with numbers
        Map<Integer, Set<Integer>> rowInd = new HashMap<>();
        Map<Integer, Set<Integer>> colInd = new HashMap<>();
        // initialize the map
        initializeMap(A, rowInd);
        initializeMapCol(B, colInd);
        // compute with assistance of map
        for (Map.Entry<Integer, Set<Integer>> entryR : rowInd.entrySet()) {
            for (Map.Entry<Integer, Set<Integer>> entryC : colInd.entrySet()) {
                int sum = 0;
                for (int i : entryR.getValue()) {
                    if (entryC.getValue().contains(i)) {
                        sum += A[entryR.getKey()][i] * B[i][entryC.getKey()];
                    }
                }
                res[entryR.getKey()][entryC.getKey()] = sum;
            }
        }
        return res;
    }

    private void initializeMap (int[][] A, Map<Integer, Set<Integer>> rowInd) {
        for (int i = 0; i < A.length; i ++) {
            for (int j = 0; j < A[i].length; j ++) {
                if (A[i][j] != 0) {
                    rowInd.computeIfAbsent(i, k -> new HashSet<>()).add(j);
                }
            }
        }
    }

    private void initializeMapCol (int[][] B, Map<Integer, Set<Integer>> colInd) {
        for (int i = 0; i < B.length; i ++) {
            for (int j = 0; j < B[i].length; j ++) {
                if (B[i][j] != 0) {
                    colInd.computeIfAbsent(j, k -> new HashSet<>()).add(i);
                }
            }
        }
    }

}
