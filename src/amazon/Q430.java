package amazon;

import java.util.ArrayDeque;
import java.util.Deque;

// Flatten a Multilevel Doubly Linked List
public class Q430 {

    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }
    // O(n) O(n) Preorder traversal
    public Node flatten(Node head) {
        if(head == null) return null;
        Node dummy = new Node(), pre = dummy, cur = null;
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(head);
        while(!stack.isEmpty()) {
            cur = stack.pop();
            pre.next = cur;
            cur.prev = pre;
            if(cur.next != null) {
                stack.push(cur.next);
            }
            if(cur.child != null) {
                stack.push(cur.child);
                cur.child = null;
            }
            pre = cur;
        }
        head.prev = null;
        return head;
    }

    public Node flatten1(Node head) {
        if(head == null) return null;
        Node pre = new Node();
        preorder(head, pre);
        head.prev = null;
        return head;
    }
    private Node preorder(Node node, Node pre) {
        if(node == null) return pre;    // return pre in case node.child is null
        pre.next = node;
        node.prev = pre;

        Node tmp = node.next;
        Node tail = preorder(node.child, node);
        node.child = null;

        return preorder(tmp, tail);
    }
}
