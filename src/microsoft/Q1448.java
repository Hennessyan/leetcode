package microsoft;

import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

// Count Good Nodes in Binary Tree
public class Q1448 {
    // O(n) O(n)
    int total = 0;
    public int goodNodes(TreeNode root) {
        if(root == null) {
            return 0;
        }
        dfs(root, root.val);
        return total;
    }
    private void dfs(TreeNode node, int max) {
        if(node != null) {
            if(node.val >= max) {
                total++;
                max = node.val;
            }
            dfs(node.left, max);
            dfs(node.right, max);
        }
    }

    class Pair{
        TreeNode root;
        int val;
        Pair(TreeNode root, int val) {
            this.root = root;
            this.val = val;
        }
    }
    public int goodNodes1(TreeNode root) {
        if(root == null) return 0;
        int count = 0 ;
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root,root.val));
        while(!q.isEmpty()) {
            Pair p = q.poll();
            TreeNode t = p.root;
            if(t.val >= p.val) {
                count++;
                p.val = t.val;
            }
            if(t.left != null) {
                q.offer(new Pair(t.left, p.val));
            }
            if(t.right != null) {
                q.offer(new Pair(t.right, p.val));
            }
        }
        return count;
    }
}
