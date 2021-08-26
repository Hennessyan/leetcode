package google;

import common.TreeNode;
import javafx.util.Pair;

import java.util.*;

// Lowest Common Ancestor of a Binary Tree
public class Q236 {
    // O(n^2) O(n)
    public TreeNode lowestCommonAncestor0(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) {
            return root;
        }
        boolean pInLeft = containNode(root.left, p);
        boolean qInRight = containNode(root.right, q);

        if(pInLeft == qInRight) {
            return root;
        }
        root = pInLeft ? root.left : root.right;
        return lowestCommonAncestor0(root, p, q);
    }
    private boolean containNode(TreeNode node, TreeNode t) {
        if(node == null) {
            return false;
        }
        if(node == t) {
            return true;
        }
        return containNode(node.left, t) || containNode(node.right, t);
    }
    // O(n) O(n)
    private TreeNode ancestor;
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        traversal(root, p, q);
        return ancestor;
    }
    private boolean traversal(TreeNode node, TreeNode p, TreeNode q) {
        if(node == null) {
            return false;
        }
        int l = traversal(node.left, p, q) ? 1 : 0;
        int r = traversal(node.right, p , q) ? 1 : 0;
        int cur = (node == p || node == q) ? 1 : 0; // p != q, otherwise we may check whether p == q at beginning, or check node == p and node == q separately.

        if(l + r + cur == 2) {
            ancestor = node;
        }
        return l + r + cur > 0;
    }
    // O(n) O(n)
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return root;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        Map<TreeNode, TreeNode> parents = new HashMap<>();
        parents.put(root, null);
        while(!parents.containsKey(p) || !parents.containsKey(q)) {
            TreeNode node = stack.pop();
            if(node.left != null) {
                stack.push(node.left);
                parents.put(node.left, node);
            }
            if(node.right != null) {
                stack.push(node.right);
                parents.put(node.right, node);
            }
        }

        Set<TreeNode> ancestors = new HashSet<>();
        while(p != null) {
            ancestors.add(p);
            p = parents.get(p);
        }
        while(ancestors.add(q)) {
            q = parents.get(q);
        }
        return q;
    }
    private int BOTH_PENDING = 2;
    private int ONE_SIDE_DONE = 1;
    private int BOTH_DONE = 0;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return root;
        }
        Deque<Pair<TreeNode, Integer>> stack = new ArrayDeque<>();
        TreeNode LCA = null, child = null;
        stack.push(new Pair<>(root, BOTH_PENDING));
        boolean one_side_done = false;

        while(!stack.isEmpty()) {
            Pair<TreeNode, Integer> pair = stack.peek();
            TreeNode node = pair.getKey();
            int status = pair.getValue();

            if(status != BOTH_DONE) {
                if(status == BOTH_PENDING) {
                    if(node == p || node == q) {
                        if(one_side_done) {
                            return LCA;
                        } else {
                            LCA = node;
                            one_side_done = true;
                        }
                    }
                    child = node.left;
                } else {
                    child = node.right;
                }
                stack.pop();
                stack.push(new Pair<>(node, status - 1));
                if(child != null) {
                    stack.push(new Pair<>(child, BOTH_PENDING));
                }
            } else {
                if(stack.pop().getKey() == LCA && one_side_done) {  // no need check one_side_done actually.
                    LCA = stack.peek().getKey();
                }
            }
        }
        return null;
    }
}
