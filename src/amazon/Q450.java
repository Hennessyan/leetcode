package amazon;

import common.TreeNode;

// Delete Node in a BST
public class Q450 {
    // O(lgn) O(lgn)
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;
        if(root.val == key) {
            if(root.right != null) {
                TreeNode tmp = root.right;
                while(tmp.left != null) {
                    tmp = tmp.left;
                }
                tmp.left = root.left;
                return root.right;
            } else return root.left;
        } else if(root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {
            root.left = deleteNode(root.left, key);
        }
        return root;
    }
}
