package microsoft;
// Inorder Successor in BST II
public class Q510 {

    // O(h) O(1)
    public Node inorderSuccessor(Node node) {
        if(node == null) {
            return node;
        }
        if(node.right != null) {
            return find(node.right);
        }
        while(node.parent != null && node.parent.left != node) {
            node = node.parent;
        }
        return node.parent;
    }
    private Node find(Node node) {
        while(node.left != null) {
            node = node.left;
        }
        return node;
    }

}
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}