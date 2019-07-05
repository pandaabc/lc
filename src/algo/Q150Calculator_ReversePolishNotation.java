package algo;


import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Reverse Polish notation is a method that used in calculators.
 *
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 *
 * Note:
 *
 * Division between two integers should truncate toward zero.
 * The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
 * 
 */
public class Q150Calculator_ReversePolishNotation {

    private Set<String> ops;
    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        for (String token : tokens) {
            if (!isOps(token)) {
                stack.push(token);
            } else {
                boolean isGoodExp =  eval(stack, token);
            }
        }
        return Integer.valueOf(stack.pop());
    }

    private boolean eval(Stack<String> stack, String ops) {
        Integer res = null;
        int b = Integer.valueOf(stack.pop());
        int a = Integer.valueOf(stack.pop());
        if ("+".equals(ops)) {
            res = a + b;
        } else if ("-".equals(ops)) {
            res = a - b;
        } else if ("*".equals(ops)) {
            res = a * b;
        } else {
            if (b != 0) {
                res = a / b;
            }
        }
        if (res == null) {
            return false;
        }
        stack.push(String.valueOf(res));
        return true;
    }

    private boolean isOps(String str) {

        if (ops == null) {
            ops = new HashSet<>();
            ops.add("+");
            ops.add("-");
            ops.add("*");
            ops.add("/");
        }

        return ops.contains(str);

    }

}
