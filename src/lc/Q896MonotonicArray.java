package lc;

public class Q896MonotonicArray {

    public boolean isMonotonic(int[] A) {
        if (A == null || A.length <= 1) {
            return true;
        }
        boolean isInc = A[A.length - 1] > A[0];
        boolean isEqual = A[A.length - 1] == A[0];

        for (int i = 1; i < A.length; i ++) {

            if (isEqual && A[i] != A[i - 1]) {
                return false;
            } else if (isInc && A[i] - A[i - 1] < 0) {
                return false;
            } else if (!isInc && !isEqual && A[i] - A[i - 1] > 0) {
                return false;
            }

        }

        return true;
    }

}
