package lc;

import java.util.ArrayDeque;
import java.util.Deque;
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

    private int computeUnit(String s) {
        Deque<String> queue = new ArrayDeque<>();
        int p0 = 0;
        for (int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if (c == '(' || c == ')' || c > '9' || c < '0') {
                if (p0 != i) {
                    queue.offerLast(s.substring(p0, i));
                }
                queue.offerLast(String.valueOf(c));
                p0 = i + 1;
            } else if (i == s.length() - 1) {
                queue.offerLast(s.substring(p0, i + 1));
            }
        }
        return computeEqn(queue);
    }

    private int computeEqn2(Deque<String> queue) {

        Deque<Integer> nums = new ArrayDeque<>();
        String ops = null;
        while (!queue.isEmpty()) {
            String cur = queue.pollFirst();
            if (cur.equals("(")) {
                computeEqn2(queue);
            } else if (cur.equals(")")) {
                break;
            } else if (cur.equals("+") || cur.equals("-")) {
                ops = cur;
            } else {
                nums.offerLast(Integer.parseInt(cur));
                if (ops != null) {
                    int sign = ops.equals("-") ? -1 : 1;
                    nums.offerLast(nums.pollLast() * sign + nums.pollLast());
                    ops = null;
                }
            }
        }
        queue.offerFirst(String.valueOf(nums.pollFirst()));
        return nums.pollFirst();
    }

    private int computeEqn(Deque<String> queue) {
        Deque<Integer> nums = new ArrayDeque<>();
        Deque<Character> ops = new ArrayDeque<>();

        while (!queue.isEmpty()) {
            String str = queue.pollFirst();
            //System.out.println(str + ": " + nums + " : "+ ops);
            if (str.equals("(")) {
                ops.offerLast('(');
            } else if (str.equals(")")) {
                calc(nums, ops);
                ops.pollLast();
                calc(nums, ops);
            } else if (str.equals("+") || str.equals("-")) {
                ops.offerLast(str.charAt(0));
            } else {
                nums.offerLast(Integer.parseInt(str));
                calc(nums, ops);
            }
            //System.out.println("After");
            //System.out.println(nums);
            //System.out.println(ops);
        }

        return nums.pollFirst();
    }

    private void calc(Deque<Integer> nums, Deque<Character> ops) {

        if (ops.isEmpty() || ops.peekLast() == '(') {
            return;
        }
        int n2 = nums.pollLast();
        int n1 = nums.pollLast();
        char op = ops.pollLast();
        nums.offerLast(calc(n1, n2, op));
    }

    private int calc (int n1, int n2, char ops) {
        if (ops == '+') {
            return n1 + n2;
        }
        return n1 - n2;
    }
}