package amazon;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

// Inorder Successor in BST
public class Q285 {
    // O(n) O(1)
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;

        while(root != null) {
            if(root.val <= p.val) {
                root = root.right;
            } else {
                successor = root;
                root = root.left;
            }
        }
        return successor;
    }

    TreeNode successor = null;
    public TreeNode inorderSuccessor1(TreeNode root, TreeNode p) {

        if(p.right != null) {
            TreeNode leftMost = p.right;
            while(leftMost.left != null) {
                leftMost = leftMost.left;
            }
            successor = leftMost;
        } else {
            inorder(root, p);
        }
        return successor;
    }
    private void inorder(TreeNode node, TreeNode p) {
        if(node == null) return;
        inorder(node.left, p);
        if(successor == null && node.val > p.val) {
            successor = node;
            return;
        }
        inorder(node.right, p);
    }

    // O(n) O(n)
    public TreeNode inorderSuccessor3(TreeNode root, TreeNode p) {
        if(root == null || p == null) {
            return root;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()) {
            while(cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if(cur.val > p.val) {
                return cur;
            }
            cur = cur.right;
        }
        return null;
    }
}
