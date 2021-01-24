package challenge.jan21;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

// Find a Corresponding Node of a Binary Tree in a Clone of That Tree
public class Q1379 {
    // O(n) O(h) - h in worst case is n
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if(original == null) {
            return null;
        }
        TreeNode left = getTargetCopy(original.left, cloned.left, target);
        if(left != null) {
            return left;
        }
        if(original == target) {
            return cloned;
        }
        return getTargetCopy(original.right, cloned.right, target);
    }

    public final TreeNode getTargetCopy1(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        Deque<TreeNode> queue_o = new ArrayDeque();
        queue_o.offer(original);

        Deque<TreeNode> queue_c = new ArrayDeque();
        queue_c.offer(cloned);

        while (!queue_o.isEmpty()) {
            TreeNode node_o = queue_o.poll();
            TreeNode node_c = queue_c.poll();

            if (node_o == target) {
                return node_c;
            }

            if (node_o.left != null) {
                queue_o.offer(node_o.left);
                queue_c.offer(node_c.left);
            }
            if (node_o.right != null) {
                queue_o.offer(node_o.right);
                queue_c.offer(node_c.right);
            }
        }
        return null;
    }
}
