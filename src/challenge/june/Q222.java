package challenge.june;

import common.TreeNode;

// Count Complete Tree Nodes
public class Q222 {

    // method1 : O(n) O(lgn)
    public int countNodes1(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return 1 + countNodes1(root.left) + countNodes1(root.right);
    }

    // method2 : O(lgn * lgn) O(lgn)
    public int countNodes(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int h = getHeight(root);
        if(h == 1) {
            return 1;
        }
        int rh = getHeight(root.right);
        if(h == rh + 1) {
            return (1 << h - 1) - 1 + 1 + countNodes(root.right);
        } else {
            return (1 << h - 2) - 1 + 1 + countNodes(root.left);
        }
    }

    private int getHeight(TreeNode root) {
        int h = 0;
        while(root != null) {
            h++;
            root = root.left;
        }
        return h;
    }
    // method3 : O(lgn^2) O(1)
    public int countNodes2(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int d = height(root);
        if(d == 0) {
            return 1;
        }
        int l = 1, r = (int) Math.pow(2, d) - 1;
        int pivot;
        while(l <= r) {
            pivot = l + (r - l) / 2;
            if(exist(root, d, pivot)) {
                l = pivot + 1;
            } else {
                r = pivot - 1;
            }
        }
        return (int) Math.pow(2, d) - 1 + l;
    }

    private boolean exist(TreeNode node, int d, int index) {
        int l = 0, r = (int) Math.pow(2, d) - 1;
        int pivot;
        for(int i = 0; i < d; i++) {
            pivot = l + (r - l) / 2;
            if(index <= pivot) {
                node = node.left;
                r = pivot;
            } else {
                node = node.right;
                l = pivot + 1;
            }
        }
        return node != null;
    }

    private int height(TreeNode root) { // h - 1
        int h = 0;
        while(root.left != null) {
            h++;
            root = root.left;
        }
        return h;
    }
}
