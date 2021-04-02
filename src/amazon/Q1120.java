package amazon;

import common.TreeNode;

// Maximum Average Subtree
public class Q1120 {
    // postorder : O(n) O(n)
    double max;
    public double maximumAverageSubtree(TreeNode root) {
        max = 0;
        dfs(root);
        return max;
    }
    private int[] dfs(TreeNode node) {
        if(node == null) {
            return new int[]{0, 0};
        }
        int[] l = dfs(node.left);
        int[] r = dfs(node.right);
        int total = node.val + l[0] + r[0];
        int count = l[1] + r[1] + 1;
        max = Math.max(max, total * 1.0 / count);
        return new int[]{total, count};
    }
}
