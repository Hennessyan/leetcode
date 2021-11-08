package amazon;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

// Sum of Left Leaves
public class Q404 {
    // BFS
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) return 0;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        int total = 0;
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if(node.left != null && node.left.left == null && node.left.right == null) {
                total += node.left.val;
            }
            if(node.left != null) stack.push(node.left);
            if(node.right != null) stack.push(node.right);
        }
        return total;
    }
    // DFS
    public int sumOfLeftLeaves1(TreeNode root) {
        return help(root, false);
    }
    private int help(TreeNode node, boolean isLeft) {
        if(node == null) return 0;
        int total = 0;
        if(isLeft && node.left == null && node.right == null) {
            total += node.val;
        }
        return total + help(node.left, true) + help(node.right, false);
    }

    public int sumOfLeftLeaves2(TreeNode root) {
        int total = 0;
        TreeNode node = root, tmp = null;
        while(node != null) {
            if(node.left == null) {
                node = node.right;
            } else {
                tmp = node.left;
                if(tmp.left == null && tmp.right == null) {
                    total += tmp.val;
                }
                while(tmp.right != null && tmp.right != node) {
                    tmp = tmp.right;
                }
                if(tmp.right == null) {
                    tmp.right = node;
                    node = node.left;
                } else {
                    tmp.right = null;
                    node = node.right;
                }
            }
        }
        return total;
    }
}
