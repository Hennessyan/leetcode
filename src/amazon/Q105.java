package amazon;

import common.TreeNode;

import java.util.Stack;

// Construct Binary Tree from Preorder and Inorder Traversal
public class Q105 {
    // maybe -> O(n/2*lgn) O(lgn)
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, 0, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int p, int[] inorder, int is, int ie) {
        if(is > ie) return null;
        TreeNode root = new TreeNode(preorder[p]);
        int i = is;
        while(i <= ie && preorder[p] != inorder[i]) {
            i++;
        }
        root.left = buildTree(preorder, p + 1, inorder, is, i - 1);
        root.right = buildTree(preorder, p + i - is + 1, inorder, i + 1, ie);
        return root;
    }

    // O(n) O(n) -> use hashmap to record inorder value with index
    // https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/

    /*method1 iterative*/
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        int m = 0, n = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        TreeNode root = new TreeNode(preorder[m++]);
        stack.push(root);

        while(m < preorder.length) {
            while(!stack.isEmpty() && stack.peek().val == inorder[n]) {
                prev = stack.pop();
                n++;
            }
            TreeNode node = new TreeNode(preorder[m++]);
            if(prev != null) {
                prev.right = node;
            } else {
                stack.peek().left = node;
            }
            stack.push(node);
            prev = null;
        }
        return root;
    }
    /*method2*/
//	int pInorder;
//	int pPreorder;
//	public TreeNode buildTree(int[] preorder, int[] inorder) {
//		pInorder = 0;
//		pPreorder = 0;
//		return buildTree(preorder, inorder, null);
//	}
//
//	private TreeNode buildTree(int[] preorder, int[] inorder, TreeNode end) {
//		if(pPreorder >= preorder.length)
//			return null;
//		TreeNode n = new TreeNode(preorder[pPreorder++]);
//
//		if(inorder[pInorder] != n.val)
//			n.left = buildTree(preorder, inorder, n);
//
//		pInorder++;
//		if(end == null || inorder[pInorder] != end.val)
//			n.right = buildTree(preorder, inorder, end);
//		return n;
//	}
}
