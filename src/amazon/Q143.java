package amazon;

import common.ListNode;

// Reorder List
public class Q143 {

    public void reorderList(ListNode head) {
        if(head == null || head.next == null) {
            return;
        }
        ListNode l1 = head, l2 = head;
        while(l2.next != null && l2.next.next != null) {
            l1 = l1.next;
            l2 = l2.next.next;
        }
        ListNode l = l1.next, pre = null;
        while(l != null) {
            ListNode next = l.next;
            l.next = pre;
            pre = l;
            l = next;
        }
        l1.next = pre;
        l = l1;
        l2 = pre;
        l1 = head;
        while(l1 != l) {
            l.next = l2.next;
            l2.next = l1.next;
            l1.next = l2;
            l1 = l2.next;
            l2 = l.next;
        }
    }
}
