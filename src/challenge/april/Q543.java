package challenge.april;

import common.TreeNode;

// Diameter of Binary Tree
public class Q543 {
    /**
     *      3, which is the length of the path [4,2,1,3] or [5,2,1,3].
     *              1
     *             / \
     *            2   3
     *           / \
     *          4   5
     */
    int max;
    public int diameterOfBinaryTree(TreeNode root) {
        max = 0;
        help(root);
        return max;
    }
    private int help(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int l = help(root.left);
        int r = help(root.right);
        max = Math.max(max, l + r);
        return Math.max(l, r) + 1;
    }
}
