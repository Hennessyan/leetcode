package amazon;

import common.TreeNode;

// Balanced Binary Tree
public class Q110 {
    // O(n) O(n)
    public boolean isBalanced(TreeNode root) {
        return help(root) >= 0;
    }
    private int help(TreeNode node) {
        if(node == null) return 0;
        int l = help(node.left);
        int r = help(node.right);
        if(l == -1 || r == -1 || Math.abs(l - r) > 1) return -1;
        return Math.max(l, r) + 1;
    }
}
