package microsoft;

import common.ListNode;

// Linked List Cycle II
public class Q142 {

    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) {
            return null;
        }
        ListNode l1 = head, l2 = head;
        while(l2 != null && l2.next != null) {
            l1 = l1.next;
            l2 = l2.next.next;
            if(l1 == l2) {
                break;
            }
        }
        if(l2 == null || l2.next == null) {
            return null;
        }
        l1 = head;
        while(l1 != l2) {
            l1 = l1.next;
            l2 = l2.next;
        }
        return l1;
    }
}
