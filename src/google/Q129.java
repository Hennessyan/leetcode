package google;

import common.TreeNode;
import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Deque;

// Sum Root to Leaf Numbers
public class Q129 {

    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }
    private int dfs(TreeNode node, int sum) {
        if(node == null) {
            return 0;
        }
        sum = sum * 10 + node.val;
        if(node.left == null && node.right == null) {
            return sum;
        }
        return dfs(node.left, sum) + dfs(node.right, sum);
    }

    public int sumNumbers1(TreeNode root) {
        Deque<Pair<TreeNode, Integer>> stack = new ArrayDeque<>();
        stack.push(new Pair<>(root, 0));
        int sum = 0, val = 0;
        while(!stack.isEmpty()) {
            Pair<TreeNode, Integer> p = stack.poll();
            root = p.getKey();
            val = p.getValue();
            if(root != null) {
                val = val * 10 + root.val;
                if(root.left == null && root.right == null) {
                    sum += val;
                } else {
                    stack.push(new Pair<>(root.left, val));
                    stack.push(new Pair<>(root.right, val));
                }
            }
        }
        return sum;
    }
    // O(n) O(1)
    public int sumNumbers2(TreeNode root) {
        int sum = 0, cur = 0;
        TreeNode tmp = null;
        while(root != null) {
            if(root.left == null) {
                cur = cur * 10 + root.val;
                if(root.right == null) {
                    sum += cur;
                }
                root = root.right;
            } else {
                tmp = root.left;
                int step = 1;
                while(tmp.right != null && tmp.right != root) {
                    tmp = tmp.right;
                    step++;
                }
                if(tmp.right == null) {
                    cur = cur * 10 + root.val;
                    tmp.right = root;
                    root = root.left;
                } else {
                    if(tmp.left == null) {  // must be root to leaf path
                        sum += cur;
                    }
                    while(step-- > 0) {
                        cur /= 10;
                    }
                    tmp.right = null;
                    root = root.right;
                }
            }
        }
        return sum;
    }
}
