package microsoft;

import common.ListNode;
import common.TreeNode;

// Convert Sorted List to Binary Search Tree
public class Q109 {
    // O(nlgn) O(lgn)
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) {
            return null;
        }
        ListNode mid = findMid(head);
        TreeNode root = new TreeNode(mid.val);
        if(head == mid) {   // avoid endless loop for L18
            return root;
        }
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(mid.next);
        return root;
    }
    private ListNode findMid(ListNode head) {
        ListNode pre = null, sl = head, fl = head;
        while(fl != null && fl.next != null) {
            pre = sl;
            sl = sl.next;
            fl = fl.next.next;
        }
        if(pre != null) {
            pre.next = null;
        }
        return sl;
    }
    // inorder : O(n) O(lgn)
    ListNode head;
    public TreeNode sortedListToBST1(ListNode head) {
        this.head = head;
        int size = getSize(head);
        return toBST(0, size - 1);
    }
    private TreeNode toBST(int l, int r) {
        if(l > r) return null;
        int m = l + (r - l) / 2;
        TreeNode left = toBST(l, m - 1);
        TreeNode root = new TreeNode(this.head.val);
        this.head = this.head.next;
        root.left = left;
        root.right = toBST(m + 1, r);
        return root;
    }
    private int getSize(ListNode head) {
        ListNode cur = head;
        int count = 0;
        while(cur != null) {
            count++;
            cur = cur.next;
        }
        return count;
    }
}
