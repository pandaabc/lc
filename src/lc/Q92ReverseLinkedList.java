package lc;

/**
 * Reverse a linked list from position m to n. Do it in one-pass.
 */
public class Q92ReverseLinkedList {

    public ListNode reverseBetween(ListNode head, int m, int n) {

        int cnt = 0;
        ListNode dummy = new ListNode(0);


        while (head != null) {
            cnt ++;
            if (m <= cnt && n >= cnt) {

            }
            dummy = head;
            head = head.next;
        }
        return null;
    }

}
