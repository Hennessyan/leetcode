package challenge.dec;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// Binary Search Tree Iterator
public class Q173 {

//    class BSTIterator {
//
//        List<Integer> nodesSorted;
//        int index;
//
//        public BSTIterator(TreeNode root) {
//
//            // Array containing all the nodes in the sorted order
//            this.nodesSorted = new ArrayList<Integer>();
//
//            // Pointer to the next smallest element in the BST
//            this.index = -1;
//
//            // Call to flatten the input binary search tree
//            this._inorder(root);
//        }
//
//        private void _inorder(TreeNode root) {
//
//            if (root == null) {
//                return;
//            }
//
//            this._inorder(root.left);
//            this.nodesSorted.add(root.val);
//            this._inorder(root.right);
//        }
//
//        public int next() {
//            return this.nodesSorted.get(++this.index);
//        }
//
//        public boolean hasNext() {
//            return this.index + 1 < this.nodesSorted.size();
//        }
//    }

    // O(1) TC in average, O(h) satisfy SC required.
    class BSTIterator {
        Stack<TreeNode> stack;
        public BSTIterator(TreeNode root) {
            stack = new Stack<>();
            setNext(root);
        }

        private void setNext(TreeNode root) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
        }

        public int next() {
            TreeNode node = stack.pop();
            setNext(node.right);
            return node.val;
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }
}
