package lc;

import java.util.*;
import java.util.stream.Collectors;

public class Q843GuessWords {

    public void findSecretWord(String[] wordlist, Master master) {
        List<String> list = Arrays.stream(wordlist).collect(Collectors.toList());
        int cnt = 0;
        int guess = 0;
        while (cnt != 6 && guess < 10) {
            String g = findNextWord(list);
            cnt = master.guess(g);
            int cntf = cnt;
            list = list.stream().filter(e -> findMatch(g, e) != cntf).collect(Collectors.toList());
            guess++;
        }

    }

    private String findNextWord(List<String> words) {
        Map<String, Integer> wordCounts = new HashMap<>();
        for (int i = 0; i < words.size(); i ++) {
            for (int j = i + 1; j < words.size(); j ++) {
                int cnt = findMatch(words.get(i), words.get(j));
                wordCounts.compute(words.get(i), (k, v) -> v == null ? cnt : v + cnt);
                wordCounts.compute(words.get(j), (k, v) -> v == null ? cnt : v + cnt);
            }
        }
        return wordCounts.entrySet().stream().min(Comparator.comparing(Map.Entry::getValue)).map(Map.Entry::getKey).orElse(null);
    }

    private int getRandomIdx(int size) {
        return new Random().nextInt(size + 1);
    }

    private int findMatch(String g, String toMatch) {
        int cnt = 0;
        for (int i = 0; i < g.length(); i ++) {
            if (g.charAt(i) == toMatch.charAt(i)) {
                cnt ++;
            }
        }
        return cnt;
    }

}

class Master{
    public int guess(String str) {
        return 0;
    }
}
