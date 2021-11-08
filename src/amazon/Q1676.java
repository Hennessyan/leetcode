package amazon;

import common.TreeNode;

import java.util.HashSet;
import java.util.Set;

// Lowest Common Ancestor of a Binary Tree IV
public class Q1676 {

    Set<TreeNode> set;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        set = new HashSet<>();
        for(TreeNode node : nodes) {
            set.add(node);
        }
        return help(root);
    }
    private TreeNode help(TreeNode node) {
        if(node == null || set.contains(node)) {
            return node;
        }
        TreeNode l = help(node.left);
        TreeNode r = help(node.right);
        if(l != null && r != null) return node;
        return l == null ? r : l;
    }

    TreeNode lca = null;
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode[] nodes) {
        Set<Integer> targetNodes = new HashSet<>();
        for(TreeNode node : nodes) {
            targetNodes.add(node.val);
        }
        helper(root, targetNodes);
        return lca;
    }

    int helper(TreeNode root, Set<Integer> nodes) {
        if(root == null) return 0;
        int leftCount = helper(root.left, nodes);
        int rightCount = helper(root.right, nodes);
        int foundCount = leftCount + rightCount;
        if(nodes.contains(root.val)) {
            foundCount++;
        }
        if(foundCount == nodes.size() && lca == null) {
            lca = root;
        }

        return foundCount;
    }
}
