package type_tree;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// Binary Tree Inorder Traversal
public class Q94 {
    // recursion: O(n) O(h)
    public List<Integer> inorderTraversal0(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        help(list, root);
        return list;
    }
    private void help(List<Integer> list, TreeNode root) {
        if(root != null) {
            help(list, root.left);
            list.add(root.val);
            help(list, root.right);
        }
    }
    // iteration : O(n) O(h)
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while(root != null && !stack.isEmpty()) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.val);
            root = root.right;
        }
        return list;
    }

    // morris traversal: O(n) O(1)
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        TreeNode cur = root;
        while(cur != null) {
            if(cur.left == null) {
                list.add(cur.val);
                cur = cur.right;
            } else {
                TreeNode tmp = cur.left;
                while(tmp.right != null && tmp.right != cur) {
                    tmp = tmp.right;
                }
                if(tmp.right == null) {
                    tmp.right = cur;
                    cur = cur.left;
                } else {
                    tmp.right = null;
                    list.add(cur.val);
                    cur = cur.right;
                }
            }
        }
        return list;
    }
}
