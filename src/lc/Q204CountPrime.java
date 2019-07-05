package lc;

import java.util.ArrayList;
import java.util.List;

public class Q204CountPrime {

    public int countPrimes(int n) {

        if (n < 2) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        List<Integer> prime = new ArrayList<>();
        prime.add(2);

        for (int i = 3; i <= n; i ++) {
            boolean isPrime = prime.stream().noneMatch(e -> n % 2 == 0);
            if (isPrime) {
                prime.add(i);
            }
        }

        return prime.size();

    }

}
