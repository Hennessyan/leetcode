package amazon;

import common.TreeNode;

// Lowest Common Ancestor of Deepest Leaves
// facebook
public class Q1123 {

    TreeNode LCA;
    int deepest;
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        LCA = null;
        deepest = 0;
        dfs(root, 0);
        return LCA;
    }
    private int dfs(TreeNode node, int d) {
        if(node == null) return d;
        int l = dfs(node.left, d + 1);
        int r = dfs(node.right, d + 1);
        deepest = Math.max(deepest, Math.max(l, r));
        if(l == deepest && r == deepest) {
            LCA = node;
        }
        return Math.max(l, r);
    }
}
