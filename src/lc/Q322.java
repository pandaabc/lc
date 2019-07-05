package lc;

import java.util.Arrays;
import java.util.Comparator;

public class Q322 {

    /**
     * Dynamic Programming:
     * the idea is this:
     * We will start with amount 1, to amount.  Find out the min coins count for each amount.
     * When it comes to any amount, we can get the min coin count by:
     * Deduct the current amount by each coin value, find the minimum count from each.
     *
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChangeDP(int[] coins, int amount) {
        // edge case
        if (amount == 0) {
            return 0;
        }
        if (coins == null || coins.length == 0) {
            return -1;
        }
        // dp initialization
        int[] minCoins = new int[amount + 1];
        // iterate through all amount
        for (int i = 1; i <= amount; i ++) {
            int count = Integer.MAX_VALUE;
            // find the possible min count by back track each coin value
            for (int coin : coins) {
                if (i - coin >= 0) {
                    if (minCoins[i - coin] == Integer.MAX_VALUE) {
                        continue;
                    }
                    count = Math.min(1 + minCoins[i - coin], count);
                }
            }
            minCoins[i] = count;
        }
        // return the value
        return minCoins[amount] == Integer.MAX_VALUE ? -1 : minCoins[amount];

    }

    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        return getCoinCombinations(coins, amount);
    }

    // this approach wont work.  as it is possible to have 1 less large coin, but with less small coin combinations
    // we will have to iterate through all combinations
    private int getCoinCombinations (int[] coins, int amount) {
        int count = -1;
        for (int i = coins.length - 1; i >= 0; i ++) {
            if (coins[i] < amount) {
                int leftCnt = getCoinCombinations(coins, amount - coins[i]);
                if (leftCnt > 0) {
                    return leftCnt + 1;
                }
            }
        }
        return count;
    }

    /**
     * this will need to iterate all combinations
     * @param coins
     * @param amount
     * @param coinIndex
     * @return
     */
    private int getCoinComb(int[] coins, int amount, int coinIndex) {

        if (coinIndex == 0) {
            if (amount % coins[0] == 0) {
                return amount / coins[0];
            }
            return Integer.MAX_VALUE;
        }
        if (amount == 0) {
            return 0;
        }
        int count = Integer.MAX_VALUE;

        for (int i = 0; i <= amount / coins[coinIndex]; i ++) {
            int cnt = getCoinComb(coins, amount - coins[coinIndex] * i, coinIndex - 1);
            if (cnt < Integer.MAX_VALUE) {
                count = Math.min(cnt + i, count);
            }
        }

        return count;
    }


}
