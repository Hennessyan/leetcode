package amazon;

import common.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Find Duplicate Subtrees
public class Q652 {

    // DFS : O(n^2) O(n^2) - build serial takes O(n)
//    Map<String, Integer> count;
//    List<TreeNode> ans;
//    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
//        count = new HashMap();
//        ans = new ArrayList();
//        collect(root);
//        return ans;
//    }
//
//    public String collect(TreeNode node) {
//        if (node == null) return "#";
//        String serial = node.val + "," + collect(node.left) + "," + collect(node.right);    // no need to split result after node.right
//        count.put(serial, count.getOrDefault(serial, 0) + 1);
//        if (count.get(serial) == 2)
//            ans.add(node);
//        return serial;
//    }
    // O(n) O(n) - L48 is constant time by using count map (L34)
    List<TreeNode> res;
    Map<String, Integer> map;
    Map<Integer, Integer> count;
    int tmp;
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        res = new ArrayList<>();
        map = new HashMap<>();
        count = new HashMap<>();
        tmp = 1;
        help(root);
        return res;
    }
    private int help(TreeNode root) {
        if(root == null) {
            return 0;
        }
        String serial = root.val + "," + help(root.left) + "," + help(root.right);
        int uid = map.computeIfAbsent(serial, x -> tmp++);
        count.put(uid, count.getOrDefault(uid, 0) + 1);
        if(count.get(uid) == 2) {
            res.add(root);
        }
        return uid;
    }
}
