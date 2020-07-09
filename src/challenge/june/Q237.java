package challenge.june;

import common.ListNode;

// Delete Node in a Linked List
public class Q237 {

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
