package challenge.dec;
// Populating Next Right Pointers in Each Node II
public class Q117 {
    // BFS
    public Node connect0(Node root) {
        if(root == null) {
            return root;
        }
        Node dummy = new Node(0);
        Node cur = root;
        while(cur != null) {
            Node tmp = dummy;
            while(cur != null) {
                if(cur.left != null) {
                    tmp.next = cur.left;
                    tmp = tmp.next;
                }
                if(cur.right != null) {
                    tmp.next = cur.right;
                    tmp = tmp.next;
                }
                cur = cur.next;
            }
            cur = dummy.next;
            dummy.next = null;
        }
        return root;
    }
    // DFS
    public Node connect(Node root) {
        if(root == null) {
            return root;
        }
        if(root.left != null) {
            root.left.next = root.right != null ? root.right : getNext(root.next);
        }
        if(root.right != null) {
            root.right.next = getNext(root.next);
        }
        connect(root.right);
        connect(root.left);
        return root;
    }
    private Node getNext(Node root) {
        while(root != null) {
            if(root.left != null) {
                return root.left;
            }
            if(root.right != null) {
                return root.right;
            }
            root = root.next;
        }
        return null;
    }
}
