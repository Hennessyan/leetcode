package amazon;

import common.TreeNode;

import java.util.LinkedList;
import java.util.List;

// Closest Binary Search Tree Value II
public class Q272 {

    // check solution ->
    // heap : comparator should be < ? -1 : 1
    // quick-select : O(n) -> O(n^2) in worst case
    // O(n) O(n)
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        LinkedList<Integer> deque = new LinkedList<>();
        inorder(root, deque);
        while(deque.size() > k) {
            if(Math.abs(deque.getFirst() - target) < Math.abs(deque.getLast() - target)) {
                deque.removeLast();
            } else {
                deque.removeFirst();
            }
        }
        return deque;
    }
    private void inorder(TreeNode node, LinkedList<Integer> deque) {
        if(node != null) {
            inorder(node.left, deque);
            deque.add(node.val);
            inorder(node.right, deque);
        }
    }
}
