package lc;

import java.util.Stack;

public class Q394DecodeString {

    class Solution {
        public String decodeString(String s) {
            if (s == null || s.isEmpty()) {
                return s;
            }
            Stack<Character> stack = new Stack<>();
            StringBuilder res = new StringBuilder();
            for (int i = s.length() - 1; i >= 0; i --) {
                char cur = s.charAt(i);
                if (cur == '[') {
                    // find the numbers before it
                    int j = i - 1;
                    StringBuilder num = new StringBuilder();
                    while (j >= 0 && s.charAt(j) >= '0' && s.charAt(j) <= '9') {
                        num.insert(0, s.charAt(j));
                        j --;
                    }
                    i = j + 1;
                    int rep = Integer.parseInt(num.toString());
                    // pop out the repeating string
                    StringBuilder curSb = new StringBuilder();
                    while (stack.peek() != ']') {
                        curSb.append(stack.pop());
                    }
                    stack.pop();
                    String tempStr = curSb.toString();
                    while (rep > 1) {
                        curSb.append(tempStr);
                        rep --;
                    }
                    // add the string back or put it in the results
                    if (!stack.isEmpty()) {
                        for (int k = curSb.length() - 1; k >= 0; k --) {
                            stack.push(curSb.charAt(k));
                        }
                    } else {
                        res.insert(0, curSb);
                    }
                } else if (cur == ']' || !stack.isEmpty()){
                    stack.push(cur);
                } else {
                    res.insert(0, cur);
                }
            }
            while (!stack.isEmpty()) {
                res.insert(0, stack.pop());
            }
            return res.toString();
        }
    }

}
