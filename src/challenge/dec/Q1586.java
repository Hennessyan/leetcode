package challenge.dec;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// Binary Search Tree Iterator II
public class Q1586 {
    // index must start from -1: index after call next() should be current position,
    // then prev() can get prev by using --index


    // SC : O(n)

    
    /*
    class BSTIterator {

        List<Integer> arr = new ArrayList();
        int pointer;
        int n;

        public void inorder(TreeNode r, List<Integer> arr) {
            if (r == null) return;
            inorder(r.left, arr);
            arr.add(r.val);
            inorder(r.right, arr);
        }
        // O(n)
        public BSTIterator(TreeNode root) {
            inorder(root, arr);
            n = arr.size();
            pointer = -1;
        }
        // O(1)
        public boolean hasNext() {
            return pointer < n - 1;
        }
        // O(1)
        public int next() {
            ++pointer;
            return arr.get(pointer);
        }
        // O(1)
        public boolean hasPrev() {
            return pointer > 0;
        }
        // O(1)
        public int prev() {
            --pointer;
            return arr.get(pointer);
        }
    }
    */
    // Javadocs recommends to use ArrayDeque, and not Stack as a stack implementation.
    class BSTIterator {
        Deque<TreeNode> stack;
        TreeNode node;
        List<Integer> list;
        int index;
        // O(1)
        public BSTIterator(TreeNode root) {
            node = root;
            stack = new ArrayDeque<>();
            list = new ArrayList<>();
            index = -1;
        }
        // O(1)
        public boolean hasNext() {
            return node != null || !stack.isEmpty() || index < list.size() - 1;
        }
        // O(n) in worst case
        public int next() {
            ++index;
            if(index == list.size()) {
                while(node != null) {
                    stack.push(node);
                    node = node.left;
                }
                node = stack.pop();
                list.add(node.val);
                node = node.right;
            }
            return list.get(index);
        }
        // O(1)
        public boolean hasPrev() {
            return index > 0;
        }
        // O(1)
        public int prev() {
            return list.get(--index);
        }
    }
}
