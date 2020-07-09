package facebook;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

// Unique Binary Search Trees II
public class Q95 {
    // O(n*Gn) O(n*Gn) Gn = G0*Gn-1 + G1*Gn-2 + G2*Gn-3 + ... + Gn-1*G0
    public List<TreeNode> generateTrees(int n) {
        if(n <= 0) {
            return new ArrayList<>();
        }
        return help(1, n);
    }
    private List<TreeNode> help(int l, int r) {
        List<TreeNode> list = new ArrayList<>();
        if(l > r) {
            list.add(null);
            return list;
        }
        for(int i = l ; i <= r; i++) {
            for(TreeNode left : help(l, i - 1)) {
                for(TreeNode right : help(i + 1, r)) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    list.add(root);
                }
            }
        }
        return list;
    }
}
