package microsoft;

import common.ListNode;

// Reverse Linked List
public class Q206 {

    /*method2=1 similar as method1*/
    //Iterative
//	public ListNode reverseList(ListNode head) {
//		ListNode prev = null, cur = head;
//		while(cur != null){
//			ListNode next = cur.next;
//			cur.next = prev;
//			prev = cur;
//			cur = next;
//		}
//		return prev;
//	}
    /*method2*/
    //Recursive
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
}
