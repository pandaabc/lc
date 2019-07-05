package lc;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Q305 {

    public static void main(String[] args) {
        Q305 q = new Q305();
        q.numIslands2(3, 3, new int[][]{{0,0},{0,1},{1,2},{2,1}});
    }

        public List<Integer> numIslands2(int m, int n, int[][] positions) {

            Map<Integer, Set<Integer>> isLands = new HashMap<>();
            int[][] mark = new int[m][n];
            int curKey = 1;
            List<Integer> res = new ArrayList<>();

            for (int i = 0; i < positions.length; i ++) {
                Set<Integer> finds = find(isLands, positions[i], m, n, mark);
                union(isLands, positions[i], mark, finds, curKey);
                curKey ++;
                res.add(isLands.size());
            }

            return res;

        }

        private Set<Integer> find(Map<Integer, Set<Integer>> isLands, int[] positions, int m, int n, int[][] mark) {
            Set<Integer> finds = new HashSet<>();
            if (positions[0] > 0 && mark[positions[0]-1][positions[1]] != 0) {
                finds.add(mark[positions[0]-1][positions[1]]);
            }
            if (positions[1] > 0 && mark[positions[0]][positions[1] - 1] != 0) {
                finds.add(mark[positions[0]][positions[1] - 1]);
            }
            if (positions[0] < m - 1 && mark[positions[0] + 1][positions[1]] != 0) {
                finds.add(mark[positions[0] + 1][positions[1]]);
            }
            if (positions[1] < n - 1 && mark[positions[0]][positions[1] + 1] != 0) {
                finds.add(mark[positions[0]][positions[1] + 1]);
            }
            return finds;
        }

        private int union(Map<Integer, Set<Integer>> isLands, int[] positions, int[][] mark, Set<Integer> finds, int curKey) {
            if (finds.size() > 0) {
                mark[positions[0]][positions[1]] = finds.stream().findAny().get();
                Set<Integer> keys = isLands.entrySet().stream()
                        .filter(entry -> finds.stream().anyMatch(entry.getValue()::contains))
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toSet());
                isLands.put(curKey, keys.stream().map(isLands::get).flatMap(Set::stream).collect(Collectors.toSet()));
                keys.stream().forEach(isLands::remove);
            } else {
                mark[positions[0]][positions[1]] = curKey;
                isLands.computeIfAbsent(curKey, k -> new HashSet<>()).add(curKey);
            }
            return curKey ++;
        }

        private List<String> find(Map<String, Set<String>> isLands, int[] positions, int m, int n) {

            String up = positions[0] == 0 ? null : (positions[0] - 1) + "-" + (positions[1]);
            String down = positions[0] == m ? null : (positions[0] + 1) + "-" + (positions[1]);
            String left = positions[1] == 0 ? null : (positions[0]) + "-" + (positions[1] - 1);
            String right = positions[1] == n ? null : (positions[0]) + "-" + (positions[1] + 1);

            return isLands.entrySet().stream().filter(entry -> {
                boolean res = up != null && entry.getValue().contains(up)
                        || down != null && entry.getValue().contains(down)
                        || left != null && entry.getValue().contains(left)
                        || right != null && entry.getValue().contains(right);
                return res;
            }).map(Map.Entry::getKey).collect(Collectors.toList());

        }

        private Map<String, Set<String>> union(Map<String, Set<String>> isLands, List<String> finds, int[] positions) {
            Set<String> newIsland = finds.stream().map(f -> isLands.get(f)).flatMap(Set::stream).collect(Collectors.toSet());
            String key = positions[0] + "-" + positions[1];
            newIsland.add(key);
            isLands.put(key, newIsland);
            finds.stream().forEach(ky -> isLands.remove(ky));
            return isLands;
        }


}
