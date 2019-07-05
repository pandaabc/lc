package lc;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 */
public class Q149MaxPointsInALine {

    public int maxPoints(int[][] points) {

        if (points == null || points.length == 0) {
            return 0;
        }
        if (points.length <= 2) {
            return points.length;
        }

        Map<String, Set<Integer>> linesByPoint = new HashMap<>();
        Map<Integer, Set<String>> pointsByLine = new HashMap<>();
        int lineCnt = 0;

        List<String> pointKey = Arrays.stream(points).map(e -> e[0] + "-" + e[1]).collect(Collectors.toList());
        Map<String, Integer> cntByPoints = new HashMap<>();
        pointKey.stream().forEach(e -> cntByPoints.compute(e, (k, v) -> v == null ? 1 : v + 1));

        for (int i = 0; i < points.length; i ++) {
            Set<Integer> triedLines = linesByPoint.computeIfAbsent(pointKey.get(i), k -> new HashSet<>());
            Set<String> triedPoints = triedLines.stream().map(e -> pointsByLine.get(e)).flatMap(Set::stream).collect(Collectors.toSet());

            for (int j = 0;j < points.length; j ++) {
                if (i == j || triedPoints.contains(pointKey.get(j))) {
                    continue;
                }
                lineCnt += 1;
                linesByPoint.computeIfAbsent(pointKey.get(i), key -> new HashSet<>()).add(lineCnt);
                linesByPoint.computeIfAbsent(pointKey.get(j), key -> new HashSet<>()).add(lineCnt);
                Set<String> linePoints = new HashSet<>();
                linePoints.add(pointKey.get(i));
                linePoints.add(pointKey.get(j));
                for (int k = 0; k < points.length; k ++) {
                    if (i ==k || j == k) {
                        continue;
                    }
                    if (isLine(points[i], points[j], points[k])) {
                        linesByPoint.computeIfAbsent(pointKey.get(k), key -> new HashSet<>()).add(lineCnt);
                        linePoints.add(pointKey.get(k));
                    }
                }
                pointsByLine.put(lineCnt, linePoints);

            }

        }

        return pointsByLine.values().stream().map(e -> e.stream().map(p -> cntByPoints.get(p)).mapToInt(c -> c).sum()).mapToInt(c -> c).max().orElse(0);

    }

    private boolean isLine(int[] p1, int[] p2, int[] p3) {
        if ((p1[0] - p2[0]) * (p1[0] - p3[0]) == 0 && (p1[0] - p3[0] != 0 || p1[0] - p2[0] != 0)) {
            return false;
        } else if ((p1[1] - p3[1]) * (p1[1] - p2[1]) == 0 && (p1[1] - p2[1] != 0 || p1[1] - p3[1] != 0)) {
            return false;
        }

        return (p1[0] - p2[0]) * (p1[1] - p3[1]) == (p1[1] - p2[1]) * (p1[0] - p3[0]);

    }

}
