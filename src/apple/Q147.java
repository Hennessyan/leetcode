package apple;

import common.ListNode;

// Insertion Sort List
public class Q147 {
    // O(n^2) O(1)
    public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(), cur = head, next;
        while(cur != null) {
            ListNode pre = dummy;
            while(pre.next != null && pre.next.val <= cur.val) {
                pre = pre.next;
            }
            next = cur.next;
            cur.next = pre.next;
            pre.next = cur;
            cur = next;
        }
        return dummy.next;
    }
}
