package lc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Q890FindAndReplacePattern {

    public List<String> findAndReplacePattern(String[] words, String pattern) {

        return Arrays.stream(words).filter(e -> matchPattern(e, pattern)).collect(Collectors.toList());

    }

    private boolean matchPattern(String word, String pattern) {

        Set<Character> used = new HashSet<>();
        Integer[] mapping = new Integer[26];
        for (int i = 0; i < word.length(); i ++) {
            char c = word.charAt(i);
            //System.out.println(used);
            //System.out.println(Arrays.toString(mapping));

            if (mapping[c - 'a'] != null && mapping[c - 'a'] != pattern.charAt(i) - 'a' || mapping[c - 'a'] == null && used.contains(pattern.charAt(i))) {
                //System.out.println("return false");
                return false;
            } else {
                mapping[c - 'a'] = pattern.charAt(i) - 'a';
                used.add(pattern.charAt(i));
            }
        }
        return true;

    }

}
