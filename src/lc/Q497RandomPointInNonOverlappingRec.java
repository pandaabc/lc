package lc;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

public class Q497RandomPointInNonOverlappingRec {
    Map<Integer, Integer> idxMap;
    int[] recSize;
    int max;
    int[][] rects;
    public Q497RandomPointInNonOverlappingRec(int[][] rects) {
        this.recSize = new int[rects.length];
        this.rects = rects;
        int size = 0;
        for (int i = 0; i < rects.length; i ++) {
            size += (rects[i][2] - rects[i][0]) * (rects[i][3] - rects[i][1]);
            this.recSize[i] = size;
        }
        max = size;
    }

    public int[] pick() {
        int picker = new Random().nextInt(max + 1);
        int idx = Arrays.binarySearch(recSize, picker);
        idx = idx > 0 ? idx : - idx - 1;
        picker = idx >= 0 ? picker - recSize[idx - 1] : picker;
        int y = picker / (rects[idx][2] - rects[idx][0]);
        int x = picker % (rects[idx][2] - rects[idx][0]);
        return new int[]{x, y};
    }
}
