package microsoft;

import common.ListNode;

// Linked List Cycle
public class Q141 {

    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) {
            return false;
        }
        ListNode l1 = head, l2 = head;
        while(l2 != null && l2.next != null) {
            l1 = l1.next;
            l2 = l2.next.next;
            if(l1 == l2) {
                return true;
            }
        }
        return false;
    }
}
