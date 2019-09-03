package algo;

import lc.ListNode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class Q146LRUCache {

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(2, 1);
        cache.put(1, 1);
        cache.put(2, 3);
        cache.put(4, 1);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));

    }

    static class LRUCache {

        private int capacity;
        private Map<Integer, ListNode> values;
        private ListNode head;
        private ListNode end;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            values = new HashMap<>();
            head = new ListNode(0, 0);
            end = new ListNode(0, 0);
            head.next = end;
            end.pre = head;
        }

        public int get(int key) {

            System.out.println(key);
            System.out.println(values);

            ListNode node = values.get(key);
            if (node == null) {
                return -1;
            }

            // take node out
            node.pre.next = node.next;
            node.next.pre = node.pre;

            // put it the first
            node.pre = head;
            node.next = head.next;
            head.next.pre = node;
            head.next = node;

            return node.val;
        }

        public void put(int key, int value) {

            ListNode node = null;
            if (values.containsKey(key)) {
                node = values.get(key);
                node.val = value;
                node.pre.next = node.next;
                node.next.pre = node.pre;
            } else {
                node = new ListNode(key, value);
                values.put(key, node);
            }
            // put node in first
            node.pre = head;
            node.next = head.next;
            head.next.pre = node;
            head.next = node;

            // check capacity
            if (values.size() > capacity) {
                values.remove(end.pre.key);
                end.pre = end.pre.pre;
                end.pre.next = end;
            }

            ListNode tp = head.next;
            System.out.println();
            while (tp != null) {
                System.out.print(tp.val);
                tp = tp.next;
            }
        }
    }

}

class LRUCache extends LinkedHashMap<Integer, Integer>{
    int capacity;
    public LRUCache(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return super.size() > capacity;
    }

}
