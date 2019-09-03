package lc;

import java.util.TreeMap;

public class Q1146SnapshotArray {

    int nextSnapId;
    TreeMap<Integer, Integer>[] array;
    public Q1146SnapshotArray(int length) {
        this.nextSnapId = 0;
        this.array = new TreeMap[length];
        for (int i = 0; i < length; i ++) {
            array[i] = new TreeMap<>();
            array[i].put(0, 0);
        }
    }

    public void set(int index, int val) {
        array[index].put(nextSnapId, val);
    }

    public int snap() {
        nextSnapId ++;
        return nextSnapId - 1;
    }

    public int get(int index, int snap_id) {
        TreeMap<Integer, Integer> vals = array[index];
        return vals.lowerEntry(snap_id + 1).getValue();
    }
}
