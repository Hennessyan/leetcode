package linkedin;

import common.ListNode;

// Rotate List
public class Q61 {
    // O(n) O(1)
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null || k == 0) return head;
        ListNode node = head;
        int count = 1;
        while(node.next != null) {
            node = node.next;
            count++;
        }
        k %= count;
        if(k == 0) return head;
        k = count - k;
        node.next = head;
        for(int i = 0; i < k; i++) {
            node = node.next;
        }
        head = node.next;
        node.next = null;
        return head;
    }
}
