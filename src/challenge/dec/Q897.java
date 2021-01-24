package challenge.dec;

import common.TreeNode;

import java.util.Stack;

// Increasing Order Search Tree
public class Q897 {
    // recursive method
    TreeNode cur;
    public TreeNode increasingBST0(TreeNode root) {
        TreeNode head = new TreeNode(0);
        cur = head;
        inorder(root);
        return head.right;
    }
    private void inorder(TreeNode node) {
        if(node == null) {
            return;
        }

        inorder(node.left);
        node.left = null;
        cur.right = node;
        cur = node;
        inorder(node.right);
    }

    public TreeNode increasingBST1(TreeNode root) {
        TreeNode head = new TreeNode(0);
        TreeNode cur = head;
        Stack<TreeNode> stack = new Stack<>();
        while(root != null || !stack.isEmpty()) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            cur.right = root;
            cur = root;
            root.left = null;
            root = root.right;
        }
        return head.right;
    }

    public TreeNode increasingBST(TreeNode root) {
        TreeNode head = new TreeNode(0);
        TreeNode cur = head, tmp = null;
        while(root != null) {
            if(root.left == null) {
                cur.right = root;
                cur = root;
                root = root.right;
            } else {
                tmp = root.left;
                while(tmp.right != null && tmp.right != root) {
                    tmp = tmp.right;
                }
                if(tmp.right == null) {
                    tmp.right = root;
                    root = root.left;
                } else {
                    // tmp.right = null;    // no need this line !
                    cur.right = root;
                    cur = root;
                    root.left = null;
                    root = root.right;
                }
            }
        }
        return head.right;
    }
}
