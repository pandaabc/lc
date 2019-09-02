package lc;

public class Q29DivideTwoInteger {

    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }

        int sign = (dividend > 0 && divisor > 0 || dividend < 0 && divisor < 0) ? 1 : -1;

        dividend = dividend > 0 ? - dividend : dividend;
        divisor = divisor > 0 ? - divisor : divisor;
        int d = divisor;
        int m = 1;
        int res = 0;

        while (divisor >= dividend) {
            if (d >= dividend) {
                res -= m;
                dividend -= d;
                d += d;
                m += m;
            } else {
                d = divisor;
                m = 1;
            }
        }

        if (sign > 0 && res == Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }

        return sign < 0 ? res : - res;
    }

}
