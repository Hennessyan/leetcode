package google;

import common.TreeNode;

// Distribute Coins in Binary
// next Q969 Q1648
public class Q979 {
    // O(n) O(n)
    int ans;
    public int distributeCoins(TreeNode root) {
        ans = 0;
        dfs(root);
        return ans;
    }
    private int dfs(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int l = dfs(root.left);
        int r = dfs(root.right);
        ans += Math.abs(l) + Math.abs(r);
        return root.val + l + r - 1;
    }
}
