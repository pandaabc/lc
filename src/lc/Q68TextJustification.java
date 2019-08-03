package lc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Q68TextJustification {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<List<String>> lists = new ArrayList<>();
        lists.add(new ArrayList<>());
        int cnt = 0;
        int i = 0;
        for (String w : words) {
            cnt += w.length();
            if (cnt <= maxWidth) {
                lists.get(i).add(w);
                cnt += 1;
            } else {
                i ++;
                lists.add(new ArrayList<>());
                lists.get(i).add(w);
                cnt = w.length() + 1;
            }
        }
        List<String> res = new ArrayList<>();
        for (int j = 0; j < lists.size(); j ++) {
            if (j == lists.size() - 1) {
                res.add(buildWords(lists.get(i), true, maxWidth));
            } else {
                res.add(buildWords(lists.get(i), false, maxWidth));
            }
        }
        return res;
    }

    private String buildWords(List<String> words, boolean lastLine, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        if (lastLine || words.size() == 1) {
            sb.append(words.stream().collect(Collectors.joining(" ")));
            addSpace(sb, maxWidth - sb.length());
        } else {
            int tl = words.stream().map(String::length).mapToInt(e -> e).sum();
            int avgSpc = (maxWidth - tl) / (words.size() - 1);
            int firstSpc = maxWidth - tl - avgSpc * (words.size() - 2);
            for (int i = 0; i < words.size(); i ++) {
                sb.append(words.get(i));
                if (i == 0) {
                    addSpace(sb, firstSpc);
                } else if (i < words.size() - 1) {
                    addSpace(sb, avgSpc);
                }
            }
        }
        return sb.toString();
    }

    private void addSpace(StringBuilder sb, int cnt) {
        for (int i = 0; i < cnt; i ++) {
            sb.append(" ");
        }
    }

}
