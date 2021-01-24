package challenge.dec;

import java.util.LinkedList;
import java.util.Queue;

// Populating Next Right Pointers in Each Node
public class Q116 {

    //BFS : O(n) O(n)
    public Node connect(Node root) {
        if(root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                Node node = queue.poll();
                if(i + 1 < size) {
                    node.next = queue.peek();
                }
                if(node.left != null) {
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
        }
        return root;
    }
    //BFS : O(n) O(1)
    public Node connect1(Node root) {
        if(root == null) {
            return null;
        }
        Node node = root;
        while(node.left != null) {
            Node cur = node;
            while(cur != null) {
                cur.left.next = cur.right;
                if(cur.next != null) {
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            node = node.left;
        }
        return root;
    }
    //Recursion DFS : O(n) O(lgn)
    public Node connect2(Node root) {
        if(root == null) {
            return null;
        }
        if(root.left != null){
            root.left.next = root.right;
            if(root.next != null)
                root.right.next = root.next.left;
            connect(root.left);
            connect(root.right);
        }
        return root;
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}
