package lc;

import java.util.*;
import java.util.stream.Collectors;

public class Q127WordLadder_BFS {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Deque<String> deque = new LinkedList<>();
        Set<String> wordSet = wordList.stream().collect(Collectors.toSet());
        deque.offer(beginWord);
        int cnt = 0;

        while (!deque.isEmpty()) {
            cnt ++;
            int size = deque.size();
            for (int i = 0; i < size; i ++) {
                String cur = deque.poll();
                //System.out.println(cur);
                if (cur.equals(endWord)) {
                    return cnt;
                }
                deque.addAll(findWordsWith1Change(cur, wordSet));
            }
        }
        return 0;

    }

    public Set<String> findWordsWith1Change(String word, Set<String> wordList) {

        Set<String> ret = wordList.stream().filter(e -> isOnlyOneCharChanged(e, word)).collect(Collectors.toSet());
        wordList.removeIf(ret::contains);
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
