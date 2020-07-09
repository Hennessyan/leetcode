package challenge.june;

import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

// Invert Binary Tree
public class Q226 {

    // O(n) O(n)
    public TreeNode invertTree1(TreeNode root) {
        if(root == null) {
            return null;
        }
        TreeNode left = invertTree1(root.right);
        root.right = invertTree1(root.left);
        root.left = left;
        return root;
    }

    public TreeNode invertTree(TreeNode root) {
        if(root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
            if(node.left != null) {
                queue.add(node.left);
            }
            if(node.right != null) {
                queue.add(node.right);
            }
        }
        return root;
    }
}
