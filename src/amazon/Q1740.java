package amazon;

import common.TreeNode;

// Find Distance in a Binary Tree
public class Q1740 {

    TreeNode ancestor;
    public int findDistance(TreeNode root, int p, int q) {
        // if(p == q) {
        //     return 0;
        // }
        traversal(root, p, q);
        return dist(ancestor, p, 0) + dist(ancestor, q, 0);
    }
    private boolean traversal(TreeNode root, int p, int q) {
        if(root == null) {
            return false;
        }
        int l = traversal(root.left, p, q) ? 1 : 0;
        int r = traversal(root.right, p, q) ? 1 : 0;
        int m = (root.val == p) ? 1 : 0;
        if(root.val == q) {
            m++;
        }
        if(l + r + m >= 2) {
            ancestor = root;
        }
        return l + r + m > 0;
    }
    private int dist(TreeNode ancestor, int p, int depth) {
        if(ancestor == null) {
            return 0;
        }
        if(ancestor.val == p) {
            return depth;
        }
        int l = dist(ancestor.left, p, depth + 1);
        return l == 0 ? dist(ancestor.right, p, depth + 1) : l;
    }
}
