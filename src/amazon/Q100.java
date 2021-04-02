package amazon;

import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

// Same Tree
public class Q100 {

    //recustion/iteration:O(n)  O(n)
    //遍历n个node,因此TC是O(n), worst case,树是链表,因此深度为n，所以SC为O(n)
    /*method1*/
//	public boolean isSameTree(TreeNode p, TreeNode q) {
//		if(p == null && q == null)
//			return true;
//		if(p == null || q == null)
//			return false;
//		if(p.val == q.val)
//			return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
//		return false;
//    }
    /*method2*/
//	public boolean isSameTree(TreeNode p, TreeNode q) {
//	     Stack<TreeNode> stack_p = new Stack <> ();
//	     Stack<TreeNode> stack_q = new Stack <> ();
//	     if (p != null) stack_p.push( p ) ;
//	     if (q != null) stack_q.push( q ) ;
//	     while (!stack_p.isEmpty() && !stack_q.isEmpty()) {
//	    	 TreeNode pn = stack_p.pop() ;
//	    	 TreeNode qn = stack_q.pop() ;
//	    	 if (pn.val != qn.val) return false ;
//	    	 if (pn.right != null) stack_p.push(pn.right) ;
//	    	 if (qn.right != null) stack_q.push(qn.right) ;
//	    	 if (stack_p.size() != stack_q.size()) return false ;
//	    	 if (pn.left != null) stack_p.push(pn.left) ;
//	    	 if (qn.left != null) stack_q.push(qn.left) ;
//	    	 if (stack_p.size() != stack_q.size()) return false ;
//	     }
//	     return stack_p.size() == stack_q.size() ;
//	 }
    /*method3*/
    public boolean isSameTree(TreeNode root, TreeNode root1) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(root1);
        while(!queue.isEmpty()){
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();
            if(node1 == null && node2 == null)
                continue;
            if(node1 == null || node2 == null)
                return false;
            if(node1.val != node2.val)
                return false;
            queue.offer(node1.left);
            queue.offer(node2.left);
            queue.offer(node1.right);
            queue.offer(node2.right);
        }
        return true;
    }
}
