package challenge.may;

import common.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

// Cousins in Binary Tree
public class Q993 {

//    Map<Integer, Integer> depth;
//    Map<Integer, TreeNode> parent;
//    public boolean isCousins(TreeNode root, int x, int y) {
//        depth = new HashMap<>();
//        parent = new HashMap<>();
//        dfs(root, null);
//        return (depth.get(x) == depth.get(y) && parent.get(x) != parent.get(y));
//    }
//    private void dfs(TreeNode node, TreeNode p) {
//        if(node != null) {
//            parent.put(node.val, p);
//            depth.put(node.val, p == null ? 0 : depth.get(p.val) + 1);
//            dfs(node.left, node);
//            dfs(node.right, node);
//        }
//    }
    //https://leetcode.com/problems/cousins-in-binary-tree/solution/
    //dfs
    private boolean isCousin = false;
    private int recordDepth = -1;
    public boolean isCousins(TreeNode root, int x, int y) {
        dfs(root, 0, x, y);
        return this.isCousin;
    }
    private boolean dfs(TreeNode root, int depth, int x, int y) {
        if(root == null) {
            return false;
        }
        if(recordDepth != -1 && recordDepth < depth) {
            return false;
        }
        if(root.val == x || root.val == y) {
            if(recordDepth == -1) {
                recordDepth = depth;
            }
            return recordDepth == depth;
        }
        boolean l = dfs(root.left, depth + 1, x, y);
        boolean r = dfs(root.right, depth + 1, x, y);
        if(l && r && recordDepth != depth + 1) {
            isCousin = true;
        }
        return l || r;
    }

    // bfs
    public boolean isCousins1(TreeNode root, int x, int y) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            boolean sibling = false, cousin = false;
            int val = queue.size();
            for(int i = 0; i < val; i++) {
                TreeNode node = queue.poll();
                if(node == null) {
                    sibling = false;
                }else {
                    if(node.val == x || node.val == y) {
                        if(!cousin) {
                            sibling = cousin = true;
                        }else {
                            return !sibling;
                        }
                    }
                    if(node.left != null) queue.add(node.left);
                    if(node.right != null) queue.add(node.right);
                    queue.add(null);
                }
            }
            if(cousin) return false;
        }
        return false;
    }
}
