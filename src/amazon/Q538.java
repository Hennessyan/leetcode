package amazon;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

// Convert BST to Greater Tree
public class Q538 {
    // O(n) O(n)
    int sum = 0;
    public TreeNode convertBST1(TreeNode root) {
        if(root != null) {
            convertBST1(root.right);
            sum += root.val;
            root.val = sum;
            convertBST1(root.left);
        }
        return root;
    }
    // O(n) O(n)
    public TreeNode convertBST2(TreeNode root) {
        if(root == null) {
            return root;
        }
        int sum = 0;
        TreeNode node = root;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while(node != null || !stack.isEmpty()) {
            while(node != null) {
                stack.push(node);
                node = node.right;
            }
            node = stack.pop();
            node.val += sum;
            sum = node.val;
            node = node.left;
        }

        return root;
    }
    // O(n) O(1)
    public TreeNode convertBST(TreeNode root) {
        int sum = 0;
        TreeNode node = root, tmp = null;
        while(node != null) {
            if(node.right == null) {
                sum += node.val;
                node.val = sum;
                node = node.left;
            } else {
                tmp = node.right;
                while(tmp.left != null && tmp.left != node) {
                    tmp = tmp.left;
                }
                if(tmp.left == null) {
                    tmp.left = node;
                    node = node.right;
                } else if(tmp.left == node) {
                    sum += node.val;
                    node.val = sum;
                    tmp.left = null;
                    node = node.left;
                }
            }
        }
        return root;
    }
}
