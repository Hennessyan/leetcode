package challenge.dec;

import common.TreeNode;

// Maximum Depth of Binary Tree
public class Q104 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Q104 q = new Q104();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(q.maxDepth(root));
    }
    // O(n) O(n)
    /*method1*/
    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return (left == 0 || right == 0) ? left + right + 1: Math.max(left,right) + 1;
    }

    /*method2*/
//	public int maxDepth(TreeNode root) {
//    if(root == null) return 0;
//    int depth = 0;
//    Queue<TreeNode> q = new LinkedList<TreeNode>();
//    q.offer(root);
//    while(!q.isEmpty()){
//        int size = q.size();
//        // for each level
//        for(int i=0;i<size;i++){
//            TreeNode node = q.poll();
//            if(node.left != null){
//                q.offer(node.left);
//            }
//            if(node.right != null){
//                q.offer(node.right);
//            }
//        }
//        depth++;
//    }
//    return depth;
//}

    /*method3*/
//	public int maxDepth(TreeNode root) {
//        if(root == null){
//            return 0;
//        }
//        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
//    }
}
