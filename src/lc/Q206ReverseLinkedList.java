package lc;

/**
 * Reverse a singly linked list.
 *
 * Example:
 *
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * Follow up:
 *
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class Q206ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);

        while (head != null) {

            ListNode temp = dummy.next;
            dummy.next = head;
            head = head.next;
            dummy.next.next = temp;

        }

        return dummy.next;
    }

}

