package algo;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q772BasicCalculatorII {

    public int calculate(String s) {
        s = s.replaceAll(" ", "");

        return (int)evaluate(s);
    }

    private long evaluate(String s) {
        if (s.charAt(0) == '-') {
            s = "0" + s;
        }
        Deque<Long> numD = new ArrayDeque<>();
        Deque<Character> opsD = new ArrayDeque<>();

        // build deques
        int p0 = 0;
        for (int i = 0; i < s.length(); i ++) {
            if (i == s.length() - 1) {
                numD.offerLast(Long.parseLong(s.substring(p0, i + 1)));
                break;
            }
            char c = s.charAt(i);
            if (c == '(') {
                //System.out.println(s + " " + i);
                int i1 = findClosingP(s, i + 1);
                numD.offerLast(evaluate(s.substring(i + 1, i1)));
                i = i1;
                p0 = i + 1;
            } else if (c < '0' || c > '9') {
                opsD.offerLast(c);
                if (p0 != i) {
                    numD.offerLast(Long.parseLong(s.substring(p0, i)));
                }
                p0 = i + 1;
            }
        }

        while (!opsD.isEmpty()) {
            //System.out.println(opsD);
            //System.out.println(numD);
            char op = opsD.pollFirst();
            long num1 = numD.pollFirst();
            long num2 = numD.pollFirst();

            if (op == '*' || op == '/' || opsD.isEmpty() || !opsD.isEmpty() && (opsD.getFirst() == '+' || opsD.getFirst() == '-')) {
                if (num2 == 0 && op == '/') {
                    return num1;
                }
                numD.offerFirst(doOps(num1, num2, op));
            } else if (!opsD.isEmpty() && (opsD.getFirst() == '*' || opsD.getFirst() == '/')) {
                long num3 = numD.pollFirst();
                char op1 = opsD.pollFirst();
                if (num3 == 0. && op1 == '/') {
                    return doOps(num1, num2, op);
                }
                numD.offerFirst(doOps(num2, num3, op1));
                opsD.offerFirst(op);
                numD.offerFirst(num1);
            }

        }

        return numD.poll();

    }

    private long doOps(long num1, long num2, char op) {
        if (op == '*') {
            return num1 * num2;
        }
        if (op == '/') {
            return num1 / num2;
        }
        if (op == '+') {
            return num1 + num2;
        }
        return num1 - num2;
    }

    private int findClosingP(String s, int i0) {
        int cnt = 1;
        for (int i = i0; i < s.length(); i ++) {
            if (s.charAt(i) == '(') {
                cnt ++;
            }
            if (s.charAt(i) == ')') {
                cnt --;
                if (cnt == 0) {
                    return i;
                }
            }
        }

        return -1;

    }

}
