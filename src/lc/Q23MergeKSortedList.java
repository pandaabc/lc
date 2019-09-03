package lc;

import java.util.*;

public class Q23MergeKSortedList {
    public ListNode mergeKLists(ListNode[] lists) {
        Map<ListNode, Integer> nodeIndexMap = new HashMap<>();
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparing(e -> e.val));
        for (int i = 0; i < lists.length; i ++) {
            nodeIndexMap.put(lists[i], i);
            queue.offer(lists[i]);
        }

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (queue.size() > 0) {
            ListNode node = queue.poll();
            if (dummy.next == null) {
                dummy.next = node;
            } else {
                cur.next = node;
            }

            if (node.next != null) {
                queue.offer(node.next);
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}
