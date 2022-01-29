package amazon;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

// Closest Binary Search Tree Value
public class Q270 {
    // O(H) O(1)
    public int closestValue(TreeNode root, double target) {
        double diff = (double) Integer.MAX_VALUE;
        int ans = 0;
        TreeNode node = root;
        while(node != null) {
            if(node.val == target) {
                return node.val;
            } else if(node.val < target) {
                double tmp = target - node.val;
                if(tmp < diff) {
                    diff = tmp;
                    ans = node.val;
                }
                node = node.right;
            } else {
                double tmp = node.val - target;
                if(tmp < diff) {
                    diff = tmp;
                    ans = node.val;
                }
                node = node.left;
            }
        }
        return ans;
    }

    public int closestValue1(TreeNode root, double target) {
        int val, closet = root.val;
        while(root != null) {
            val = root.val;
            closet = Math.abs(val - target) < Math.abs(closet - target) ? val : closet;
            root = val < target ? root.right : root.left;
        }
        return closet;
    }

    public int closestValue2(TreeNode root, double target) {
        double diff = Integer.MAX_VALUE;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while(node != null || !stack.isEmpty()) {
            while(node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            if(Math.abs(node.val - target) < Math.abs(diff)) {
                diff = node.val - target;
            } else break;
            node = node.right;
        }
        return (int) (target + diff);
    }
}
