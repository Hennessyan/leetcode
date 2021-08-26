package google;

import common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Find Mode in Binary Search Tree
public class Q501 {

    int count = 0, max = 0;
    Integer pre = null;
    List<Integer> list = null;
    public int[] findMode(TreeNode root) {
        inorder(root);
        int[] res = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
    private void inorder(TreeNode root) {
        if(root == null) {
            return;
        }
        inorder(root.left);
        if(pre != null && pre == root.val) {
            count++;
        } else {
            count = 1;
        }
        if(max < count) {
            max = count;
            list = new ArrayList<>(Arrays.asList(root.val));
        } else if(max == count) {
            list.add(root.val);
        }
        pre = root.val;
        inorder(root.right);
    }

//    TreeNode prev;
//    int count = 0;
//    int maxCount = -1;
//    public int[] findMode(TreeNode root) {
//        List<Integer> modes = new ArrayList<>();
//        prev = root;
//        inorder(root, modes);
//        int[] res = new int[modes.size()];
//        for (int i = 0; i < modes.size(); i++) {
//            res[i] = modes.get(i);
//        }
//        return res;
//    }
//    public void inorder(TreeNode root, List<Integer> modes) {
//        if (root == null) {
//            return;
//        }
//        inorder(root.left, modes);
//
//        count = prev.val == root.val ? count + 1 : 1;
//
//        if (count == maxCount) {
//            modes.add(root.val);
//        } else if (count > maxCount) {
//            modes.clear();            // O(n) operation !!!
//            modes.add(root.val);
//            maxCount = count;
//        }
//        prev = root;
//        inorder(root.right, modes);
//    }
}
