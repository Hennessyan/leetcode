package amazon;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

// Path Sum II
public class Q113 {
    // O(nlgn) O(lgn) - depth is lgn, n / 2 leaves.
    // vs
    // O(n^2) O(n)
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        backtrack(root, new ArrayList<>(), res, sum);
        return res;
    }
    private void backtrack(TreeNode root, List<Integer> list, List<List<Integer>> res, int sum) {
        if(root == null) {
            return;
        }
        list.add(root.val);
        if(sum - root.val == 0 && root.left == null && root.right == null) {
            res.add(new ArrayList<>(list));
        }else {
            backtrack(root.left, list, res, sum - root.val);
            backtrack(root.right, list, res, sum - root.val);
        }
        list.remove(list.size() - 1);
    }
}
