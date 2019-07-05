package lc;

import java.util.List;

public class Q142LinkedListCycleII {
    public class Solution {
        public ListNode detectCycle(ListNode head) {
            // two pointers.
            // one pointer move 2 steps.
            // another point move 1 step

            ListNode p1 = head;
            ListNode p2 = head;
            boolean meet = false;

            while (p2 != null && p2.next != null && p2.next.next != null) {

                p2 = p2.next.next;
                p1 = p1.next;

                if (p1 == p2) {
                    meet = true;
                    break;
                }

            }

            if (!meet) {
                return null;
            }
            // send another point from head.  whenever they meet, it is the starting point of the cycle

            ListNode res = head;

            while (res != p1) {
                res = res.next;
                p1 = p1.next;
            }

            return res;

        }
    }
}
