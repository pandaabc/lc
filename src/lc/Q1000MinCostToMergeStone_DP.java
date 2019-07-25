package lc;

import java.util.stream.IntStream;

public class Q1000MinCostToMergeStone_DP {

    public int mergeStones(int[] stones, int K) {
        int[] minCostWithStone = new int[stones.length];

        for (int i = 0; i < stones.length; i ++) {

            if (i < K - 1) {
                minCostWithStone[i] = -1;
            } else {
                int withI = IntStream.range(i - K + 1, i + 1).map(e -> stones[e]).sum();
                
            }

        }
        return 0;
    }

}
