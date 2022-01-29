package facebook;

import common.TreeNode;

// Maximum Difference Between Node and Ancestor
public class Q1026 {
    // O(n) O(n)
    int ans;
    public int maxAncestorDiff(TreeNode root) {
        ans = 0;
        helper(root, root.val, root.val);
        return ans;
    }
    private void helper(TreeNode node, int min, int max) {
        if(node == null) return;
        ans = Math.max(ans, Math.max(Math.abs(min - node.val), Math.abs(max - node.val)));
        min = Math.min(node.val, min);
        max = Math.max(node.val, max);
        helper(node.left, min, max);
        helper(node.right, min, max);
    }

    public int maxAncestorDiff1(TreeNode root) {
        if(root == null) return 0;
        return helper1(root, root.val, root.val);
    }
    private int helper1(TreeNode node, int max, int min) {
        if(node == null) return max - min;
        max = Math.max(max, node.val);
        min = Math.min(min, node.val);
        int l = helper1(node.left, max, min);
        int r = helper1(node.right, max, min);
        return Math.max(l, r);
    }
}
