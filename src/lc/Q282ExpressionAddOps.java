package lc;

import java.util.*;

public class Q282ExpressionAddOps {
    class Solution {
        Set<String> ops = new HashSet<>();
        public List<String> addOperators(String num, int target) {
            if (num == null || num.length() == 0) {
                return new ArrayList<>();
            }
            List<String> result = new ArrayList<>();
            addOps(num, result, target, "");
            return result;
        }

        private void addOps(String num, List<String> res, int target, String prefix) {
            //System.out.println(prefix);
            if (num.isEmpty() && evaluate(prefix, target)) {
                res.add(prefix);
                return;
            }

            for (int i = 0; i < num.length(); i ++) {
                long number = Long.parseLong(num.substring(0, i + 1));
                if (num.charAt(0) == '0' && i > 0) {
                    return;
                }
                if (prefix.length() > 0) {
                    for (String op : getOps()) {
                        addOps(num.substring(i + 1), res, target, prefix + op + number);
                    }
                } else {
                    addOps(num.substring(i + 1), res, target, prefix + number);
                }
            }

        }

        private boolean evaluate (String prefix, int target) {

            Stack<String> stack = new Stack<>();
            int p0 = prefix.length();
            for (int i = prefix.length() - 1; i >= 0; i --) {
                if (i == 0) {
                    stack.push(prefix.substring(i, p0));
                } else if (prefix.charAt(i) < '0' || prefix.charAt(i) > '9') {
                    stack.push(prefix.substring(i + 1, p0));
                    stack.push(prefix.substring(i, i + 1));
                    p0 = i;
                }
            }
            //System.out.println(prefix);
            //System.out.println(stack);
            while (stack.size() > 1) {
                long num1 = Long.parseLong(stack.pop());
                String op = stack.pop();
                long num2 = Long.parseLong(stack.pop());
                if (op.equals("*")) {
                    stack.push(String.valueOf(num1 * num2));
                } else if (!stack.isEmpty() && "*".equals(stack.peek())) {
                    stack.pop();
                    stack.push(String.valueOf(num2 * Long.parseLong(stack.pop())));
                    stack.push(op);
                    stack.push(String.valueOf(num1));
                } else if (op.equals("+")) {
                    stack.push(String.valueOf(num1 + num2));
                } else {
                    stack.push(String.valueOf(num1 - num2));
                }
            }
            return Long.parseLong(stack.pop()) == target;
        }

        private Set<String> getOps() {
            if (ops.isEmpty()) {
                ops.add("+");
                ops.add("-");
                ops.add("*");
            }
            return ops;
        }
    }
}
