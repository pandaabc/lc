package lc;

import java.util.HashMap;
import java.util.Map;

public class Q460LFUCache {
}

class LFUCache {

    int[] nums;
    int[] values;
    int[] counts;
    int capacity;
    int minIndex;
    Map<Integer, Integer> numIndex;
    public LFUCache(int capacity) {
        this.nums = new int[capacity];
        this.capacity = capacity;
        this.minIndex = capacity - 1;
        this.counts = new int[capacity];
        this.numIndex = new HashMap<>();
        this.values = new int[capacity];
    }

    public int get(int key) {
        if (!numIndex.containsKey(key)) {
            return -1;
        }
        int index = numIndex.get(key);
        counts[index] += 1;
        int value = nums[index];
        while (index < capacity - 1 && nums[index] >= nums[index + 1]) {
            swap(index, index + 1);
            index ++;
        }
        return value;
    }

    public void put(int key, int value) {
        int index = 0;
        if (numIndex.containsKey(key)) {
            index = numIndex.get(key);
            nums[index] = value;
        } else {
            if (minIndex >= 0) {
                nums[minIndex] = value;
                numIndex.put(key, minIndex);
                minIndex --;
            } else {
                numIndex.remove(nums[0]);
                numIndex.put(key, 0);
                counts[0] = 0;
            }
        }
        while (index < capacity - 1 && nums[index] >= nums[index + 1]) {
            swap(index, index + 1);
            index ++;
        }
    }

    private void swap(int i, int j) {
        int numT = nums[i];
        int countT = counts[i];
        nums[i] = nums[j];
        counts[i] = counts[j];
        nums[j] = numT;
        counts[j] = countT;
        numIndex.put(numT, j);
        numIndex.put(nums[i], i);
    }
}