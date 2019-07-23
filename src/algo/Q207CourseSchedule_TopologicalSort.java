package algo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Q207CourseSchedule_TopologicalSort {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) {
            return true;
        }
        if (prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0) {
            return false;
        }

        Map<Integer, Set<Integer>> preClasses = new HashMap<>();
        Set<Integer> allCourses = IntStream.range(0, numCourses).boxed().collect(Collectors.toSet());
        int canFinish = 0;

        for (int[] pre : prerequisites) {
            preClasses.computeIfAbsent(pre[0], k -> new HashSet<>()).add(pre[1]);
            allCourses.add(pre[0]);
            allCourses.add(pre[1]);
        }

        while (canFinish < numCourses) {

            Integer nextCourse = allCourses.stream().filter(e -> !preClasses.containsKey(e) || preClasses.get(e).isEmpty()).findAny().orElse(null);
            if (nextCourse == null) {
                return false;
            }
            canFinish ++;
            preClasses.values().stream().forEach(e -> e.remove(nextCourse));
        }

        return canFinish >= numCourses;
    }

}
