package challenge.july;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Binary Tree Level Order Traversal
public class Q102 {
    // BFS : O(n) O(n)
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                list.add(cur.val);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            res.add(list);
        }
        return res;
    }

    public List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        help(root, res, 0);
        return res;
    }

    private void help(TreeNode root, List<List<Integer>> res, int i) {
        if (root != null) {
            if (i == res.size()) {
                res.add(new ArrayList<>());
            }
            res.get(i).add(root.val);
            help(root.left, res, i + 1);
            help(root.right, res, i + 1);
        }
    }
}
