package google;

import common.TreeNode;

// Diameter of Binary Tree
public class Q543 {
    int max;
    public int diameterOfBinaryTree(TreeNode root) {
        max = 0;
        helper(root);
        return max;
    }
    private int helper(TreeNode node) {
        if(node == null) {
            return 0;
        }
        int left = helper(node.left);
        int right = helper(node.right);
        max = Math.max(max, left + right);
        return Math.max(left, right) + 1;
    }
}
