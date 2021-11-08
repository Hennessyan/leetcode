package amazon;

import common.TreeNode;

// Binary Tree Maximum Path Sum
public class Q124 {
    //
    int ans;
    public int maxPathSum(TreeNode root) {
        ans = Integer.MIN_VALUE;
        dfs(root);
        return ans;
    }
    private int dfs(TreeNode node) {
        if(node == null) return 0;
        int l = Math.max(dfs(node.left), 0);
        int r = Math.max(dfs(node.right), 0);
        ans = Math.max(ans, l + r + node.val);
        return node.val + Math.max(l, r);
    }
}
