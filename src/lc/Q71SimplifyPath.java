package lc;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q71SimplifyPath {

    public String simplifyPath(String path) {
        if (path == null) {
            return path;
        }
        String[] ps = path.split("/");
        Deque<String> deque = new ArrayDeque<>();
        for (int i = 0; i < ps.length; i ++) {
            String cur = ps[i];
            if (".".equals(cur)) {
                continue;
            } else if ("..".equals(cur)) {
                if (deque.size() > 0) {
                    deque.pollFirst();
                }
            } else if (!cur.isEmpty()) {
                deque.offerFirst(cur);
            }
        }

        StringBuilder sb = new StringBuilder();

        while (deque.size() > 0) {
            sb.append("/");
            sb.append(deque.pollLast());
        }

        return sb.length() == 0 ? "/" : sb.toString();
    }

}
