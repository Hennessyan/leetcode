package challenge;

import common.ListNode;

// Convert Binary Number in a Linked List to Integer
public class Q1290 {
    // O(n) O(1)
    public int getDecimalValue(ListNode head) {
        if(head == null) return 0;
        int ans = 0;
        ListNode node = head;
        while(node != null) {
            ans = ans << 1 | node.val;
            node = node.next;
        }
        return ans;
    }

    public int getDecimalValue1(ListNode head) {
        if(head == null) return 0;
        int ans = 0;
        ListNode node = head;
        while(node != null) {
            ans = (ans << 1) + node.val;
            node = node.next;
        }
        return ans;
    }
    // fast
    public int getDecimalValue12(ListNode head) {
        if(head == null) return 0;
        int ans = 0;
        ListNode node = head;
        while(node != null) {
            ans = ans * 2 + node.val;
            node = node.next;
        }
        return ans;
    }
}
