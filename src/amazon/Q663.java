package amazon;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

// Equal Tree Partition
public class Q663 {
    // O(n) O(n)
    public boolean checkEqualTree(TreeNode root) {
        if(root == null) return false;
        List<Integer> values = new ArrayList<>();
        int sum = dfs(root, values);
        if(sum % 2 == 0) {
            for(int i = 0; i < values.size() - 1; i++) {
                if(sum == values.get(i) * 2) return true;
            }
        }
        return false;
    }
    private int dfs(TreeNode root, List<Integer> values) {
        if(root == null) return 0;
        int val = root.val + dfs(root.left, values) + dfs(root.right, values);
        values.add(val);
        return val;
    }
}
