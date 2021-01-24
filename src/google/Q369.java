package google;

import common.ListNode;

// Plus One Linked List
public class Q369 {
    // O(n) O(1)
    public ListNode plusOne(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode notNine = dummy;

        while(head != null) {
            if(head.val != 9) {
                notNine = head;
            }
            head = head.next;
        }

        notNine.val += 1;
        notNine = notNine.next;
        while(notNine != null) {
            notNine.val = 0;
            notNine = notNine.next;
        }

        return dummy.val == 1 ? dummy : dummy.next;
    }
}
