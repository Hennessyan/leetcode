package facebook;

import common.ListNode;

// Linked List Cycle II
public class Q142 {

    public ListNode detectCycle(ListNode head) {
        ListNode a = head, b = head;
        while(b != null && b.next != null) {
            a = a.next;
            b = b.next.next;
            if(a == b) {
                break;
            }
        }
        if(b == null || b.next == null) {
            return null;
        }
        a = head;
        while(a != b) {
            a = a.next;
            b = b.next;
        }
        return a;
    }
}
