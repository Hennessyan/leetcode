package amazon;
// Lowest Common Ancestor of a Binary Tree III
public class Q1650 {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }
    // same as two linkedlist issue.
    public Node lowestCommonAncestor(Node p, Node q) {
        Node a = p, b = q;
        while(a != b) {
            a = a == null ? q : a.parent;
            b = b == null ? p : b.parent;
        }
        return a;
    }
}
