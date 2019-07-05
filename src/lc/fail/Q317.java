package lc.fail;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
 *
 * Each 0 marks an empty land which you can pass by freely.
 * Each 1 marks a building which you cannot pass through.
 * Each 2 marks an obstacle which you cannot pass through.
 *
 *
 * Solution:
 * 1. find out all buildings.
 * 2. do bfs for all buildings. and find out the steps needed.
 * 3. sum all the steps and find out the minimum.
 */
public class Q317 {

    /**
     * this method wont work as the first meet does not mean shortest total distance.
     * @param grid
     * @return
     */
    public int shortestDistance(int[][] grid) {
        // step 0. edge cases
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        // step 1. find all index of 1s (buildings)
        List<int[]> buildings = findBuildings(grid);
        // step 2. initialize information
        List<int[][]> distances = IntStream.range(0, buildings.size()).mapToObj(i -> new int[grid.length][grid[0].length]).collect(Collectors.toList());
        List<Queue<int[]>> queues = IntStream.range(0, buildings.size()).mapToObj(i -> {
            Queue<int[]> q = new LinkedList<>();
            q.offer(buildings.get(i));
            return q;
        }).collect(Collectors.toList());
        int[][] reachedCount = new int[grid.length][grid[0].length];
        int targetSize = buildings.size();
        int i = 0;

        // move step by step from all buildings
        while (i < grid.length * grid[0].length) {
            int[] loc = moveOneStep(distances, queues, reachedCount, targetSize, i, grid);
            if (loc != null) {
                return distances.stream().map(e -> e[loc[0]][loc[1]]).reduce(0, (x, y) -> x + y);
            }
            i ++;
        }
        return -1;
    }

    private int[] moveOneStep(List<int[][]> distances, List<Queue<int[]>> queues, int[][] reachedCount, int targetSize, int stepNum, int[][] grid) {
        for (int i = 0; i < queues.size(); i ++) {
            int length = queues.get(i).size();
            for (int j = 0; j < length; j ++) {
                Queue<int[]> queue = queues.get(i);
                int[] loc = queue.poll();
                int x0 = loc[0];
                int y0 = loc[1];
                int[][] distance = distances.get(i);
                distance[x0][y0] = stepNum;
                reachedCount[x0][y0] += 1;
                if (reachedCount[x0][y0] == targetSize) {
                    return loc;
                }
                if (x0 > 0 && distance[x0 - 1][y0] == 0 && grid[x0 - 1][y0] == 0) {
                    queue.offer(new int[]{x0 - 1, y0});
                }
                if (y0 > 0 && distance[x0][y0 - 1] == 0 && grid[x0][y0 - 1] == 0) {
                    queue.offer(new int[]{x0, y0 - 1});
                }
                if (x0 < grid.length - 1 && distance[x0 + 1][y0] == 0 && grid[x0 + 1][y0] == 0) {
                    queue.offer(new int[]{x0 + 1, y0});
                }
                if (y0 < grid[0].length - 1 && distance[x0][y0 + 1] == 0 && grid[x0][y0 + 1] == 0) {
                    queue.offer(new int[]{x0, y0 + 1});
                }
            }
        }
        return null;
    }

    private List<int[]> findBuildings (int[][] grid) {
        List<int[]> buildings = new ArrayList<>();
        for (int i = 0; i < grid.length; i ++) {
            for (int j = 0; j < grid[i].length; j ++) {
                if (grid[i][j] == 1) {
                    int[] location = new int[2];
                    location[0] = i;
                    location[1] = j;
                    buildings.add(location);
                }
            }
        }
        return buildings;
    }

    /**
     * this method works.  however, it made TLE not sure why.  dont see any method better than this...
     * may be too much queues?
     * @param grid
     * @return
     */
    public int shortestDistanceV1(int[][] grid) {

        // step 1. find all index of 1s
        List<int[]> buildings = findBuildings(grid);

        // find out distances to all buildings
        List<int[][]> distances = new ArrayList<>(buildings.size());

        for (int i = 0; i < buildings.size(); i ++) {
            Queue<int[]> queue = new LinkedList<>();
            Queue<Integer> dis = new LinkedList<>();
            int x = buildings.get(0)[0];
            int y = buildings.get(0)[1];
            distances.add(new int[grid.length][grid[0].length]);

            int[][] distance = distances.get(i);
            distance[x][y] = grid.length * grid[0].length * buildings.size() + 1;
            queue.offer(new int[]{x, y});
            dis.offer(0);

            while (queue.size() > 0) {
                int[] cur = queue.poll();
                int localDis = dis.poll();
                int x0 = cur[0];
                int y0 = cur[1];
                distance[x0][y0] = localDis;
                // add points
                if (x0 > 0 && distance[x0 - 1][y0] == 0 && grid[x0 - 1][y0] == 0) {
                    queue.offer(new int[]{x0 - 1, y0});
                    dis.offer(localDis+1);
                }
                if (y0 > 0 && distance[x0][y0 - 1] == 0 && grid[x0][y0 - 1] == 0) {
                    queue.offer(new int[]{x0, y0 - 1});
                    dis.offer(localDis+1);
                }
                if (x0 < grid.length - 1 && distance[x0 + 1][y0] == 0 && grid[x0 + 1][y0] == 0) {
                    queue.offer(new int[]{x0 + 1, y0});
                    dis.offer(localDis+1);
                }
                if (y0 < grid[0].length - 1 && distance[x0][y0 + 1] == 0 && grid[x0][y0 + 1] == 0) {
                    queue.offer(new int[]{x0, y0 + 1});
                    dis.offer(localDis+1);
                }
            }
        }

        // find out min
        int totalDis = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; i ++) {
            for (int j = 0; j < grid[i].length; j ++) {
                if (grid[i][j] > 0) {
                    continue;
                }
                int ii = i;
                int jj = j;
                if (distances.stream().anyMatch(e -> e[ii][jj] == 0)) {
                    return -1;
                }
                int d = distances.stream().map(e -> e[ii][jj]).reduce(0, (x,y) -> x + y);
                totalDis = Math.min(totalDis, d);
            }
        }

        return totalDis;

    }

}
