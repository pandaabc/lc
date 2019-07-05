package lc;

import java.util.*;
import java.util.stream.Collectors;

public class Q126WordLadderII {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        if (!wordList.contains(endWord)) {
            return new ArrayList<>();
        }

        List<List<String>> res = new ArrayList<>();

        Deque<List<String>> ladders = new LinkedList<>();
        Deque<Deque<String>> nextCom = new LinkedList<>();


        List<String> l = new LinkedList<>();
        Deque<String> n = new LinkedList<>();
        n.offer(beginWord);

        ladders.add(l);
        nextCom.add(n);

        boolean found = false;


        while (!found && nextCom.isEmpty()) {

            Set<String> nexComWords = new HashSet<>();
            int comSize = nextCom.size();
            for (int i = 0; i < comSize; i ++) {

                List<String> lad = ladders.poll();
                Deque<String> com = nextCom.poll();

                int size = com.size();
                for (int j = 0; j < size; j ++) {
                    List<String> newLad = new ArrayList<>(lad);
                    Deque<String> newCom = new LinkedList<>();
                    String w = com.poll();
                    newLad.add(w);
                    if (w.equals(endWord)) {
                        res.add(newLad);
                        found = true;
                        continue;
                    } else {
                        Set<String> nextC = findWordsWith1Change(w, wordList);
                        nexComWords.addAll(nextC);
                        newCom.addAll(nextC);
                    }
                    ladders.add(newLad);
                    nextCom.add(newCom);
                }
            }

            wordList.removeIf(nexComWords::contains);

        }

        return res;

    }

    public Set<String> findWordsWith1Change(String word, List<String> wordList) {

        Set<String> ret = wordList.stream().filter(e -> isOnlyOneCharChanged(e, word)).collect(Collectors.toSet());
        return ret;

    }

    public boolean isOnlyOneCharChanged(String word1, String word2) {

        int cnt = 0;
        for (int i = 0; i < word1.length(); i ++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                if (++ cnt > 1) {
                    return false;
                }
            }
        }
        return true;

    }
}
