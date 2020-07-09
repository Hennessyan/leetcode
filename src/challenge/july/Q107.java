package challenge.july;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Binary Tree Level Order Traversal II
public class Q107 {

    // DFS : O(n) O(n)
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        dfs(root, 0, res);
        return res;
    }
    private void dfs(TreeNode root, int level, List<List<Integer>> res) {
        if(root != null) {
            if(res.size() == level) {
                res.add(0, new ArrayList<>());
            }
            res.get(res.size() - level - 1).add(root.val);
            dfs(root.left, level + 1, res);
            dfs(root.right, level + 1, res);
        }
    }
    // BFS : O(n) O(n)
    public List<List<Integer>> levelOrderBottom1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        List<Integer> tmp;
        TreeNode node;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            tmp = new ArrayList<>();
            for(int i = 0; i < size; i++) {
                node = queue.poll();
                tmp.add(node.val);
                if(node.left != null) {
                    queue.add(node.left);
                }
                if(node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(0, tmp);
        }
        return res;
    }

}
