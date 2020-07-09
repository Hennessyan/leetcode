package challenge.june;

import common.TreeNode;

// Search in a Binary Search Tree
public class Q700 {

    // O(H) O(H)
    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null) {
            return null;
        }
        if(root.val == val) {
            return root;
        } else if(root.val > val) {
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
        }
    }
    // O(H) O(1)
    public TreeNode searchBST1(TreeNode root, int val) {
        while(root != null && root.val != val) {
            if(root.val > val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return root;
    }
}
