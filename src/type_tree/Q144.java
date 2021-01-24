package type_tree;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// Binary Tree Preorder Traversal
public class Q144 {
    // O(n) O(h)
    public List<Integer> preorderTraversal0(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        help(root, list);
        return list;
    }
    private void help(TreeNode root, List<Integer> list) {
        if(root != null) {
            list.add(root.val);
            help(root.left, list);
            help(root.right, list);
        }
    }
    // O(n) O(h)
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while(root != null || !stack.isEmpty()) {
            while(root != null) {
                stack.push(root);
                list.add(root.val);
                root = root.left;
            }
            root = stack.pop().right;
        }
        return list;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
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
                    list.add(cur.val);      //注意这里是cur.val
                    tmp.right = cur;
                    cur = cur.left;
                } else {
                    tmp.right = null;
                    cur = cur.right;
                }
            }
        }
        return list;
    }

    public ArrayList<Integer> preorderTraversal3(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if(root == null)
			return result;
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		while(!stack.empty()){
			TreeNode cur = stack.pop();
			result.add(cur.val);
			if(cur.right != null)
				stack.push(cur.right);
			if(cur.left != null)
				stack.push(cur.left);
		}
		return result;
	}
}
