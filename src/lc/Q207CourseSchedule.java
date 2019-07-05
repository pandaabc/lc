package lc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 */
public class Q207CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        if (numCourses == 0) {
            return true;
        }

        Map<Integer, Set<Integer>> inCourse = new HashMap<>();
        Map<Integer, Set<Integer>> childCourse = new HashMap<>();

        for (int i = 0; i < prerequisites.length; i ++) {
            int[] pair = prerequisites[i];
            inCourse.computeIfAbsent(pair[0], k -> new HashSet<>()).add(pair[1]);
            childCourse.computeIfAbsent(pair[1], k -> new HashSet<>()).add(pair[0]);
            if (inCourse.computeIfAbsent(pair[0], k -> new HashSet<>()).contains(pair[1])
                    || childCourse.computeIfAbsent(pair[1], k -> new HashSet<>()).contains(pair[0])) {
                return false;
            }
        }

        return true;
    }

}
