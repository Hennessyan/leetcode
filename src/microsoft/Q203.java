package microsoft;

import common.ListNode;

// Remove Linked List Elements
public class Q203 {

    public ListNode removeElements1(ListNode head, int val) {
        if(head == null) {
            return head;
        }
        head.next = removeElements1(head.next, val);
        return head.val == val ? head.next : head;
    }

    public ListNode removeElements(ListNode head, int val) {
        if(head == null) {
            return head;
        }
        ListNode cur = head;
        while(cur.next != null) {
            if(cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head.val == val ? head.next : head;
    }
}
