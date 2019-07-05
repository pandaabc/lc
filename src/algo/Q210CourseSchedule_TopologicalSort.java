package algo;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
 *
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
 */
public class Q210CourseSchedule_TopologicalSort {

    public static void main(String[] args) {
        Q210CourseSchedule_TopologicalSort sort = new Q210CourseSchedule_TopologicalSort();
        sort.findOrder(2, new int[][]{{1, 0}});
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) {
            return new int[0];
        }
        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, Set<Integer>> childCourse = new HashMap<>();
        constructMaps(inDegree, childCourse, prerequisites, numCourses);
        return topologicalSort(inDegree, childCourse, numCourses);
    }

    private void constructMaps (Map<Integer, Integer> inDegree, Map<Integer, Set<Integer>> childCourse, int[][] prerequisites, int numCourses) {
        for (int i = 0; i < numCourses; i ++) {
            inDegree.computeIfAbsent(i, k -> 0);
            childCourse.computeIfAbsent(i, k -> new HashSet<>());
        }

        for (int i = 0; i < prerequisites.length; i ++) {
            int[] prerequ = prerequisites[i];
            inDegree.compute(prerequ[0], (k, v) -> v == null ? 1 : v + 1);
            childCourse.computeIfAbsent(prerequ[1], k -> new HashSet<>()).add(prerequ[0]);
        }

    }

    private int[] topologicalSort(Map<Integer, Integer> inDegree, Map<Integer, Set<Integer>> childCourse, int numCourses) {
        int[] res = new int[numCourses];
        int i = 0;
        while (inDegree.size() > 0) {
            int courseId = -1;
            for (Map.Entry<Integer, Integer> idEntry : inDegree.entrySet()) {
                if (idEntry.getValue() == 0) {
                    res[i] = idEntry.getKey();
                    i ++;
                    courseId = idEntry.getKey();
                    break;
                }
            }
            if (courseId == -1) {
                return new int[0];
            }
            for (int c : childCourse.get(courseId)) {
                inDegree.compute(c, (k, v) -> v-1);
            }
            inDegree.remove(courseId);
        }
        return res;
    }

}
