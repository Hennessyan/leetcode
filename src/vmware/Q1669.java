package vmware;

import common.ListNode;

// Merge In Between Linked Lists
public class Q1669 {

    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode cur = list1, start = null, end = null;
        int count = 1;
        while((start == null || end == null) && cur.next != null) {
            if(count == a) {
                start = cur;
            }
            if(count == b) {
                end = cur.next;
            }
            count++;
            cur = cur.next;
        }
        cur = list2;
        while(cur.next != null) {
            cur = cur.next;
        }
        cur.next = end.next;
        start.next = list2;
        return list1;
    }
}
