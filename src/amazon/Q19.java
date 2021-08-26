package amazon;
// Remove Nth Node From End of List
import common.ListNode;

public class Q19 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy, fast = dummy;
        for(int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        while(fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy, node = dummy;
        while(n-- > 0) {
            cur = cur.next;
        }
        while(cur.next != null) {
            cur = cur.next;
            node=  node.next;
        }
        node.next = node.next.next;
        return dummy.next;
    }
}
