package amazon;

import common.TreeNode;

// Binary Tree Longest Consecutive Sequence
public class Q298 {

    //O(n) O(n)
    //preorder
    int res = 0;
    public int longestConsecutive(TreeNode root) {
        if(root == null) {
            return 0;
        }
        dfs(root, null, 0);
        return res;
    }
    private void dfs(TreeNode root, TreeNode p, int len) {
        if(root == null) {
            return;
        }
        len = (p != null && p.val + 1 == root.val) ? len + 1 : 1;
        res = Math.max(res, len);
        dfs(root.left, root, len);
        dfs(root.right, root, len);
    }
    //postorder
    public int longestConsecutive1(TreeNode root) {
        if(root == null) {
            return 0;
        }
        dfs(root);
        return res;
    }
    private int dfs(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int l = dfs(root.left);
        int r = dfs(root.right);
        if (root.left != null && root.val + 1 != root.left.val) {
            l = 0;
        }
        if (root.right != null && root.val + 1 != root.right.val) {
            r = 0;
        }
        int len = Math.max(l, r) + 1;
        res = Math.max(len, res);
        return len;
    }
}
