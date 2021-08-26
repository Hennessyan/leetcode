package google;

import common.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Delete Nodes And Return Forest
public class Q1110 {
    // postorder : O(n) O(n)
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> res = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();
        for(int val : to_delete) {
            seen.add(val);
        }
        if(!dfs(root, res, seen)) {
            res.add(root);
        }
        return res;
    }
    private boolean dfs(TreeNode root, List<TreeNode> res, Set<Integer> seen) {
        if(root == null) {
            return false;
        }
        boolean l = dfs(root.left, res, seen);
        boolean r = dfs(root.right, res, seen);
        if(l) {
            root.left = null;
        }
        if(r) {
            root.right = null;
        }
        if(seen.contains(root.val)) {
            seen.remove(root.val);
            if(root.left != null) res.add(root.left);       // should not use !l, otherwise will add null into res.
            if(root.right != null) res.add(root.right);     // should not use !r, otherwise will add null into res.
            return true;
        }
        return false;
    }
}
