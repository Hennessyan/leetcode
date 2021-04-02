package google;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// Binary Tree Paths
public class Q257 {
    // O(n) O(n)
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        dfs(root, "", res);
        return res;
    }
    private void dfs(TreeNode node, String prefix, List<String> res) {
        if(node == null) {
            return;
        }
        prefix += Integer.toString(node.val);
        if(node.left == null && node.right == null) {
            res.add(prefix);
        } else {
            prefix += "->";
            dfs(node.left, prefix, res);
            dfs(node.right, prefix, res);
        }
    }

    public List<String> binaryTreePaths1(TreeNode root) {
        LinkedList<String> paths = new LinkedList();
        if (root == null)
            return paths;

        LinkedList<TreeNode> node_stack = new LinkedList();
        LinkedList<String> path_stack = new LinkedList();
        node_stack.add(root);
        path_stack.add(Integer.toString(root.val));
        TreeNode node;
        String path;
        while ( !node_stack.isEmpty() ) {
            node = node_stack.pollLast();
            path = path_stack.pollLast();
            if ((node.left == null) && (node.right == null))
                paths.add(path);
            if (node.left != null) {
                node_stack.add(node.left);
                path_stack.add(path + "->" + Integer.toString(node.left.val));
            }
            if (node.right != null) {
                node_stack.add(node.right);
                path_stack.add(path + "->" + Integer.toString(node.right.val));
            }
        }
        return paths;
    }
}
