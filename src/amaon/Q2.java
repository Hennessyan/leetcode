package amaon;

import common.ListNode;

// Add Two Numbers
public class Q2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum = 0, carry = 0;
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while(l1 != null || l2 != null || carry != 0) {
            sum = carry;
            if(l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if(l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            cur.next = new ListNode(sum % 10);
            carry = sum / 10;
            cur = cur.next;
        }
        return dummy.next;
    }
}
