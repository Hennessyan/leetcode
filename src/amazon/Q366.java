package amazon;

import common.TreeNode;

import java.util.*;

// Find Leaves of Binary Tree
public class Q366 {
    // O(n) O(n)
    List<List<Integer>> res;
    public List<List<Integer>> findLeaves(TreeNode root) {
        res = new ArrayList<>();
        dfs(root);
        return res;
    }
    private int dfs(TreeNode node) {
        if(node == null) return -1;
        int l = dfs(node.left);
        int r = dfs(node.right);
        int cur = Math.max(l, r) + 1;
        if(res.size() == cur) {
            res.add(new ArrayList<>());
        }
        res.get(cur).add(node.val);
        return cur;
    }

    // expensive : O(n^2) O(n)
    public List<List<Integer>> findLeaves2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        while(root != null){
            List<Integer> list = new ArrayList<>();
            root = help(root, list);
            res.add(list);
        }
        return res;
    }
    private TreeNode help(TreeNode root, List<Integer> list) {
        if(root == null) {
            return root;
        }
        if(root.left == null && root.right == null) {
            list.add(root.val);
            return null;
        }
        root.left = help(root.left, list);
        root.right = help(root.right, list);
        return root;
    }

    // very slow with data structures, don't use it.
    public List<List<Integer>> findLeaves1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        Map<TreeNode, TreeNode> graph = new HashMap<>();
        Map<TreeNode, Integer> degree = new HashMap<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            int count = 0;
            TreeNode node = stack.pop();
            if(node.left != null) {
                graph.put(node.left, node);
                stack.push(node.left);
                count++;
            }
            if(node.right != null) {
                graph.put(node.right, node);
                stack.push(node.right);
                count++;
            }
            degree.put(node, count);
        }
        Queue<TreeNode> queue = new LinkedList<>();
        for(TreeNode key : degree.keySet()) {
            if(degree.get(key) == 0) {
                queue.add(key);
            }
        }
        while(!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode tmp = queue.poll();
                list.add(tmp.val);
                TreeNode p = graph.get(tmp);
                if(degree.containsKey(p)) {
                    int c = degree.get(p);
                    if(--c == 0) {
                        queue.add(p);
                    } else {
                        degree.put(p, c);
                    }
                }
            }
            res.add(list);
        }
        return res;
    }

}
