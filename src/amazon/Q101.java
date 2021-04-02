package amazon;

import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

// Symmetric Tree
public class Q101 {
    //  O(n) O(n)
    public boolean isSymmetric(TreeNode root) {
        return help(root, root);
    }
    private boolean help(TreeNode t1, TreeNode t2) {
        if(t1 == null && t2 == null) {
            return true;
        }
        if(t1 == null || t2 == null) {
            return false;
        }
        return t1.val == t2.val && help(t1.left, t2.right) && help(t1.right, t2.left);
    }

    public boolean isSymmetric1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode t1 = root, t2 = root;
        queue.add(t1);
        queue.add(t2);
        while(!queue.isEmpty()) {
            t1 = queue.poll();
            t2 = queue.poll();
            if(t1 == null && t2 == null) {
                continue;
            }
            if(t1 == null || t2 == null) {
                return false;
            }
            if(t1.val != t2.val) {
                return false;
            }
            queue.add(t1.left);
            queue.add(t2.right);
            queue.add(t1.right);
            queue.add(t2.left);
        }
        return true;
    }
}
