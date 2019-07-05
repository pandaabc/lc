package lc;

import java.util.Arrays;

public class Q302 {

    public static void main(String[] args) {
        Q302 q = new Q302();
        char[][] img = new char[][]{{'0','0','1','0'},{'0','1','1','0'},{'0','1','0','0'}};
        q.minArea(img, 0, 2);
    }

    public int minArea(char[][] image, int x, int y) {

        int x0 = binarySearch(image, 0, x, false, true);
        int x1 = binarySearch(image, x, image[0].length, true, true);
        int y0 = binarySearch(image, 0, y, false, false);
        int y1 = binarySearch(image, y, image.length, true, false);

        return (x1 - x0) * (y1 - y0);

    }

    private int binarySearch(char[][] image, int i0, int i1, boolean goPositive, boolean horizontal) {

        if (i0 >= i1) {
            if (goPositive) {
                return i1;
            } else {
                return i0;
            }
        }

        int i = (i0 + i1)/2;

        boolean isArea = isArea(image, i, horizontal);

        if (isArea) {
            if (goPositive) {
                i0 = i + 1;
            } else {
                i1 = i - 1;
            }
        } else {
            if (goPositive) {
                i1 = i - 1;
            } else {
                i0 = i + 1;
            }
        }

        return binarySearch(image, i0, i1, goPositive, horizontal);


    }

    private boolean isArea(char[][] image, int x, boolean horizontal) {
        if (horizontal) {
            for (int i = 0; i < image.length; i ++) {
                if (image[i][x] == '1') {
                    return true;
                }
            }
            return false;
        }

        for (int i = 0; i < image[x].length; i ++) {
            if (image[x][i] == '1'){
                return true;
            }
        }
        return false;
    }


}
