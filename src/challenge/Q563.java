package challenge;

import common.TreeNode;

// Binary Tree Tilt
public class Q563 {

    int sum;
    public int findTilt(TreeNode root) {
        sum = 0;
        help(root);
        return sum;
    }
    private int help(TreeNode node) {
        if(node == null) return 0;
        int l = help(node.left);
        int r = help(node.right);
        sum += Math.abs(l - r);
        return l + r + node.val;
    }
}
