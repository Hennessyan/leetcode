package challenge.dec;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

// Validate Binary Search Tree
public class Q98 {
    // method1 : O(n) O(n)
    public boolean isValidBST1(TreeNode root) {
        if(root == null) {
            return true;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode pre = null;
        while(root != null || !stack.isEmpty()) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(pre != null && root.val <= pre.val) {
                return false;
            }
            pre = root;
            root = root.right;
        }
        return true;
    }
    // method2 : O(n) O(n)
    private Integer prev;
    public boolean isValidBST2(TreeNode root) {
        prev = null;
        return inorder(root);
    }
    private boolean inorder(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!inorder(root.left)) {
            return false;
        }
        if (prev != null && root.val <= prev) {
            return false;
        }
        prev = root.val;
        return inorder(root.right);
    }

    //Can't use isValid(root, Integer.MIN_VALUE, Integer.MAX_VALUE):
    //It failed when a tree node is either Integer.MIN_VALUE or Integer.MAX_VALUE
//	public boolean isValidBST(TreeNode root) {
//		 return isValid(root, null, null);
//    }
//	private  boolean isValid(TreeNode root, Integer min, Integer max){
//		if(root == null)
//			return true;
//		if(min != null && root.val <= min) return false;
//		if(max != null && root.val >= max) return false;
//
//		 return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
//	}

    /*method3 morris traversal*/
    public boolean isValidBST3(TreeNode root) {
        if(root == null){
            return true;
        }
        TreeNode pre = null, cur = root;
        while(cur != null){
            if(cur.left == null){
                if(pre != null && pre.val >= cur.val){
                    return false;
                }
                pre = cur;
                cur = cur.right;
            }else{
                TreeNode tmp = cur.left;
                while(tmp.right != null && tmp.right != cur){
                    tmp = tmp.right;
                }
                if(tmp.right == null){
                    tmp.right = cur;
                    cur = cur.left;
                }else{
                    tmp.right = null;
                    if(pre != null && pre.val >= cur.val){      // no need to check if pre != null here
                        return false;
                    }
                    pre = cur;
                    cur = cur.right;
                }
            }
        }
        return true;
    }
}
