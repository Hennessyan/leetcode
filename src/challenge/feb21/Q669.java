package challenge.feb21;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

// Trim a Binary Search Tree
public class Q669 {
    // preorder - O(n) O(n)
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if(root == null || low > high) {
            return null;
        }
        if(root.val >= low && root.val <= high) {
            root.left = trimBST(root.left, low, root.val - 1);
            root.right = trimBST(root.right, root.val + 1, high);
            return root;
        } else if(root.val > high) {
            return trimBST(root.left, low, high);
        } else {
            return trimBST(root.right, low, high);
        }
    }
    // postorder - O(n) O(n)
    public TreeNode trimBST1(TreeNode root, int low, int high) {
        if (root == null) return root;
        if (root.val > high) return trimBST1(root.left, low, high);
        if (root.val < low) return trimBST1(root.right, low, high);

        root.left = trimBST1(root.left, low, high);
        root.right = trimBST1(root.right, low, high);
        return root;
    }
    // very slow
    public TreeNode trimBST2(TreeNode root, int low, int high) {
        if(root == null) {
            return null;
        }
        while(root != null && (root.val < low || root.val > high)) {
            if(root.val < low) {
                root = root.right;
            }
            if(root.val > high) {
                root = root.left;
            }
        }
        if(root != null) {
            Deque<TreeNode> stack = new ArrayDeque<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode node = stack.poll();
                boolean flag = false;

                if (node.left != null && node.left.val < low) {
                    node.left = node.left.right;
                    flag = true;
                }
                if (node.right != null && node.right.val > high) {
                    node.right = node.right.left;
                    flag = true;
                }
                // calculate child node later
                if (flag) {
                    stack.push(node);
                } else {
                    if (node.left != null) stack.push(node.left);
                    if (node.right != null) stack.push(node.right);
                }
            }
        }
        return root;
    }
}
