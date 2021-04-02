package amazon;

import common.ListNode;
import common.TreeNode;

// Linked List in Binary Tree
public class Q1367 {
    // O(nk) O(nk) : n - num of treenode, k - num of listnode
    public boolean isSubPath(ListNode head, TreeNode root) {
        if(root == null) {
            return false;
        }
        return helper(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }
    private boolean helper(ListNode head, TreeNode node) {
        if(head == null) {
            return true;
        }
        if(node == null) {
            return false;
        }
        if(head.val == node.val) {
            return helper(head.next, node.left) || helper(head.next, node.right);
        }
        return false;
    }
}
