package amazon;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

// Construct Binary Search Tree from Preorder Traversal
public class Q1008 {
    // recursion : O(n) O(n)
    int i;
    public TreeNode bstFromPreorder(int[] preorder) {
        if(preorder == null || preorder.length == 0) {
            return null;
        }
        i = 0;
        return build(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    private TreeNode build(int[] preorder, int min, int max) {
        if(i == preorder.length) return null;
        int val = preorder[i];
        if(val > max || val < min) return null;
        TreeNode node = new TreeNode(val);
        i++;
        node.left = build(preorder, min, val);
        node.right = build(preorder, val, max);
        return node;
    }
    // O(n) O(n)
    public TreeNode bstFromPreorder1(int[] preorder) {
        int n = preorder.length;
        if(n == 0) return null;
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        for(int i = 1; i < n; i++) {
            TreeNode child = new TreeNode(preorder[i]);
            TreeNode node = stack.peek();
            while(!stack.isEmpty() && stack.peek().val < child.val) {
                node = stack.pop();
            }
            if(node.val > child.val) node.left = child;
            else node.right = child;
            stack.push(child);
        }

        return root;
    }
}
