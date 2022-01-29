package linkedin;

import common.TreeNode;

// Binary Tree Upside Down
public class Q156 {
    // O(n) O(1)
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        TreeNode cur = root, left = null, right = null, tmp_right = null, next = null;
        while(cur != null) {
            next = cur.left;
            tmp_right = cur.right;
            cur.left = right;
            cur.right = left;
            left = cur;
            right = tmp_right;
            cur = next;
        }
        return left;
    }
    // O(n) O(n)
    public TreeNode upsideDownBinaryTree1(TreeNode root) {
        if(root == null || root.left == null) return root;
        TreeNode head = upsideDownBinaryTree1(root.left);
        root.left.left = root.right;
        root.right = null;
        root.left.right = root;
        root.left = null;
        return head;
    }
}
