package lc;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Q736ParseLispExpression {

    Map<String, Integer> valuesMap;
    public int evaluate(String expression) {

        valuesMap = new HashMap<>();
        Deque<String> deque = prepareExp(expression);
        //System.out.println(deque);
        return evaluate(deque);

    }

    public int evaluate(Deque<String> deque) {
        //System.out.println(deque);
        if (deque.size() == 1) {
            String str = deque.pollFirst();
            if (str.charAt(0) > '9' || str.charAt(0) < '0') {
                return valuesMap.get(str);
            }
            return Integer.parseInt(str);
        }

        String op = deque.pollFirst();
        if (op.equals("(")) {
            return evaluate(getSubDeque(deque));
        }
        if (op.equals("let")) {
            boolean test = true;
            while (test) {
                String ex = deque.pollFirst();
                String val = deque.pollFirst();
                if (val.equals("(")) {
                    valuesMap.put(ex, evaluate(getSubDeque(deque)));
                } else {
                    valuesMap.put(ex, Integer.parseInt(val));
                }
                if (deque.peekFirst().equals("(") || deque.size() == 1) {
                    test = false;
                }
            }
        }
        if (op.equals("add") || op.equals("mult")) {


            String num1Str = deque.pollFirst();
            int num1 = getInt(num1Str, deque);
            String num2Str = deque.pollFirst();
            int num2 = getInt(num2Str, deque);
            if (op.equals("add")) {
                deque.offerFirst(String.valueOf(num1 + num2));
            } else {
                deque.offerFirst(String.valueOf(num1 * num2));
            }

        }

        return evaluate(deque);

    }

    private int getInt(String num1Str, Deque<String> deque) {
        int num1;
        if (num1Str.equals("(")) {
            num1 = evaluate(getSubDeque(deque));
        } else if (num1Str.charAt(0) > '9' || num1Str.charAt(0) < '0') {
            num1 = valuesMap.get(num1Str);
        } else {
            num1 = Integer.parseInt(num1Str);
        }
        return num1;
    }


    private Deque<String> prepareExp (String expression) {
        String[] exps = expression.split(" ");
        Deque<String> deque = new ArrayDeque<>();
        for (String e : exps) {

            int p0 = 0;
            for (int i = 0; i < e.length(); i ++) {
                if (e.charAt(i) == '(' || e.charAt(i) == ')') {
                    if (p0 < i) {
                        deque.offer(e.substring(p0, i));
                    }
                    deque.offer(String.valueOf(e.charAt(i)));
                    p0 = i + 1;
                } else if (i == e.length() - 1) {
                    deque.offer(e.substring(p0, i + 1));
                }
            }
        }
        return deque;
    }

    private Deque<String> getSubDeque(Deque<String> deque) {

        int cnt = 1;
        Deque<String> d = new ArrayDeque<>();
        while (!deque.isEmpty()) {
            String s = deque.pollFirst();
            if (s.equals("(")) {
                cnt += 1;
            } else if (s.equals(")")) {
                cnt -= 1;
                if (cnt == 0) {
                    return d;
                }
            }
            d.offerLast(s);
        }
        return null;

    }
}
