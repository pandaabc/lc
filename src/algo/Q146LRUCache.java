package algo;

import lc.ListNode;

import java.util.HashMap;
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
        }

        public int get(int key) {
            if (!values.containsKey(key)) {
                return -1;
            }
            ListNode node = values.get(key);
            if (node == head) {
                return node.val;
            }
            if (node != head) {
                node.pre.next = node.next;
            }
            if (node != end) {
                node.next.pre = node.pre;
            } else if (node.pre != null){
                end = node.pre;
            }
            node.next = head;
            head.pre = node;
            node.pre = null;
            head = node;

            return node.val;
        }

        public void put(int key, int value) {
            if (values.isEmpty()) {
                ListNode node = new ListNode(key, value);
                values.put(key, node);
                head = node;
                end = node;
            } else {
                ListNode node = values.computeIfAbsent(key, k -> new ListNode(key, value));
                node.val = value;
                values.put(key, node);
                node.next = head;
                head.pre = node;
                head = node;
                if (head == end && end.pre != null) {
                    end = end.pre;
                    end.next = null;
                }
                head.pre = null;
                if (values.size() > capacity) {
                    values.remove(end.key);
                    end = end.pre;
                    end.next = null;
                }
            }
        }
    }

}
