package amazon;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

// Balance a Binary Search Tree
// similar : Q108
public class Q1382 {
    // O(n) O(n)
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return build(list, 0, list.size() - 1);
    }
    private void inorder(TreeNode node, List<Integer> list) {
        if(node != null) {
            inorder(node.left, list);
            list.add(node.val);
            inorder(node.right, list);
        }
    }
    private TreeNode build(List<Integer> list, int l, int r) {
        if(l > r) return null;
        int m = l + (r - l) / 2;
        TreeNode node = new TreeNode(list.get(m));
        node.left = build(list, l, m - 1);
        node.right = build(list, m + 1, r);
        return node;
    }
    // method2
    TreeNode head=null, curr=null;
    int count = 0; // Counter for no of nodes in the tree.
    public TreeNode balanceBST1(TreeNode root) {
        if(root == null) return root;
        convertTreeToList(root);
        return convertListToBalancedBST(count);
    }

    private void convertTreeToList(TreeNode node) {
        if(node == null) return;
        convertTreeToList(node.left);
        insertToList(node); // If node.right requires change in this method, then we need to take a temp.
        convertTreeToList(node.right);
    }

    private void insertToList(TreeNode node) {
        count++;
        if(head == null) {
            head = node;
            curr = head;
        }
        else {
            curr.right = node;
            curr = curr.right;
        }
    }

    private TreeNode convertListToBalancedBST(int count) {
        if(count == 0) return null;
        int leftCount = count/2;
        int rootCount = 1;
        int rightCount = count - leftCount - rootCount;
        TreeNode left = convertListToBalancedBST(leftCount);
        TreeNode root = getFromHead();
        TreeNode right = convertListToBalancedBST(rightCount);
        root.left = left;
        root.right = right;
        return root;
    }

    private TreeNode getFromHead() {
        if(head == null) return null;
        TreeNode retNode = head;
        head = head.right;
        return retNode;
    }
}
