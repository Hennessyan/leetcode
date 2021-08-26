package google;
// Change the Root of a Binary Tree
public class Q1666 {
    /**
     * [5,6,2,null,null,7,4]
     * 7
     * L8-L9 is wrong, because excluding root is mentioned in the question !!!
     * should return based on description: [7,2,null,5,4,null,6], but expected answer is [7,2,null,5,4,6]
     * Example2 violates the rule, but shows how to process root node.
     */
    // method1
    public Node flipBinaryTree(Node root, Node leaf) {
        flip(leaf, null);
        return leaf;
    }
    private void flip(Node node, Node prev) {
        if(node.parent == null) {
            if(node.left == prev) {
                node.left = null;
            } else {
                node.right = null;
            }
            node.parent = prev;
            return;
        }
        if(node.right == prev) {
            node.right = node.left;
        }
        Node next = node.parent;
        node.left = next;
        node.parent = prev;
        flip(next, node);
    }

    public Node flipBinaryTree2(Node root, Node leaf) {
        Node cur = leaf, prev = null;
        while(cur != root) {                // stop at root because root node does not follow the rule.
            Node parent = cur.parent, left = cur.left;
            if(left != null) {
                cur.right = left;
            }
            cur.left = parent;
            if(parent.left == cur) {
                parent.left = null;
            } else {
                parent.right = null;
            }
            cur.parent = prev;
            prev = cur;
            cur = parent;
        }
        cur.parent = prev;                // need to modify parent of root node here.
        return leaf;
    }


    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }
}
