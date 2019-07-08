package lc;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class Q388LongestAbsoluteFileAdd {

    public int lengthLongestPath(String input) {
        LinkedList<String> list = new LinkedList<>();

        int p0 = 0;
        int preTabs = 0;
        int tabs = 0;
        boolean word = true;
        String curString = "";
        for (int i = 0; i < input.length(); i ++) {
            //System.out.println(list);
            if (input.charAt(i) == '\n') {
                list.add(input.substring(p0, i));
                word = false;
                p0 = i + 1;
            } else if (input.charAt(i) == '\t') {
                tabs ++;
                word = false;
                p0 = i + 1;
            } else {
                if (i == input.length() - 1) {
                    list.add(input.substring(p0, input.length()));
                    String newS = list.stream().collect(Collectors.joining("/"));
                    if (list.peekLast().contains(".") &&
                            newS.length() > curString.length()) {
                        curString = newS;
                    }
                    break;
                }
                if (word) {
                    continue;
                }
                word = true;
                //System.out.println(preTabs +" "+tabs);
                if (preTabs >= tabs) {
                    String newS = list.stream().collect(Collectors.joining("/"));
                    if (list.peekLast().contains(".") && newS.length() > curString.length()) {
                        curString = newS;
                    }
                    int diff = preTabs - tabs;
                    for (int j = 0; j <= diff; j ++) {
                        list.pollLast();
                    }
                }
                preTabs = tabs;
                tabs = 0;
            }
        }

        return curString.length();
    }

}
