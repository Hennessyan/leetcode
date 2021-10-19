package amazon;

import common.TreeNode;

// Subtree of Another Tree
public class Q572 {
    // O(mn) O(n) : m - size of s, n - size of t.
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s == null) {
            return t == null;
        }
        return isSame(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }
    private boolean isSame(TreeNode s, TreeNode t) {
        if(s == null && t == null)
            return true;
        if(s == null || t == null)
            return false;
        /**
         *  if(s == null) return t == null;
         *  if(t == null) return false;
         */
        return s.val == t.val && isSame(s.left, t.left) && isSame(s.right, t.right);
    }
}
