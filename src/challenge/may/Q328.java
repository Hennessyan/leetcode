package challenge.may;

import common.ListNode;

// Odd Even Linked List
public class Q328 {

    // Input: 2->1->3->5->6->4->7->NULL
    // Output: 2->3->6->7->1->5->4->NULL
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode odd = head, even = head.next, evenHead = even;
        while(even != null && even.next != null) {
            odd = odd.next = even.next;
            even = even.next = odd.next;
        }
        odd.next = evenHead;
        return head;
    }
}
