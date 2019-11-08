package lyft;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;

//Largest BST Subtree
public class Q333 {

    //O(n) O(n)
    public int largestBSTSubtree(TreeNode root) {
        int[] ans = help(root);
        return ans[2];
    }

    private int[] help(TreeNode node) {
        if (node == null) {
            return new int[]{MAX_VALUE, Integer.MIN_VALUE, 0};
        }
        int[] l = help(node.left);
        int[] r = help(node.right);
        if (node.val > l[1] && node.val < r[0]) {
            return new int[]{Math.min(node.val, l[0]), Math.max(node.val, r[1]), l[2] + r[2] + 1};
        } else {
            return new int[]{MIN_VALUE, MAX_VALUE, Math.max(l[2], r[2])};
        }
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
