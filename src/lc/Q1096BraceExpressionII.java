package lc;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Q1096BraceExpressionII {

    public List<String> braceExpansionII(String expression) {
        if (expression == null || expression.length() == 0) {
            return new ArrayList<>();
        }
        return expand(expression).stream().distinct().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
    }

    private List<String> expand(String exp){
        //System.out.println(exp);
        List<String> res = new ArrayList<>();
        res.add("");
        int p0 = 0;
        for (int i = 0; i < exp.length(); i ++) {
            char c = exp.charAt(i);
            if (c == '{') {
                res = append(res, exp.substring(p0, i));
                int i1 = findClosed(exp, i);
                res = append(res, expand(exp.substring(i + 1, i1)));
                i = i1;
                p0 = i1 + 1;
            } else if (c == ',') {
                res = append(res, exp.substring(p0, i));
                res.addAll(expand(exp.substring(i + 1)));
                return res;
            } else if (i == exp.length() - 1) {
                res = append(res, exp.substring(p0, i + 1));
            }
            //System.out.println(exp + " " + i + " " + res);
        }
        return res;
    }

    private List<String> append(List<String> res, String s) {
        return res.stream().map(e -> e + s).collect(Collectors.toList());
    }

    private List<String> append(List<String> res, List<String> s) {
        return res.stream().map(e -> s.stream().map(s0 -> e + s0).collect(Collectors.toList())).flatMap(List::stream).collect(Collectors.toList());
    }

    private int findClosed(String exp, int i0) {
        //System.out.println(exp);
        int cnt = 1;
        for (int i = i0 + 1; i < exp.length(); i ++) {
            if (exp.charAt(i) == '{') {
                cnt ++;
            } else if (exp.charAt(i) == '}') {
                cnt --;
                if (cnt == 0) {
                    return i;
                }
            }
        }
        return -1;
    }

}
