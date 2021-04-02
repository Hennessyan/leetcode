package amazon;

import common.TreeNode;

// Binary Tree Pruning
public class Q814 {
    // O(N) O(N)
    public TreeNode pruneTree(TreeNode root) {
        if(root == null) {
            return root;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if(root.left == null && root.right == null && root.val == 0) {
            return null;
        } else {
            return root;
        }
    }
}
