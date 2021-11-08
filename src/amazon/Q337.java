package amazon;

import common.TreeNode;

import java.util.HashMap;
import java.util.Map;

// House Robber III
public class Q337 {
    // TLE
    public int rob(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int include = root.val + nrob(root.left) + nrob(root.right);
        int exclude = rob(root.left) + rob(root.right);
        return Math.max(include, exclude);
    }
    private int nrob(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return rob(root.left) + rob(root.right);
    }
    // O(n) O(n)
    Map<TreeNode, Integer> robMap = new HashMap<>();
    Map<TreeNode, Integer> nrobMap = new HashMap<>();
    public int rob1(TreeNode root) {
        return rob(root, false);
    }
    // parentRob : parent node is rob or not
    private int rob(TreeNode root, boolean parentRob) {
        if(root == null) return 0;
        if(parentRob) {
            if(nrobMap.containsKey(root)) {
                return nrobMap.get(root);
            }
            int val = rob(root.left, false) + rob(root.right, false);
            nrobMap.put(root, val);
            return val;
        } else {
            if(robMap.containsKey(root)) {
                return robMap.get(root);
            }
            int rob = root.val + rob(root.left, true) + rob(root.right, true);
            int nrob = rob(root.left, false) + rob(root.right, false);
            int res = Math.max(rob, nrob);
            robMap.put(root, res);
            return res;
        }
    }
}
