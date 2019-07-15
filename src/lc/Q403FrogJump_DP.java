package lc;

import java.util.*;
import java.util.stream.Collectors;

public class Q403FrogJump_DP {

    public boolean canCross(int[] stones) {
        Map<Integer, Set<Integer>> stoneSteps = new HashMap<>();
        Set<Integer> allStones = Arrays.stream(stones).boxed().collect(Collectors.toSet());
        stoneSteps.computeIfAbsent(0, k -> new HashSet()).add(1);
        int lastStone = stones[stones.length - 1];
        for (int i = 0; i < stones.length; i ++) {
            int stoneNumber = stones[i];
            Set<Integer> possibleSteps = stoneSteps.get(stoneNumber);
            for (int step : possibleSteps) {
                for (int j = -1; j <= 1; j ++) {
                    if (j + step <= 0 || !allStones.contains(j + step + stoneNumber)) {
                        continue;
                    }
                    if (j + step + stoneNumber == lastStone) {
                        return true;
                    }
                    stoneSteps.computeIfAbsent(step + j + stoneNumber, k -> new HashSet<>()).add(step + j);
                }
            }
        }
        return false;
    }
}
