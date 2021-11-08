package microsoft;

import common.TreeNode;

// Lowest Common Ancestor of a Binary Tree II
// Q236
public class Q1644 {

    int count = 0;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode dfs = dfs(root, p, q);
        return count == 2 ? dfs : null;

    }
    private TreeNode dfs(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null) return null;

        TreeNode left = dfs(root.left, p, q);
        TreeNode right = dfs(root.right, p, q);
        if (root == q || root == p) {
            count++;
            return root;
        }
        if (left == null) return right;
        if (right == null) return left;

        return root;
    }

    TreeNode ans;
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        ans = null;
        help(root, p, q);
        return ans;
    }
    private boolean help(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return false;
        int l = help(root.left, p, q) ? 1 : 0;
        int r = help(root.right, p, q) ? 1 : 0;
        int cur = (root == p || root == q) ? 1 : 0;
        if(l + r + cur == 2) ans = root;
        return l + r + cur > 0;
    }
}
