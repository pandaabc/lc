package lc;

import java.util.Stack;

public class Q224BasicCalculator {

    public static void main(String[] args) {
        Q224BasicCalculator q224BasicCalculator = new Q224BasicCalculator();
        //System.out.println(q224BasicCalculator.calculate("1+2"));
        //System.out.println(q224BasicCalculator.calculate("1-2"));
        System.out.println(q224BasicCalculator.calculate("(1+(1-2)+3)"));
    }

    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        s = s.replace(" ", "");

        return computeUnit(s);
    }

    public int computeUnit(String s) {

        Stack<String> stack = new Stack<>();
        int res = 0;

        int p0 = 0;

        for (int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if (c == '(') {
                int i1 = findClosingIdx(s.substring(i));
                int pr = computeUnit(s.substring(i + 1, i + i1));
                stack.push(String.valueOf(pr));
                if (stack.size() > 1) {
                    calculate(stack);
                }
                i += i1;
            } else if (c == '+' || c == '-') {
                stack.push(String.valueOf(c));
            } else {
                for (int j = i + 1; j <= s.length(); j ++) {
                    if (j == s.length() || s.charAt(j) > '9' || s.charAt(j) < '0') {
                        stack.push(s.substring(i, j));
                        if (stack.size() > 1) {
                            calculate(stack);
                        }
                        i = j - 1;
                        break;
                    }
                }
            }
        }
        return Integer.valueOf(stack.pop());
    }

    private void calculate(Stack<String> stack) {

        int n2 = Integer.valueOf(stack.pop());
        String ops = stack.pop();
        int n1 = Integer.valueOf(stack.pop());
        if (ops.equals("+")) {
            stack.push(String.valueOf(n1 + n2));
        } else {
            stack.push(String.valueOf(n1 - n2));
        }

    }

    public int findClosingIdx(String s) {
        int cnt = 0;
        for (int j = 0; j < s.length(); j ++) {
            if (s.charAt(j) == '(') {
                cnt ++;
            } else if (s.charAt(j) == ')') {
                cnt --;
                if (cnt == 0) {
                    return j;
                }
            }
        }
        return -1;
    }

}
