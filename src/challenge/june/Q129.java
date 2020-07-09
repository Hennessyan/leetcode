package challenge.june;

import common.TreeNode;
import javafx.util.Pair;

import java.util.Deque;
import java.util.LinkedList;

// Sum Root to Leaf Numbers
public class Q129 {


    // O(n) O(h)
    public int sumNumbers1(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int val) {
        if(root == null) {
            return 0;           //注意[0,1]的例子.
        }
        val = val * 10 + root.val;
        if(root.left == null && root.right == null) {
            return val;
        }
        return dfs(root.left, val) + dfs(root.right, val);
    }
    // O(n) O(h)
    public int sumNumbers2(TreeNode root) {
        Deque<Pair<TreeNode, Integer>> deque = new LinkedList<>();
        deque.push(new Pair<>(root, 0));

        int sum = 0;
        while(!deque.isEmpty()) {
            Pair<TreeNode, Integer> tmp = deque.pop();
            TreeNode node = tmp.getKey();
            int val = tmp.getValue();
            if(node != null) {
                val = val * 10 + node.val;
                if(node.left == null && node.right == null) {
                    sum += val;
                } else {
                    deque.push(new Pair<>(node.left, val));
                    deque.push(new Pair<>(node.right, val));
                }
            }
        }
        return sum;
    }
    // Morris Preorder Traversal. O(n) O(1)
    public int sumNumbers(TreeNode root) {
        TreeNode tmp;
        int sum = 0, cur = 0, step;
        while(root != null) {
            if(root.left == null) {
                cur = cur * 10 + root.val;
                if(root.right == null) {
                    sum += cur;
                }
                root = root.right;
            } else {
                tmp = root.left;
                step = 1;
                while(tmp.right != null && tmp.right != root) {
                    tmp = tmp.right;
                    step++;
                }
                if(tmp.right == null) {
                    tmp.right = root;
                    cur = cur * 10 + root.val;
                    root = root.left;
                } else {
                    tmp.right = null;
                    if(tmp.left == null) {
                        sum += cur;
                    }
                    for(int i = 0; i < step; i++) {
                        cur /= 10;
                    }
                    root = root.right;
                }
            }
        }
        return sum;
    }
}
