package microsoft;

import common.TreeNode;

import java.util.*;

// Two Sum IV - Input is a BST
public class Q653 {
    // O(n) O(n)
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> seen = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(seen.contains(k - node.val)) return true;
            seen.add(node.val);
            if(node.left != null) queue.add(node.left);
            if(node.right != null) queue.add(node.right);
        }
        return false;
    }

    public boolean findTarget1(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return help(root, set, k);
    }
    private boolean help(TreeNode root, Set<Integer> set, int k) {
        if(root == null) {
            return false;
        }
        if(set.contains(k - root.val)) {
            return true;
        }
        set.add(root.val);
        return help(root.left, set, k) || help(root.right, set, k);
    }

    public boolean findTarget2(TreeNode root, int k) {
        List< Integer > list = new ArrayList();
        inorder(root, list);
        int l = 0, r = list.size() - 1;
        while (l < r) {
            int sum = list.get(l) + list.get(r);
            if (sum == k)
                return true;
            if (sum < k)
                l++;
            else
                r--;
        }
        return false;
    }
    public void inorder(TreeNode root, List < Integer > list) {
        if (root == null)
            return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }
}
