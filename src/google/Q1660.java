package google;

import common.TreeNode;

import java.util.*;

// Correct a Binary Tree
public class Q1660 {

    public TreeNode correctBinaryTree(TreeNode root) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        TreeNode target = null, node = null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            node = queue.poll();
            if(node.left != null) {
//                if(parent.containsKey(node.left)) {
//                    target = node;
//                    break;
//                } else {
                    parent.put(node.left, node);
                    queue.add(node.left);
//                }
            }
            if(node.right != null) {
                if(parent.containsKey(node.right)) {
                    target = node;
                    break;
                } else {
                    parent.put(node.right, node);
                    queue.add(node.right);
                }
            }
        }
        node = parent.get(target);
        if(node.left == target) node.left = null;
        else node.right = null;
        return root;
    }
}
