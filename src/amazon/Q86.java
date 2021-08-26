package amazon;

import common.ListNode;

// Partition List
public class Q86 {
    // O(n) O(1)
    public ListNode partition(ListNode head, int x) {

        ListNode before_head = new ListNode(0);
        ListNode before = before_head;
        ListNode after_head = new ListNode(0);
        ListNode after = after_head;

        while (head != null) {

            if (head.val < x) {
                before.next = head;
                before = before.next;
            } else {
                after.next = head;
                after = after.next;
            }

            head = head.next;
        }

        after.next = null;
        before.next = after_head.next;

        return before_head.next;
    }

    public ListNode partition1(ListNode head, int x) {
        if(head == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy, pre = dummy;

        while(cur.next != null) {
            if(cur.next.val < x) {
                if(pre == cur) {
                    pre = pre.next;
                    cur = cur.next;
                } else {
                    ListNode tmp = cur.next;
                    cur.next = tmp.next;
                    tmp.next = pre.next;
                    pre.next = tmp;
                    pre = tmp;
                }
            } else {
                cur = cur.next;
            }

        }
        return dummy.next;
    }

    public ListNode partition2(ListNode head, int x) {
        if(head == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy, pre = dummy;
        while(cur != null) {
            while(cur.next != null && cur.next.val >= x) {
                cur = cur.next;
            }
            if(pre == cur) {
                pre = cur.next;
                cur = pre;
                continue;
            }
            if(cur.next != null) {
                ListNode tmp = cur.next;
                cur.next = tmp.next;
                tmp.next = pre.next;
                pre.next = tmp;
                pre = tmp;
            }else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
