package microsoft;

import common.ListNode;

// Reverse Nodes in k-Group
public class Q25 {

    public ListNode reverseKGroup(ListNode head, int k) {
        int len = getLength(head);
        int times = len / k;
        if(times == 0) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy, pre = null, node = null, next = null;
        for(int i = 0; i < times; i++) {
            node = cur.next;
            for(int j = 0; j < k; j++) {
                next = node.next;
                node.next = pre;
                pre = node;
                node = next;
            }
            cur.next.next = node;
            next = cur.next;
            cur.next = pre;
            cur = next;
        }
        return dummy.next;
    }
    private int getLength(ListNode head) {
        int len = 0;
        while(head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    /*method2 recursion*/
    //because of recursion, space is O(n). (stack)
    public ListNode reverseKGroup1(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;
        while (curr != null && count != k) { // find the k+1 node
            curr = curr.next;
            count++;
        }
        if (count == k) { // if k+1 node is found
            curr = reverseKGroup1(curr, k); // reverse list with k+1 node as head
            // head - head-pointer to direct part,
            // curr - head-pointer to reversed part;
            while (count-- > 0) { // reverse current k-group:
                ListNode tmp = head.next; // tmp - next head in direct part
                head.next = curr; // preappending "direct" head to the reversed list
                curr = head; // move head of reversed part to a new node
                head = tmp; // move "direct" head to the next node in direct part
            }
            head = curr;
        }
        return head;
    }
}
