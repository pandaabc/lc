package lc;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Q843GuessWords {

    public void findSecretWord(String[] wordlist, Master master) {
        List<String> list = Arrays.stream(wordlist).collect(Collectors.toList());
        int cnt = 0;
        int guess = 0;
        while (cnt != 6 && guess < 10) {
            String g = list.get(getRandomIdx(list.size()));
            cnt = master.guess(g);
            int cntf = cnt;
            list = list.stream().filter(e -> findMatch(g, e) != cntf).collect(Collectors.toList());
            guess++;
        }

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
