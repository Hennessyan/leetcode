package amazon;

import common.TreeNode;

// Flatten Binary Tree to Linked List
public class Q114 {
    // O(n) O(n)
    public void flatten(TreeNode root) {
        help(root);
    }
    private TreeNode help(TreeNode node) {
        if(node == null) return null;

        if(node.left == null && node.right == null) return node; // necessary

        TreeNode ltail = help(node.left);
        TreeNode rtail = help(node.right);
        if(ltail != null) {
            ltail.right = node.right;
            node.right = node.left;
            node.left = null;
        }
        // always return the tail node
        return rtail == null ? ltail : rtail;
    }
    // O(n) O(1)
    public void flatten1(TreeNode root) {
        if(root == null) return;
        TreeNode node = root;
        while(node != null) {
            if(node.left != null) {
                TreeNode tmp = node.left;
                while(tmp.right != null) {
                    tmp = tmp.right;
                }
                tmp.right = node.right;
                node.right = node.left;
                node.left = null;
            }
            node = node.right;
        }
    }
}
