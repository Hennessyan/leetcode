package type_tree;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// Binary Tree Postorder Traversal
public class Q145 {

    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()) {
            root = stack.pop();
            if(root != null) {
                list.add(0, root.val);
                stack.push(root.left);
                stack.push(root.right);
            }
        }
        return list;
    }

    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while(root != null || !stack.isEmpty()) {
            while(root != null) {
                list.add(0, root.val);
                stack.push(root);
                root = root.right;
            }
            root = stack.pop().left;
        }
        return list;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        TreeNode cur = root ,tmp = null;
        while(cur != null) {
            if(cur.right == null) {
                list.add(0, cur.val);
                cur = cur.left;
            } else {
                tmp = cur.right;
                while(tmp.left != null && tmp.left != cur) {
                    tmp = tmp.left;
                }
                if(tmp.left == null) {
                    list.add(0, cur.val);
                    tmp.left = cur;
                    cur = cur.right;
                } else {
                    tmp.left = null;
                    cur = cur.left;
                }
            }
        }
        return list;
    }
}
