package facebook;
// Insert into a Sorted Circular Linked List
public class Q708 {

    class Node {
        public int val;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }

    public Node insert(Node head, int val) {
        if(head == null) {
            Node node = new Node(val);
            node.next = node;
            return node;
        }
        Node node = head;
        while(node.next != head) {
            if(node.val <= node.next.val) {
                if(node.val <= val && node.next.val >= val) {
                    break;
                }
            } else {
                if(node.val <= val || node.next.val >= val) {
                    break;
                }
            }
            node = node.next;
        }
        node.next = new Node(val, node.next);
        return head;
    }
}
