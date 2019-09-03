package lc;

public class Q42TrappinRainWater {

    public int trap(int[] height) {

        if (height == null || height.length <= 1) {
            return 0;
        }

        int p0 = 0;
        int p1 = height.length - 1;
        int lmin = height[0];
        int rmin = height[p1];
        int total = 0;

        while(p0 < p1) {

            int l = height[p0];
            int r = height[p1];
            if (l < r) {
                if (l >= lmin) {
                    lmin = l;
                } else {
                    total += lmin - l;
                }
                p0 ++;
            } else {
                if (r >= rmin) {
                    rmin = r;
                } else {
                    total += rmin - r;
                }
                p1 --;
            }
        }

        return total;

    }
}
