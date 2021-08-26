package microsoft;

import common.ListNode;

// Reverse Linked List II
public class Q92 {
    // *** check all methods in solutions !!!
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null)
            return head;
        ListNode fake_head = new ListNode(0);
        fake_head.next = head;

        //find begin point
        ListNode prev = fake_head;
        for(int i = 0; i < m - 1; i++)
            prev = prev.next;

        ListNode cur = prev.next;
        ListNode new_head = null;
        for(int i = 0; i <= n - m; i++){
            ListNode next = cur.next;
            cur.next = new_head;
            new_head = cur;
            cur = next;
        }

        prev.next.next = cur;
        prev.next = new_head;

        return fake_head.next;
    }
    /*method3 complex*/
//	public ListNode reverseBetween(ListNode head, int m, int n) {
//	    if(head == null) return null;
//	    ListNode dummy = new ListNode(0); // create a dummy node to mark the head of this list
//	    dummy.next = head;
//	    ListNode pre = dummy; // make a pointer pre as a marker for the node before reversing
//	    for(int i = 0; i<m-1; i++) pre = pre.next;
//
//	    ListNode start = pre.next; // a pointer to the beginning of a sub-list that will be reversed
//	    ListNode then = start.next; // a pointer to a node that will be reversed
//
//	    // 1 - 2 -3 - 4 - 5 ; m=2; n =4 ---> pre = 1, start = 2, then = 3
//	    // dummy-> 1 -> 2 -> 3 -> 4 -> 5
//
//	    for(int i=0; i<n-m; i++)
//	    {
//	        start.next = then.next;
//	        then.next = pre.next;
//	        pre.next = then;
//	        then = start.next;
//	    }
//
//	    // first reversing : dummy->1 - 3 - 2 - 4 - 5; pre = 1, start = 2, then = 4
//	    // second reversing: dummy->1 - 4 - 3 - 2 - 5; pre = 1, start = 2, then = 5 (finish)
//
//	    return dummy.next;
//
//	}
}
