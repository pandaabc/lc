package lc;

import java.util.ArrayList;
import java.util.List;

public class Q986IntervalListIntersection {

    public int[][] intervalIntersection(int[][] A, int[][] B) {

        if (A == null || A.length == 0 || B == null || B.length == 0) {
            return new int[0][0];
        }

        List<int[]> result = new ArrayList<>();

        int p0 = 0;
        int p1 = 0;

        while (p0 < A.length && p1 < B.length) {
            if (A[p0][1] < B[p1][0]) {
                p0 ++;
            } else if (A[p0][0] > B[p1][1]) {
                p1 ++;
            } else {
                int[] a = A[p0];
                int[] b = B[p1];
                result.add(new int[]{Math.max(a[0], b[0]), Math.min(a[1], b[1])});

                if (a[1] < b[1]) {
                    p0 ++;
                } else {
                    p1 ++;
                }

            }

        }

        int[][] res = new int[result.size()][2];
        for (int i = 0; i < result.size(); i++) {
            res[i] = result.get(i);
        }
        return res;
    }

}
