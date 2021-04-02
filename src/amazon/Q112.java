package amazon;

import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

// Path Sum
public class Q112 {

    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) {
            return false;
        }
        if(root.val == sum && root.left == null && root.right == null) {
            return true;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    public boolean hasPathSum1(TreeNode root, int sum) {
        if(root == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> sums = new LinkedList<>();
        queue.add(root);
        sums.add(root.val);
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int val = sums.poll();
            if(node.left == null && node.right == null && val == sum) {
                return true;
            }
            if(node.left != null) {
                queue.add(node.left);
                sums.add(node.left.val + val);
            }
            if(node.right != null) {
                queue.add(node.right);
                sums.add(node.right.val + val);
            }
        }
        return false;
    }
}
