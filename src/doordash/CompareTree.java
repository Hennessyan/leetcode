package doordash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompareTree {


    public static void main(String[] args) {
        CompareTree q = new CompareTree();
        TreeNode r1 = q.new TreeNode(1,1);
        TreeNode rc1 = q.new TreeNode(2,2);
        TreeNode rc2 = q.new TreeNode(3,3);
        r1.children.add(rc1);
        r1.children.add(rc2);
        rc1.children.add(q.new TreeNode(4,4));
        rc1.children.add(q.new TreeNode(5,5));
        rc2.children.add(q.new TreeNode(7,7));
        TreeNode r2 = q.new TreeNode(1,1);
        TreeNode rc3 = q.new TreeNode(2,2);
        TreeNode rc4 = q.new TreeNode(8,8);
        r2.children.add(rc3);
        r2.children.add(rc4);
        rc3.children.add(q.new TreeNode(5,5));
        rc3.children.add(q.new TreeNode(4,4));
        rc3.children.add(q.new TreeNode(6,6));
        rc4.children.add(q.new TreeNode(7,7));
        System.out.println(q.compare(r1, r2));
    }

    class TreeNode {
        int key, val;
        List<TreeNode> children;
        public TreeNode(int key, int val) {
            this.key = key;
            this.val = val;
            this.children = new ArrayList<>();
        }
    }

    public int compare(TreeNode r1, TreeNode r2) {
        if(r1 == null || r2 == null || r1.key != r2.key)
            return count(r1) + count(r2);
        int total = 0;
        // ask total should be 1 / 2 here:
        if(r1.val != r2.val) {
            total = 1;
        }
        Map<Integer, TreeNode> map = new HashMap<>();
        for(TreeNode child : r2.children) {
            map.put(child.key, child);
        }
        for(TreeNode c1 : r1.children) {
            TreeNode c2 = map.get(c1.key);
            total += compare(c1, c2);
            if(c2 != null) {
                map.remove(c1.key);
            }
        }
        for(TreeNode child : map.values()) {
            total += count(child);
        }
        return total;
    }
    private int count(TreeNode node) {
        if(node == null) return 0;
        int ans = 1;
        for(TreeNode child : node.children) {
            ans += count(child);
        }
        return ans;
    }
}


