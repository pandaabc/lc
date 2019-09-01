package lc;

import java.util.*;

public class Q282ExpressionAddOps {
    class Solution {
        public List<String> addOperators(String num, int target) {
            if (num == null || num.length() == 0) {
                return new ArrayList<>();
            }
            List<String> eqns = new ArrayList<>();
            tryOps(num, "", new ArrayDeque<>(), new ArrayDeque<>(), target, eqns);
            return eqns;
        }

        private void tryOps(String num, String prefix, Deque<Integer> nums, Deque<Character> ops, int target, List<String> eqns) {
            
            if (num.isEmpty()) {
                if (!nums.isEmpty()) {
                    if (nums.size() == 1 && nums.poll() ==  target) {
                        eqns.add(prefix);
                    }
                    if (nums.size() > 1 && doOp(nums.pollFirst(), nums.pollFirst(), ops.pollFirst()) == target) {
                        eqns.add(prefix);
                    }
                }

                return;
            }

            for (int i = 1; i <= num.length() && i < 11; i ++) {
                long n0 = Long.parseLong(num.substring(0, i));
                if (n0 > Integer.MAX_VALUE || String.valueOf(n0).length() < i) {
                    break;
                }
                int n = (int) n0;
                for (int j = 0; j < 3; j ++) {
                    Deque<Integer> nums1 = new ArrayDeque<>(nums);
                    Deque<Character> ops1 = new ArrayDeque<>(ops);
                    if (j == 0) {
                        addNumberAndOps(nums1, ops1, n, j);
                        tryOps(num.substring(i), prefix.isEmpty() ? prefix + n : prefix + "+"+n, nums1, ops1, target, eqns);
                        if (prefix.isEmpty()) {
                            break;
                        }
                    } else if (j == 1) {
                        addNumberAndOps(nums1, ops1, n, j);
                        tryOps(num.substring(i), prefix.isEmpty() ? prefix + n : prefix + "-"+n, nums1, ops1, target, eqns);
                    } else {
                        addNumberAndOps(nums1, ops1, n, j);
                        tryOps(num.substring(i), prefix.isEmpty() ? prefix + n : prefix + "*"+n, nums1, ops1, target, eqns);
                    }
                }
            }
        }

        private void addNumberAndOps(Deque<Integer> nums, Deque<Character> ops, int n, int opNum) {
            if (nums.size() < 1) {
                nums.offerLast(n);
                return;
            }
            if (nums.size() < 2) {
                nums.offerLast(n);
                ops.offerLast(opNum == 0 ? '+' : opNum == 1 ? '-' : '*');
                return;
            }
            if (opNum == 0) {
                nums.offerFirst(doOp(nums.pollFirst(), nums.pollFirst(), ops.pollFirst()));
                ops.offerLast('+');
                nums.offerLast(n);
            } else if (opNum == 1) {
                nums.offerFirst(doOp(nums.pollFirst(), nums.pollFirst(), ops.pollFirst()));
                ops.offerLast('-');
                nums.offerLast(n);
            } else {
                nums.offerLast(doOp(nums.pollLast(), n, '*'));
            }
        }


        private int doOp(int num1, int num2, char op) {
            if (op == '+') {
                return num1 + num2;
            }
            if (op == '-') {
                return num1 - num2;
            }
            return num1 * num2;
        }

    }
}
