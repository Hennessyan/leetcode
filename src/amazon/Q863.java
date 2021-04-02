package amazon;

import common.TreeNode;

import java.util.*;

// All Nodes Distance K in Binary Tree
public class Q863 {
    // O(n) O(n)
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList<>();
        help(root, target, K, res);
        return res;
    }
    private int help(TreeNode root, TreeNode target, int k, List<Integer> res) {
        if(root == null) {
            return -1;
        }
        if(root == target) {
            get(target, k, res);
            return 1;
        }
        int l = help(root.left, target, k, res);
        if(l != -1) {
            if(l == k) {
                res.add(root.val);
                return -1;          // 保证了不会有超过k的情况.
            } else {
                get(root.right, k - l - 1, res);
                return l + 1;
            }
        }
        int r = help(root.right, target, k, res);
        if(r != -1) {
            if(r == k) {
                res.add(root.val);
                return -1;          // 保证了不会有超过k的情况.
            } else {
                get(root.left, k - r - 1, res);
                return r + 1;
            }
        }

        return -1;
    }
    private void get(TreeNode node, int k, List<Integer> res) {
        if(node != null) {
            if(k == 0) {
                res.add(node.val);
                return;
            }
            get(node.left, k - 1, res);
            get(node.right, k - 1, res);
        }
    }
    // method2 : worse than method1
    Map<TreeNode, TreeNode> parent;
    public List<Integer> distanceK2(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList<>();
        parent = new HashMap<>();
        dfs(root, null);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(target);
        Set<TreeNode> seen = new HashSet<>();
        seen.add(target);

        while(!queue.isEmpty()) {
            if(K == 0) {            // corner case : K == 0
                while(!queue.isEmpty()) {
                    res.add(queue.poll().val);
                }
                return res;
            }
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                if(node.left != null && !seen.contains(node.left)) {
                    queue.add(node.left);
                    seen.add(node.left);
                }
                if(node.right != null && !seen.contains(node.right)) {
                    queue.add(node.right);
                    seen.add(node.right);
                }
                TreeNode prev = parent.get(node);
                if(prev != null && !seen.contains(prev)) {
                    queue.add(prev);
                    seen.add(prev);
                }
            }
            K--;
        }
        return res;
    }
    private void dfs(TreeNode node, TreeNode p) {
        if(node != null) {
            parent.put(node, p);
            dfs(node.left, node);
            dfs(node.right, node);
        }
    }
}
