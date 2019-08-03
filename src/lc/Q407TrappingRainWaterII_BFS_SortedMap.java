package lc;

import javax.naming.InitialContext;
import java.util.*;

public class Q407TrappingRainWaterII_BFS_SortedMap {

    public int trapRainWaterPQ(int[][] heightMap) {

        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) {
            return 0;
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((e1, e2) -> Integer.compare(e1[0], e2[0]));
        boolean[][] visited = new boolean[heightMap.length][heightMap[0].length];
        for (int i = 0; i < heightMap.length; i ++) {
            queue.offer(new int[]{heightMap[i][0], i, 0});
            queue.offer(new int[]{heightMap[i][heightMap[0].length - 1], i, heightMap[0].length - 1});
            visited[i][0] = true;
            visited[i][heightMap[0].length - 1] = true;
        }

        for (int i = 0; i < heightMap[0].length; i ++) {
            queue.offer(new int[]{heightMap[0][i], 0, i});
            queue.offer(new int[]{heightMap[heightMap.length - 1][i], heightMap.length - 1, i});
            visited[0][i] = true;
            visited[heightMap.length - 1][i] = true;
        }

        int curBoundary = 0;
        int trappedWater = 0;
        while (!queue.isEmpty()) {

            int[] cur = queue.poll();
            int i = cur[1];
            int j = cur[2];
            if (curBoundary > cur[0]) {
                trappedWater += curBoundary - cur[0];
            } else {
                curBoundary = Math.max(curBoundary, cur[0]);
            }
            if (!visited[i - 1][j] && i > 0) {
                queue.add(new int[]{heightMap[i - 1][j], i - 1, j});
            }
            if (!visited[i + 1][j] && i < heightMap.length - 1) {
                queue.add(new int[]{heightMap[i + 1][j], i + 1, j});
            }
            if (!visited[i][j - 1] && j > 0) {
                queue.add(new int[]{heightMap[i][j - 1], i, j - 1});
            }
            if (!visited[i][j + 1] && j < heightMap[0].length - 1) {
                queue.add(new int[]{heightMap[i][j + 1], i, j + 1});
            }
        }
        return trappedWater;
    }


    public int trapRainWater(int[][] heightMap) {

        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) {
            return 0;
        }

        PriorityQueue<int[]> queue = getSortedHeight(heightMap);
        TreeSet<Integer> sortedHeight = getSortedHeightInt(heightMap);
        boolean[][] visited = new boolean[heightMap.length][heightMap[0].length];
        int maxWater = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[1]][cur[2]]) {
                continue;
            }
            int water = bfsRainWater(visited, cur, heightMap, heightMap[0].length, sortedHeight);
            maxWater = Math.max(water, maxWater);
        }
        return maxWater;
    }

    private int bfsRainWater(boolean[][] visited, int[] start, int[][] heightMap, int cols, TreeSet<Integer> sortedHeight) {
        int heightCnt = start[0];
        Integer boundaryHeight = sortedHeight.higher(start[0]);
        if (boundaryHeight ==  null) {
            return 0;
        }
        int maxWater = 0;
        SortedMap<Integer, Set<Integer>> locByHeight = new TreeMap<>();
        locByHeight.computeIfAbsent(start[0], k -> new HashSet<>()).add(start[1] * cols + start[2]);
        boolean[][] localVisited = new boolean[visited.length][visited[0].length];
        localVisited[start[1]][start[2]] = true;
        Set<Integer> computed = new HashSet<>();
        while (!locByHeight.isEmpty()) {

            while (locByHeight.firstKey() < boundaryHeight) {
                Set<Integer> locs = locByHeight.get(locByHeight.firstKey());
                locByHeight.remove(locByHeight.firstKey());
                for (int loc : locs) {
                    int i = loc / cols;
                    int j = loc % cols;
                    if (visited[i][j] || i == 0 || j == 0 || i == visited.length - 1 || j == cols - 1) {
                        return maxWater;
                    }
                    computed.add(loc);
                    heightCnt += heightMap[i][j];
                    localVisited[i][j] = true;
                    // add boundaries
                    if (!localVisited[i - 1][j]) {
                        locByHeight.computeIfAbsent(heightMap[i - 1][j], k -> new HashSet<>()).add((i - 1) * cols + j);
                    }
                    if (!localVisited[i + 1][j]) {
                        locByHeight.computeIfAbsent(heightMap[i + 1][j], k -> new HashSet<>()).add((i + 1) * cols + j);
                    }
                    if (!localVisited[i][j - 1]) {
                        locByHeight.computeIfAbsent(heightMap[i][j - 1], k -> new HashSet<>()).add(i * cols + j - 1);
                    }
                    if (!localVisited[i][j + 1]) {
                        locByHeight.computeIfAbsent(heightMap[i][j + 1], k -> new HashSet<>()).add(i * cols + j + 1);
                    }
                }
            }

            maxWater = Math.max(boundaryHeight * computed.size() - heightCnt, maxWater);
            computed.forEach(e -> visited[e / cols][e % cols] = true);
            boundaryHeight = sortedHeight.higher(boundaryHeight);
            if (boundaryHeight == null) {
                return maxWater;
            }

        }
        return maxWater;
    }

    private PriorityQueue<int[]> getSortedHeight(int[][] heightMap){
        PriorityQueue<int[]> queue = new PriorityQueue<>((e1, e2) -> Integer.compare(e1[0], e2[0]));
        for (int i = 0; i < heightMap.length; i ++) {
            for (int j = 0; j < heightMap[0].length; j ++) {
                queue.offer(new int[]{heightMap[i][j], i, j});
            }
        }
        return queue;
    }

    private TreeSet<Integer> getSortedHeightInt(int[][] heightMap) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < heightMap.length; i ++) {
            for (int j = 0; j < heightMap[0].length; j ++) {
                set.add(heightMap[i][j]);
            }
        }
        return set;
    }




}
