package amazon;

import common.TreeNode;

// Lowest Common Ancestor of a Binary Search Tree
public class Q235 {
    // Recursive approach : O(n) O(n)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return root;
        }
        if(root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if(root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }
    // Iterative approach : O(n) O(1)
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode node = root;
        while(node != null) {
            if (node.val > p.val && node.val > q.val) {
                node = node.left;
            } else if(node.val < p.val && node.val < q.val) {
                node = node.right;
            } else {
                break;
            }
        }
        return node;
    }
}
