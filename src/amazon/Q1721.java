package amazon;

import common.ListNode;

// Swapping Nodes in a Linked List
public class Q1721 {
    // two pass : O(n) O(1)
    public ListNode swapNodes1(ListNode head, int k) {
        int len = 0;
        ListNode cur = head, start = null, end = null;
        while(cur != null) {
            len++;
            if(len == k) {
                start = cur;
            }
            cur = cur.next;
        }
        end = head;
        for(int i = 1; i <= len - k; i++) {
            end = end.next;
        }
        int tmp = start.val;
        start.val = end.val;
        end.val = tmp;
        return head;
    }
    // one pass : O(n) O(1)
    // end node is k nodes behind cur node
    public ListNode swapNodes(ListNode head, int k) {
        int len = 0;
        ListNode cur = head, start = null, end = null;
        while(cur != null) {
            len++;
            if(end != null) {
                end = end.next;
            }
            if(len == k) {
                start = cur;
                end = head;
            }
            cur = cur.next;
        }
        int tmp = start.val;
        start.val = end.val;
        end.val = tmp;
        return head;
    }
    // swap node will be more complex, check below method:
    public ListNode swapNodes0(ListNode head, int k) {
        int len = 0;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy, start = null, end = null;
        while(cur != null) {
            len++;
            if(end != null) {
                end = end.next;
            }
            if(len == k) {
                start = cur;
            }
            if(len == k + 1) {      // !!!!!!!
                end = dummy;
            }
            cur = cur.next;
        }
        if(start.next == end) {         // [1,2,3,4,5,6,7,8] 4 !!!
            ListNode c1 = start.next;
            ListNode c2 = end.next;
            c1.next = c2.next;
            c2.next = c1;
            start.next = c2;
        } else if(end.next == start) {  // [1,2] 2 !!!
            ListNode c1 = start.next;
            ListNode c2 = end.next;
            c2.next = c1.next;
            c1.next = c2;
            end.next = c1;
        } else {
            ListNode next1 = start.next.next;
            start.next.next = end.next.next;
            end.next.next = next1;

            ListNode tmp = end.next;
            end.next = start.next;
            start.next = tmp;

        }
        return dummy.next;
    }
}
