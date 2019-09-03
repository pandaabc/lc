package lc;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Q721AccountMerge {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        if (accounts == null || accounts.size() == 0) {
            return new ArrayList<>();
        }

        Map<String, Set<Integer>> emailToIndex = new HashMap<>();

        for (int i = 0; i < accounts.size(); i ++) {
            List<String> account = accounts.get(i);
            String name = account.get(0);
            for (int j = 1; j < account.size(); j ++) {
                emailToIndex.computeIfAbsent(account.get(j), k -> new HashSet<>()).add(i);
            }
        }

        Set<Integer> tried = new HashSet<>();
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < accounts.size(); i ++) {
            if (!tried.contains(i)) {

                List<Integer> allAccounts = new ArrayList<>();
                allAccounts.add(i);
                mergeAccount(emailToIndex, accounts, tried, i, allAccounts);
                String name = accounts.get(i).get(0);
                List<String> cur = new ArrayList<>();
                cur.add(name);
                cur.addAll(allAccounts.stream().map(accounts::get).flatMap(List::stream).filter(e -> !e.equals(name)).distinct().sorted(Comparator.naturalOrder()).collect(Collectors.toList()));
                result.add(cur);

            }
        }
        return result;
    }

    public void mergeAccount(Map<String, Set<Integer>> emailToIndex, List<List<String>> accounts, Set<Integer> tried, int index, List<Integer> allAccounts) {

        List<String> account = accounts.get(index);

        Set<Integer> toCheck = IntStream.range(1, account.size()).boxed().map(account::get).map(emailToIndex::get).flatMap(Set::stream).filter(e -> !tried.contains(e)).collect(Collectors.toSet());
        for (int idx : toCheck) {
            allAccounts.add(idx);
            tried.add(idx);
            mergeAccount(emailToIndex, accounts, tried, idx, allAccounts);
        }
    }

}
