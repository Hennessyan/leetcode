package challenge.dec;

import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// Minimum Depth of Binary Tree
public class Q111 {

    public int minDepth0(TreeNode root) {
        if(root == null) return 0;
        int l = minDepth0(root.left);
        int r = minDepth0(root.right);
        return (l == 0 || r == 0) ? l + r + 1 : Math.min(l, r) + 1;
    }

    public int minDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int depth = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if(node.left == null && node.right == null) {
                    return depth;
                }
                if(node.left != null) {
                    queue.add(node.left);
                }
                if(node.right != null) {
                    queue.add(node.right);
                }
            }
            depth++;
        }
        return -1;
    }
    /*method3*/
    public int minDepth2(TreeNode root) {
        if(root == null) return 0;
        return helper(root);
    }
    public int helper(TreeNode root){
        if(root == null)
            return Integer.MAX_VALUE;
        if(root.left == null && root.right == null)
            return 1;
        int depthLeft = helper(root.left);
        int depthRight = helper(root.right);

        return Math.min(depthLeft, depthRight) + 1;
    }

    public int minDepth4(TreeNode root) {
        if(root == null) {
            return 0;
        }
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(root, 1));
        int res = Integer.MAX_VALUE;
        while(!stack.isEmpty()) {
            Pair p = stack.pop();
            TreeNode node = p.node;
            int d = p.depth;
            if(node.left == null && node.right == null) {
                res = Math.min(res, d);
            }
            if(node.left != null) {
                stack.push(new Pair(node.left, d + 1));
            }
            if(node.right != null) {
                stack.push(new Pair(node.right, d + 1));
            }
        }
        return res;
    }
}
class Pair {
    TreeNode node;
    int depth;
    public Pair(TreeNode node, int depth) {
        this.node = node;
        this.depth = depth;
    }
}