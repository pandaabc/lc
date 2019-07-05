package algo;

import lc.ListNode;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 */
public class Q148SortList_MergeSort_DivideConquer {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        return mergeSort(head);
    }

    private ListNode mergeSort(ListNode node) {

        // step 1: find mid point to separate
        int cnt1 = 0;
        int cnt2 = 0;
        ListNode part1 = node;
        ListNode dummy = new ListNode(0);
        dummy.next = node;
        while (node != null) {
            cnt2 ++;
            if (cnt2 % 2 == 0) {
                cnt1 ++;
                dummy = dummy.next;
            }
            node = node.next;
        }
        // if only 1 element, return
        if (cnt2 == 1) {
            return part1;
        }
        // break the chain
        ListNode part2 = dummy.next;
        dummy.next = null;
        // if only 2 elements, sort
        return mergeNodes(mergeSort(part1), mergeSort(part2));
    }

    private ListNode mergeNodes(ListNode node1, ListNode node2) {
        //System.out.println(node1.val +" "+node2.val);
        ListNode head = new ListNode(0);
        ListNode cur = head;

        while (node1 != null || node2 != null) {
            if (node1 == null) {
                cur.next = node2;
                break;
            }
            if (node2 == null) {
                cur.next = node1;
                break;
            }
            if (node1.val < node2.val) {
                cur.next = node1;
                node1 = node1.next;
            } else {
                cur.next = node2;
                node2 = node2.next;
            }
            cur = cur.next;
        }

        return head.next;
    }
}
