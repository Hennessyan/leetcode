package amazon;

import common.ListNode;

// Reverse Nodes in k-Group
public class Q25 {
    // O(n) O(n/k)
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode node = head;
        int count = 0;
        while(node != null && count < k) {
            node = node.next;
            count++;
        }
        if(count == k) {
            node = reverseKGroup(node, k);
            while(count-- > 0) {
                ListNode next = head.next;
                head.next = node;
                node = head;
                head = next;
            }
            head = node;
        }
        return head;
    }
    // O(n) O(1)
    // corner case => [1,2,3,4,5] 1 => [1,2,3,4,5]
    public ListNode reverseKGroup1(ListNode head, int k) {
        ListNode node = head, new_head = null, new_tail = null;

        while(node != null) {
            ListNode tmp = node;
            int count = 0;
            while(tmp != null && count < k) {
                tmp = tmp.next;
                count++;
            }
            if(count == k) {
                ListNode rev_head = reverseKlist(node, k);
                if(new_head == null) {
                    new_head = rev_head;
                }

                if(new_tail != null) {
                    new_tail.next = rev_head;
                }
                new_tail = node;
                node = tmp;
            } else {
                break;
            }
        }
        if(new_tail != null) {
            new_tail.next = node;
        }
        return new_head == null ? head : new_head;
    }

    public ListNode reverseKGroup2(ListNode head, int k) {
        if(head == null || head.next == null || k == 1) return head; //[1,2,3,4,5] 1 => [1,2,3,4,5]

        ListNode new_head = head, new_tail = null, cur = head;
        while(cur != null) {
            int count = 0;
            ListNode tmp = cur;
            while(tmp != null && count < k) {
                tmp = tmp.next;
                count++;
            }
            if(count < k) break;
            ListNode reverse_head = reverseKlist(cur, k);
            if(new_head == head) {
                new_head = reverse_head;
            }
            if(new_tail != null) {
                new_tail.next = reverse_head;
            }
            new_tail = cur;
            cur = tmp;
        }
        if(new_tail != null) {
            new_tail.next = cur;
        }

        return new_head;
    }

    private ListNode reverseKlist(ListNode head, int k) {
        ListNode pre = null, node = head;
        while(k-- > 0) {
            ListNode next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }
}
