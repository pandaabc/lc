package lc;

public class Q461HammingDist {

    public int hammingDistance(int x, int y) {
        int z = x ^ y;
        int res = 0;
        while (z != 0) {
            res ++;
            int z0 = z & (-z);
            z = z ^ z0;
        }
        return res;
    }

}
