package lc;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Q692TopKFrequentWords {

    public List<String> topKFrequent(String[] words, int k) {
        if (words == null || words.length == 0) {
            return new ArrayList<>();
        }

        PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>((e1 , e2) -> {
            int res = Integer.compare(e1.getValue(), e2.getValue());
            if (res == 0) {
                res = e2.getKey().compareTo(e1.getKey());
            }
            return res;
        });
        Map<String, Integer> map = new HashMap<>();
        for (String str : words){
            map.compute(str, (key, v) -> v == null ? 1 : v + 1);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            queue.offer(entry);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        List<String> res = IntStream.range(0, k).boxed().map(e -> "").collect(Collectors.toList());

        for (int i = k - 1; i >= 0; i --) {
            res.set(i, queue.poll().getKey());
        }
        return res;
    }

    public List<String> topKFrequentV2(String[] words, int k) {
        if (words == null || words.length == 0) {
            return new ArrayList<>();
        }

        Map<String, Integer> map = new HashMap<>();
        for (String str : words){
            map.compute(str, (key, v) -> v == null ? 1 : v + 1);
        }

        List<String>[] cnt = new ArrayList[words.length + 1];
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (cnt[entry.getValue()] == null) {
                cnt[entry.getValue()] = new ArrayList<>();
            }
            cnt[entry.getValue()].add(entry.getKey());
        }
        List<String> results = new ArrayList<>();
        for (int i = words.length; i >= 0; i --) {
            if (cnt[i]  != null) {
                Collections.sort(cnt[i]);
                results.addAll(cnt[i]);
                if (results.size() >= k) {
                    break;
                }
            }
        }

        return results.subList(0, k);
    }
}
