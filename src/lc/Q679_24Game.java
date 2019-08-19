package lc;

import java.util.*;
import java.util.stream.Collectors;

public class Q679_24Game {

    public boolean judgePoint24(int[] nums) {
        return tryCombination(Arrays.stream(nums).boxed().map(e -> e * 1.).collect(Collectors.toList()));
    }

    private boolean tryCombination(List<Double> numsLeft) {

        if (numsLeft.size() == 1) {
            return Math.abs(numsLeft.get(0) - 24.) < 0.00001;
        }

        for (int i = 0; i < numsLeft.size(); i ++) {
            for (int j = 0; j < numsLeft.size(); j ++) {

                if (i == j) {
                    continue;
                }
                List<Double> newList = new ArrayList<>();
                for (int m = 0; m < numsLeft.size(); m ++) {
                    if (m != i && m != j) {
                        newList.add(numsLeft.get(m));
                    }
                }
                for (int k = 0; k < 4; k ++) {
                    double num1 = numsLeft.get(i);
                    double num2 = numsLeft.get(j);
                    if (k == 0) {
                        newList.add(num1 + num2);
                    } else if (k == 1) {
                        newList.add(num1 - num2);
                    } else if (k == 2) {
                        newList.add(num1 * num2);
                    } else if (k == 3 && num2 != 0) {
                        newList.add(num1 / num2);
                    } else {
                        continue;
                    }
                    if (tryCombination(newList)) {
                        return true;
                    }
                    newList.remove(newList.size() - 1);
                }

            }
        }

        return false;
    }


}
