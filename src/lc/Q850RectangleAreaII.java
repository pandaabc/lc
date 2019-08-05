package lc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * idea:
 * first get all possible x
 * get all possible y
 * and set it as a matrix, fill in the matrix
 */
public class Q850RectangleAreaII {

    public int rectangleArea(int[][] rectangles) {

        List<Integer> xs = new ArrayList<>();
        List<Integer> ys = new ArrayList<>();
        for (int i = 0; i < rectangles.length; i ++) {
            xs.add(rectangles[i][0]);
            xs.add(rectangles[i][2]);
            ys.add(rectangles[i][1]);
            ys.add(rectangles[i][3]);
        }
        List<Integer> xs_sort = xs.stream().distinct().sorted().collect(Collectors.toList());
        List<Integer> ys_sort = ys.stream().distinct().sorted().collect(Collectors.toList());
        boolean[][] filled = new boolean[xs_sort.size()][ys_sort.size()];

        for (int i = 0; i < rectangles.length; i ++) {
            int istart = Collections.binarySearch(xs_sort, rectangles[i][0]);
            int iend = Collections.binarySearch(xs_sort, rectangles[i][2]);
            int jstart = Collections.binarySearch(ys_sort, rectangles[i][1]);
            int jend = Collections.binarySearch(ys_sort, rectangles[i][3]);
            for (int l = istart; l < iend; l ++) {
                for (int m = jstart; m < jend; m ++) {
                    filled[l][m] = true;
                }
            }
        }

        long res = 0;
        for (int i = 0; i < filled.length - 1; i ++) {
            for (int j = 0; j < filled[0].length - 1; j ++) {
                if (filled[i][j]) {
                    res += (((long)xs_sort.get(i + 1) - (long)xs_sort.get(i)) * ((long)ys_sort.get(j + 1) - (long)ys_sort.get(j))) % 1_000_000_007;
                    res %= 1_000_000_007;
                }
            }
        }

        return (int)res % 1_000_000_007;
    }



}
