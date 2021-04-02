package amazon;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Binary Tree Right Side View
public class Q199 {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if(i == 0) {
                    res.add(node.val);
                }
                if(node.right != null) {
                    queue.add(node.right);
                }
                if(node.left != null) {
                    queue.add(node.left);
                }
            }
        }
        return res;
    }

    public List<Integer> rightSideView1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        dfs(root, res, 0);
        return res;
    }
    private void dfs(TreeNode node, List<Integer> res, int depth) {
        if(node != null) {
            if(res.size() == depth) {
                res.add(node.val);
            }
            dfs(node.right, res, depth + 1);
            dfs(node.left, res, depth + 1);
        }
    }


}
