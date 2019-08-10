package lc;

public class Q977SquaresOfASortedArray {

    public int[] sortedSquares(int[] A) {
        if (A == null || A.length == 0) {
            return A;
        }
        int[] res = new int[A.length];
        // look for the pivot
        int p = 0;
        for (int i = 0; i < A.length; i ++) {
            if (A[i] >= 0) {
                break;
            }
            p ++;
        }
        int p0 = p - 1;
        int p1 = p;
        int i = 0;
        while (p0 >= 0 || p1 < A.length){
            if (p0 < 0) {
                res[i] = A[p1] * A[p1];
                p1 ++;
            } else if (p1 >= A.length) {
                res[i] = A[p0] * A[p0];
                p0 --;
            } else {
                if (A[p0] * A[p0] > A[p1] * A[p1]) {
                    res[i] = A[p1] * A[p1];
                    p1 ++;
                } else {
                    res[i] = A[p0] * A[p0];
                    p0 --;
                }
            }

            i ++;
        }
        return res;
    }
}
