package google;

import common.TreeNode;

// Binary Tree Longest Consecutive Sequence
public class Q298 {

    // inorder : O(n) O(n)
    int res = 0;
    public int longestConsecutive1(TreeNode root) {
        dfs(root, null, 0);
        return res;
    }
    private void dfs(TreeNode node, TreeNode p, int len) {
        if(node == null) return;
        len = (p != null && p.val + 1 == node.val) ? len + 1 : 1;
        res = Math.max(res, len);
        dfs(node.left, node, len);
        dfs(node.right, node, len);
    }
    // postorder
    public int longestConsecutive(TreeNode root) {
        dfs(root);
        return res;
    }
    private int dfs(TreeNode node) {
        if(node == null) return 0;
        int l = dfs(node.left);
        int r = dfs(node.right);

        if(l != 0 && node.left.val != node.val + 1) {
            l = 0;
        }
        if(r != 0 && node.right.val != node.val + 1) {
            r = 0;
        }
        int len = Math.max(l, r) + 1;
        res = Math.max(res, len);
        return len;
    }
}
