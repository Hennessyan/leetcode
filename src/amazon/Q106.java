package amazon;

import common.TreeNode;

import java.util.Stack;

// Construct Binary Tree from Inorder and Postorder Traversal
public class Q106 {

//    Map<Integer, Integer> index;
//    int pi;
//    public TreeNode buildTree(int[] inorder, int[] postorder) {
//        index = new HashMap<>();
//        int n = inorder.length;
//        pi = n - 1;
//        for(int i = 0; i < n; i++) {
//            index.put(inorder[i], i);
//        }
//        return build(postorder, 0, n - 1);
//    }
//    private TreeNode build(int[] arr, int l, int r) {
//        if(l > r) return null;
//        TreeNode root = new TreeNode(arr[pi--]);
//        int tmp = index.get(root.val);
//        root.right = build(arr, tmp + 1, r);
//        root.left = build(arr, l, tmp - 1);
//        return root;
//    }


    /*method1*/
//	public TreeNode buildTree(int[] inorder, int[] postorder) {
//        if(inorder == null || postorder == null || inorder.length != postorder.length)
//        	return null;
//        HashMap<Integer, Integer> map = new HashMap<>();
//        for(int i = 0; i < inorder.length; i++)
//        	map.put(inorder[i], i);
//        return buildTreePostIn(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1, map);
//    }
//	private TreeNode buildTreePostIn(int[] inorder, int is, int ie, int[] postorder, int ps, int pe,
//			HashMap<Integer,Integer> hm){
//		if(is > ie || ps > pe)
//			return null;
//		TreeNode root = new TreeNode(postorder[pe]);
//		int rootIndex = hm.get(postorder[pe]);
//		root.left = buildTreePostIn(inorder, is, rootIndex-1, postorder,ps, ps+rootIndex-is-1, hm);
//		root.right = buildTreePostIn(inorder, rootIndex+1, ie, postorder, ps+rootIndex-is, pe-1, hm);
//		return root;
//	}
    /*method2 similar as method1, no hashmap, more quickly*/
//	public TreeNode buildTree(int[] inorder, int[] postorder) {
//	    return build(inorder,inorder.length-1,0,postorder,postorder.length-1);
//	}
//
//	public TreeNode build(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart){
//		if(inEnd > inStart){
//			return null;
//		}
//		TreeNode root = new TreeNode(postorder[postStart]);
//		if(inEnd == inStart){
//			return root;
//		}
//		int index = 0;
//		// find the index in inorder:
//		for(int i = inStart; i >= inEnd; i--){
//			if(inorder[i] == root.val){
//				index = i;
//				break;
//			}
//		}
//
//		root.right = build(inorder,inStart,index+1,postorder,postStart-1);
//		root.left = build(inorder,index-1,inEnd,postorder,postStart-(inStart-index)-1);
//
//		return root;
//	}
    /*method3*/
//	int pInorder;   // index of inorder array
//	int pPostorder; // index of postorder array
//	//(TreeNode end is the boundary of left subtree.)
//	private TreeNode buildTree(int[] inorder, int[] postorder, TreeNode end) {
//		if (pPostorder < 0) {
//			return null;
//		}
//
//		// create root node
//		TreeNode n = new TreeNode(postorder[pPostorder--]);
//
//		// if right node exist, create right subtree
//		if (inorder[pInorder] != n.val) {
//			n.right = buildTree(inorder, postorder, n);
//		}
//
//		pInorder--;
//
//		// if left node exist, create left subtree
//		if ((end == null) || (inorder[pInorder] != end.val)) {
//			n.left = buildTree(inorder, postorder, end);
//		}
//
//		return n;
//	}
//
//	public TreeNode buildTree(int[] inorder, int[] postorder) {
//		pInorder = inorder.length - 1;
//		pPostorder = postorder.length - 1;
//
//		return buildTree(inorder, postorder, null);
//	}
    /*method4*/
    //iterative
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0 || postorder.length == 0) return null;
        int m = inorder.length - 1;
        int n = postorder.length - 1;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(postorder[n]);
        n--;
        stack.push(root);
        TreeNode prev = null;

        while(n >= 0){
            while(!stack.empty() && stack.peek().val == inorder[m]){
                prev = stack.pop();
                m--;
            }

            TreeNode newNode = new TreeNode(postorder[n]);
            if(prev != null){
                prev.left = newNode;
            }
            else if(!stack.empty()){
                TreeNode cur = stack.peek();
                cur.right = newNode;
            }
            stack.push(newNode);
            prev = null;
            n--;
        }
        return root;
    }

    // https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/solution/
}
