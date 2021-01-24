package challenge.september;

import common.TreeNode;

import java.util.*;

// All Elements in Two Binary Search Trees
// Facebook
public class Q1305 {
    // O((n+m)lg(n+m)) O(n+m)
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> res = new ArrayList<>();
        inorder(root1, res);
        inorder(root2, res);
        Collections.sort(res);
        return res;
    }

    private void inorder(TreeNode root, List<Integer> res) {
        if (root != null) {
            inorder(root.left, res);
            res.add(root.val);
            inorder(root.right, res);
        }
    }

    // O(n+m) O(n+m)
    public List<Integer> getAllElements1(TreeNode root1, TreeNode root2) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> s1 = new LinkedList<>();
        Deque<TreeNode> s2 = new LinkedList<>();
        while (!s1.isEmpty() || !s2.isEmpty() || root1 != null || root2 != null) {
            while (root1 != null) {
                s1.push(root1);
                root1 = root1.left;
            }
            while (root2 != null) {
                s2.push(root2);
                root2 = root2.left;
            }
            // This if condition is important!
            if (s2.isEmpty() || !s1.isEmpty() && s1.getFirst().val < s2.getFirst().val) {   //same as s1.peek()
                root1 = s1.pollFirst();
                res.add(root1.val);
                root1 = root1.right;
            } else {
                root2 = s2.pollFirst();
                res.add(root2.val);
                root2 = root2.right;
            }
        }
        return res;
    }
}
