package amazon;

import common.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

// Delete Leaves With a Given Value
public class Q1325 {
    // O(n) O(n)
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if(root == null) {
            return null;
        }

        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);
        if(root.left == null && root.right == null) {
            if(root.val == target) {
                return null;
            }
        }
        return root;
    }

    public TreeNode removeLeafNodes1(TreeNode root, int target) {
        if(root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode dummy = new TreeNode(-1);
        dummy.left = root;
        TreeNode cur = dummy, tmp = null;
        Map<TreeNode, TreeNode> map = new HashMap<>();
        queue.add(cur);
        while(!queue.isEmpty()) {
            cur = queue.poll();
            if(cur.left != null) {
                tmp = cur.left;
                if(tmp.left == null && tmp.right == null) {
                    if(tmp.val == target) {
                        cur.left = null;
                    }
                } else {
                    queue.add(tmp);
                    map.put(tmp, cur);
                }
            }
            if(cur.right != null) {
                tmp = cur.right;
                if(tmp.left == null && tmp.right == null) {
                    if(tmp.val == target) {
                        cur.right = null;
                    }
                } else {
                    queue.add(tmp);
                    map.put(tmp, cur);
                }
            }
            if(cur.val == target && cur.left == null && cur.right == null) {
                queue.add(map.get(cur));
            }
        }
        return dummy.left;
    }
}
