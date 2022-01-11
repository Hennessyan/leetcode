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
    // iteration
    public TreeNode deleteNode1(TreeNode root, int key) {
        TreeNode node = root, pre = null;
        while(node != null && node.val != key) {
            pre = node;
            if(node.val < key) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        if(pre == null) {
            return delete(node);
        }
        if(pre.left == node) {
            pre.left = delete(node);
        } else {
            pre.right = delete(node);
        }
        return root;
    }
    private TreeNode delete(TreeNode node) {
        if(node == null) return null;
        if(node.left == null) return node.right;
        if(node.right == null) return node.left;
        TreeNode tmp = node.right;
        while(tmp.left != null) {
            tmp = tmp.left;
        }
        tmp.left = node.left;
        return node.right;
    }
}
