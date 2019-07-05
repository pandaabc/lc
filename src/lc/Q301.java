package lc;

import java.util.*;

public class Q301 {

    public static void main(String[] args) {

    }

    private static List<String> solve(String s) {

        Queue<String> queue = new LinkedList<>();
        queue.offer(s);
        Set<String> result = new HashSet<>();

        while (queue.size() > 0) {

            Set<String> queue1 = new HashSet<>();

            while (queue.size() > 0) {

                String cur = queue.poll();
                if (isValid(cur)) {
                    result.add(cur);
                } else {
                    StringBuilder sb = new StringBuilder(cur);
                    for (int i = 0; i < cur.length(); i ++) {
                        queue1.add(sb.delete(i, i+1).toString());
                        sb.insert(i, cur.charAt(i));
                    }
                }

            }
            if (result.size() > 0){
                return new ArrayList<>(result);
            }

            queue = new LinkedList<>(queue1);
            queue1 = new HashSet<>();

        }

        return new ArrayList<>();

    }

    private static boolean isValid(String string) {

        if (string.isEmpty()) {
            return true;
        }

        int count = 0;
        for (char c : string.toCharArray()) {

            if (c == '(') {
                count++;
            } else if (c == ')') {
                if (count <= 0) {
                    return false;
                } else {
                    count --;
                }
            }

        }

        return count == 0;

    }

}
