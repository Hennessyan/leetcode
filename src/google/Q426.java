package google;

import java.util.Stack;

// Convert Binary Search Tree to Sorted Doubly Linked List
public class Q426 {
    // O(n) O(n)
    Node head = null, prev = null;
    public Node treeToDoublyList(Node root) {
        if(root == null) {  // necessary
            return null;
        }
        inorder(root);
        prev.right = head;
        head.left = prev;
        return head;
    }
    private void inorder(Node node) {
        if(node == null) return;
        inorder(node.left);
        if(head == null) head = node;   // can be replace by L26-L28
        if(prev != null) {
            prev.right = node;
            node.left = prev;
        }
//        } else {
//            head = node;
//        }
        prev = node;
        inorder(node.right);
    }

    public Node treeToDoublyList1(Node root) {
        if(root == null) {
            return root;
        }
        Node head = null, prev = null;
        Stack<Node> stack = new Stack<>();
        Node node = root;
        while(node != null || !stack.isEmpty()) {
            while(node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            if(head == null) {
                head = node;
            }
            if(prev != null) {
                prev.right = node;
                node.left = prev;
            }
            prev = node;
            node = node.right;
        }
        prev.right = head;
        head.left = prev;
        return head;
    }
    // O(n) O(1)
    public Node treeToDoublyList2(Node root) {
        if(root == null) {
            return root;
        }
        Node head = null, prev = null, node = root, tmp = null;
        while(node != null) {
            if(node.left == null) {
                if(head == null) head = node;
                if(prev != null) {                      // prev.right = node is necessary for below case:
                    prev.right = node;                  //      2
                    node.left = prev;                   //         5
                }                                       //       4    6
                prev = node;
                node = node.right;
            } else {
                tmp = node.left;
                while(tmp.right != null && tmp.right != node) {
                    tmp = tmp.right;
                }
                if(tmp.right == null) {
                    tmp.right = node;
                    node = node.left;
                }
                if(tmp.right == node) {
                    tmp.right = null;
                    prev.right = node;
                    node.left = prev;
                    prev = node;
                    node = node.right;
                }
            }
        }
        prev.right = head;
        head.left = prev;
        return head;
    }
    /*###########################*/
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
}
