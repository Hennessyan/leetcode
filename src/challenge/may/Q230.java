package challenge.may;

import common.TreeNode;

import java.util.Stack;

// Kth Smallest Element in a BST
public class Q230 {

    // Stack - O(n) O(n)
    public int kthSmallest0(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (--k == 0) {
                break;
            }
            root = root.right;
        }
        return root.val;
    }

    // O(n) O(1)
    public int kthSmallest1(TreeNode root, int k) {
        TreeNode node = root;
        while (node != null) {
            if (node.left == null) {
                if (--k == 0) {
                    break;
                }
                node = node.right;
            } else {
                TreeNode tmp = node.left;
                while (tmp.right != null && tmp.right != node) {
                    tmp = tmp.right;
                }
                if (tmp.right == null) {
                    tmp.right = node;
                    node = node.left;
                } else {
                    if (--k == 0) {
                        break;
                    }
                    tmp.right = null;
                    node = node.right;
                }
            }
        }
        return node.val;
    }

    public int kthSmallest(TreeNode root, int k) {
        int l = getCount(root.left);
        if (l == k - 1) return root.val;
        if (l >= k) {
            return kthSmallest(root.left, k);
        } else {
            return kthSmallest(root.right, k - l - 1);
        }
    }

    private int getCount(TreeNode node) {
        if(node == null) return 0;
        return 1 + getCount(node.left) + getCount(node.right);
    }
}
