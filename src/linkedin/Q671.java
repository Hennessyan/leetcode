package linkedin;

import common.TreeNode;

// Second Minimum Node In a Binary Tree
public class Q671 {
    // O(n) O(n)
    long ans = Long.MAX_VALUE;
    int min;
    public int findSecondMinimumValue(TreeNode root) {
        min = root.val;
        dfs(root);
        return ans == Long.MAX_VALUE ? -1 : (int) ans;
    }
    private void dfs(TreeNode node) {
        if(node != null) {
            if(node.val > min && node.val < ans) {
                ans = node.val;
            } else if(node.val == min) {    // this condition is important !
                dfs(node.left);
                dfs(node.right);
            }
        }
    }
}
