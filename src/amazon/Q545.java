package amazon;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

// Boundary of Binary Tree
public class Q545 {
    // O(n) O(n)
    List<Integer> left;
    List<Integer> right;
    List<Integer> leaves;
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        left = new ArrayList<>();
        right = new ArrayList<>();
        leaves = new ArrayList<>();
        if(!isLeave(root)) {
            res.add(root.val);
        }
        left(root.left);
        dfs(root);
        right(root.right);
        res.addAll(left);
        res.addAll(leaves);
        res.addAll(right);
        return res;
    }
    private boolean isLeave(TreeNode node) {
        return node.left == null && node.right == null;
    }
    // whole tree
    private void dfs(TreeNode node) {
        if(node == null) {
            return;
        }
        if(isLeave(node)) {
            leaves.add(node.val);
        }
        dfs(node.left);
        dfs(node.right);
    }
    // just left side
    private void left(TreeNode node) {
        while(node != null) {
            if(isLeave(node)) {
                return;
            }
            left.add(node.val);
            if(node.left != null) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
    }
    // just right side
    private void right(TreeNode node) {
        while(node != null) {
            if(isLeave(node)) {
                return;
            }
            right.add(0, node.val);
            if(node.right != null) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
    }
    // O(n) O(n)
    public List<Integer> boundaryOfBinaryTree1(TreeNode root) {
        left = new ArrayList<>();
        right = new ArrayList<>();
        leaves = new ArrayList<>();
        preorder(root, 0);
        left.addAll(leaves);
        left.addAll(right);
        return left;
    }
    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }
    private boolean isRoot(int flag) {
        return flag == 0;
    }
    private boolean isLeft(int flag) {
        return flag == 1;
    }
    private boolean isRight(int flag) {
        return flag == 2;
    }
    private int leftFlag(TreeNode node, int flag) {
        if(isLeft(flag) || isRoot(flag)) {
            return 1;
        } else if(isRight(flag) && node.right == null) {
            return 2;
        } else {
            return 3;
        }
    }
    private int rightFlag(TreeNode node, int flag) {
        if(isRight(flag) || isRoot(flag)) {
            return 2;
        } else if(isLeft(flag) && node.left == null) {
            return 1;
        } else {
            return 3;
        }
    }
    private void preorder(TreeNode node, int flag) {
        if(node == null) {
            return;
        }
        if(isLeft(flag) || isRoot(flag)) {
            left.add(node.val);
        } else if(isRight(flag)) {
            right.add(0, node.val);
        } else if(isLeaf(node)) {
            leaves.add(node.val);
        }
        preorder(node.left, leftFlag(node, flag));
        preorder(node.right, rightFlag(node, flag));
    }
}
