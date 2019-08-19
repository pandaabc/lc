package lc;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;


/**
 * A better approach would be hashmap + binary search in a list.. this one is a little slower.
 */
public class Q981TimeBasedKVStore {

    Map<String, TreeSet<ValueTimePair>> map;

    /** Initialize your data structure here. */
    public Q981TimeBasedKVStore() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        ValueTimePair p = new ValueTimePair(value, timestamp);
        map.computeIfAbsent(key, k -> new TreeSet<>(comparator)).add(p);
    }

    public String get(String key, int timestamp) {
        TreeSet<ValueTimePair> allPairs = map.get(key);
        if (allPairs == null || allPairs.isEmpty()) {
            return "";
        }
        ValueTimePair p = allPairs.higher(new ValueTimePair("", timestamp + 1));
        return p == null ? "" : p.value;
    }

    Comparator<ValueTimePair> comparator = Comparator.comparing(ValueTimePair::getTimeStamp).reversed();
}


class ValueTimePair {
    String value;
    int timeStamp;
    public ValueTimePair(String value, int timeStamp) {
        this.value = value;
        this.timeStamp = timeStamp;
    }
    public int getTimeStamp() {
        return timeStamp;
    }
}
