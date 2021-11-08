package amazon;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

// Recover Binary Search Tree
public class Q99 {
    // O(n) O(1)
    // can't use break like method2 and method3, it will break the origin tree.
    public void recoverTree(TreeNode root) {
        TreeNode a = null, b = null, pre = null;
        TreeNode node = root;
        while(node != null) {
            if(node.left == null) {
                if(pre != null && pre.val > node.val) {
                    if(a == null) a = pre;
                    b = node;
                }
                pre = node;
                node = node.right;
            } else {
                TreeNode tmp = node.left;
                while(tmp.right != null && tmp.right != node) {
                    tmp = tmp.right;
                }
                if(tmp.right == null) {
                    tmp.right = node;
                    node = node.left;
                } else {
                    tmp.right = null;
                    if(pre != null && pre.val > node.val) {
                        if(a == null) a = pre;
                        b = node;
                    }
                    pre = node;
                    node = node.right;
                }
            }
        }
        swap(a, b);
    }
    // O(n) O(n)
    TreeNode x = null, y = null, pred = null;
    public void recoverTree1(TreeNode root) {
        findTwoSwapped(root);
        swap(x, y);
    }
    public void findTwoSwapped(TreeNode root) {
        if (root == null) return;
        findTwoSwapped(root.left);
        if (pred != null && root.val < pred.val) {
            y = root;
            if (x == null) x = pred;
            else return;    // can happen only twice, hence we can break at second time
        }
        pred = root;
        findTwoSwapped(root.right);
    }

    private void swap(TreeNode n1, TreeNode n2) {
        int tmp = n1.val;
        n1.val = n2.val;
        n2.val = tmp;
    }
    // O(n) O(n)
    public void recoverTree2(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque();
        TreeNode x = null, y = null, pred = null;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.removeLast();
            if (pred != null && root.val < pred.val) {
                y = root;
                if (x == null) x = pred;
                else break;  // can happen only twice, hence we can break at second time
            }
            pred = root;
            root = root.right;
        }

        swap(x, y);
    }
}
