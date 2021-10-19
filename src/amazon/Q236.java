package amazon;

import common.TreeNode;
import javafx.util.Pair;

import java.util.*;

// Lowest Common Ancestor of a Binary Tree
// Q993
public class Q236 {
    // Recursive Approach : O(n) O(n) - 100%
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) {
            return root;
        }
        TreeNode l = lowestCommonAncestor(root.left, p, q);
        TreeNode r = lowestCommonAncestor(root.right, p, q);
        if(l != null && r != null) {
            return root;
        }
        return l == null ? r : l;
    }
    // method2 - very slow - 5%
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if(!cover(root, p) || !cover(root, q))
            return null;
        return ancestor(root, p, q);
    }
    private boolean cover(TreeNode root, TreeNode p){
        if(root == null)	return false;
        if(root == p)	return true;
        return cover(root.left, p) || cover(root.right, p);
    }
    private TreeNode ancestor(TreeNode root, TreeNode p, TreeNode q){
        if(root == p || root == q)
            return root;

        boolean inLeft = cover(root.left, p);
        boolean inRight = cover(root.right, q);
        if(inLeft == inRight)
            return root;
        TreeNode childIncludeSide = inLeft ? root.left : root.right;
        return ancestor(childIncludeSide, p, q);
    }
    // method3 : O(n) O(n) - 25%
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return root;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        Map<TreeNode, TreeNode> familyTree = new HashMap<>();
        familyTree.put(root, null);
        Set<TreeNode> set = new HashSet<>();
        // build family tree
        while(!familyTree.containsKey(p) || !familyTree.containsKey(q)) {
            TreeNode node = stack.pop();
            if(node.left != null) {
                familyTree.put(node.left, node);
                stack.push(node.left);
            }
            if(node.right != null) {
                familyTree.put(node.right, node);
                stack.push(node.right);
            }
        }
        // all ancestors of p
        while(p != null) {
            set.add(p);
            p = familyTree.get(p);
        }

        while(set.add(q)) {
            q = familyTree.get(q);
        }
        return q;
    }
    // method4 : O(n) O(n) - 25%
    private static int BOTH_PENDING = 2;
    private static int ONE_LEFT_DONE = 1;
    private static int BOTH_DONE = 0;
    public TreeNode lowestCommonAncestor4(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return root;
        }
        boolean one_node_done = false;
        TreeNode LCA = null;
        TreeNode child = null;

        Deque<Pair<TreeNode,Integer>> stack = new ArrayDeque<>();
        stack.push(new Pair(root, BOTH_PENDING));

        while(!stack.isEmpty()) {
            Pair<TreeNode, Integer> pair = stack.peek();
            TreeNode node = pair.getKey();
            int val = pair.getValue();

            if(val != BOTH_DONE) {
                if(val == BOTH_PENDING) {
                    if (node == p || node == q) {
                        if (one_node_done) {
                            return LCA;
                        } else {
                            LCA = stack.peek().getKey();
                            one_node_done = true;
                        }
                    }
                    child = node.left;
                } else {
                    child = node.right;
                }
                stack.pop();
                stack.push(new Pair(node, val - 1));
                if(child != null) {
                    stack.push(new Pair(child, BOTH_PENDING));
                }
            } else {
                if(LCA == stack.pop().getKey()) {
                    LCA = stack.peek().getKey();
                }
            }

        }
        return null;
    }
}
