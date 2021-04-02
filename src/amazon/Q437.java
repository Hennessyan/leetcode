package amazon;

import common.TreeNode;

import java.util.HashMap;
import java.util.Map;

// Path Sum III
// similar to Q560
public class Q437 {
    // prefix sum : O(n) O(n)
    Map<Integer, Integer> memo;
    public int pathSum(TreeNode root, int sum) {
        memo = new HashMap<>();
        memo.put(0, 1);
        return backtrack(root, 0, sum);
    }
    private int backtrack(TreeNode node, int total, int sum) {
        if(node == null) {
            return 0;
        }
        total += node.val;
        int res = memo.getOrDefault(total - sum, 0);
        memo.put(total, memo.getOrDefault(total, 0) + 1);
        res += backtrack(node.left, total, sum) + backtrack(node.right, total, sum);
        memo.put(total, memo.get(total) - 1);
        return res;
    }
}
