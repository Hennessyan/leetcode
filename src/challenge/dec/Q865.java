package challenge.dec;

import common.TreeNode;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

// Smallest Subtree with all the Deepest Nodes
// facebook
public class Q865 {

    // similar as maximum depth of binary tree

    // O(n) O(n)
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return depth(root).getValue();
    }

    private Pair<Integer, TreeNode> depth(TreeNode node) {
        if(node == null) {
            return new Pair<>(0, node);     //should be (-1, node)
        }
        Pair<Integer, TreeNode> lp = depth(node.left);
        Pair<Integer, TreeNode> rp = depth(node.right);
        int ld = lp.getKey(), rd = rp.getKey();
        return new Pair(Math.max(ld, rd) + 1, ld == rd ? node : ld < rd ? rp.getValue() : lp.getValue());
    }


    Map<TreeNode, Integer> depth;
    int max_depth;
    public TreeNode subtreeWithAllDeepest1(TreeNode root) {
        depth = new HashMap<>();
        max_depth = -1;
        depth.put(null, -1);
        dfs(root, null);
        for(Integer val : depth.values()) {
            max_depth = Math.max(max_depth, val);
        }
        return helper(root);
    }
    private void dfs(TreeNode node, TreeNode parent) {
        if(node != null) {
            depth.put(node, depth.get(parent) + 1);
            dfs(node.left, node);
            dfs(node.right, node);
        }
    }
    private TreeNode helper(TreeNode node) {
        if(node == null || depth.get(node) == max_depth) {
            return node;
        }
        TreeNode l = helper(node.left);
        TreeNode r = helper(node.right);

        if(l != null && r != null) {
            return node;
        }
        return l == null ? r : l;
    }

}
