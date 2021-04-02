package amazon;

import common.ListNode;

// Intersection of Two Linked Lists
public class Q160 {

    /*method1 shorter version*/
//	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//	    int lenA = length(headA), lenB = length(headB);
//	    // move headA and headB to the same start point
//	    while (lenA > lenB) {
//	        headA = headA.next;
//	        lenA--;
//	    }
//	    while (lenA < lenB) {
//	        headB = headB.next;
//	        lenB--;
//	    }
//	    // find the intersection until end
//	    while (headA != headB) {
//	        headA = headA.next;
//	        headB = headB.next;
//	    }
//	    return headA;
//	}
//
//	private int length(ListNode node) {
//	    int length = 0;
//	    while (node != null) {
//	        node = node.next;
//	        length++;
//	    }
//	    return length;
//	}


    /*method2*/
    //Time complexity : O(m*n)
    //Space complexity: O(1)
    //(Brute Force) [Time Limit Exceeded]
    //For each node ai in list A, traverse the entire list B and check if any node in list B coincides with ai.

    /*method3*/
    //Time complexity : O(m+n)
    //Space complexity: O(m) or O(n)
    //(Hash Table) [Accepted]
    //Traverse list A and store the address / reference to each node in a hash set.
    //Then check every node bi in list B: if bi appears in the hash set, then bi is the intersection node.

    /*method4*/
    //(Two Pointers) [Accepted]
    //Time complexity : O(m+n)
    //Space complexity: O(1)
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //boundary check
        if(headA == null || headB == null)
            return null;
        ListNode a = headA;
        ListNode b = headB;

        //if a & b have different len, then we will stop the loop after second iteration
        //因为两个list在自己的结束后接的是对方的list，整体的长度（循环次数）是一样的.
        //因此就算没有intersection,也会同时遇到null结束,然后停止while循环!
        while(a != b){
            //for the end of first iteration, we just reset the pointer to the head of another linkedlist
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }
}
