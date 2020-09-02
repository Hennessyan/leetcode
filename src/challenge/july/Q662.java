package challenge.july;

import common.TreeNode;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

// Maximum Width of Binary Tree
public class Q662 {
    // BFS : O(n) O(n)
    public int widthOfBinaryTree0(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<Pair<TreeNode, Integer>> queue = new LinkedList<>(); // Queue can't use getFirst
        queue.add(new Pair<>(root, 0));
        int max = 0;

        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> head = queue.getFirst();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Pair<TreeNode, Integer> tmp = queue.poll();
                TreeNode node = tmp.getKey();
                int val = tmp.getValue();
                if (node.left != null) {
                    queue.add(new Pair<>(node.left, val * 2));
                }
                if (node.right != null) {
                    queue.add(new Pair<>(node.right, val * 2 + 1));
                }
                max = Math.max(max, val - head.getValue() + 1);
            }
        }
        return max;
    }
    // DFS : O(n) O(n)
    Map<Integer, Integer> firstColIndex;
    int max = 0;

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        firstColIndex = new HashMap<>();
        dfs(root, 0, 0);
        return max;
    }

    private void dfs(TreeNode node, int d, int val) {
        if (!firstColIndex.containsKey(d)) {
            firstColIndex.put(d, val);
        }
        max = Math.max(max, val - firstColIndex.get(d) + 1);
        if (node.left != null) {
            dfs(node.left, d + 1, 2 * val);
        }
        if (node.right != null) {
            dfs(node.right, d + 1, 2 * val + 1);
        }
    }
}
