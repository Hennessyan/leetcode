package google;

import common.TreeNode;

import java.util.Stack;

// Range Sum of BST
public class Q938 {
    int ans;
    public int rangeSumBST0(TreeNode root, int L, int R) {
        ans = 0;
        dfs(root, L, R);
        return ans;
    }

    public void dfs(TreeNode node, int L, int R) {
        if (node != null) {
            if (L <= node.val && node.val <= R)
                ans += node.val;
            if (L < node.val)
                dfs(node.left, L, R);
            if (node.val < R)
                dfs(node.right, L, R);
        }
    }
    // O(n) O(n)
    public int rangeSumBST(TreeNode root, int low, int high) {
        if(root == null) {
            return 0;
        }
        if(root.val < low) {
            return rangeSumBST(root.right, low, high);
        }
        if(root.val > high) {
            return rangeSumBST(root.left, low, high);
        }
        return root.val + rangeSumBST(root.left, low, root.val - 1) + rangeSumBST(root.right, root.val + 1, high);
    }

    public int rangeSumBST1(TreeNode root, int low, int high) {
        if(root == null) {
            return 0;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int ans = 0;
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if(low <= node.val && node.val <= high) {
                ans += node.val;
            }
            if(low < node.val && node.left != null) {
                stack.push(node.left);
            }
            if(high > node.val && node.right != null) {
                stack.push(node.right);
            }
        }
        return ans;
    }
}
