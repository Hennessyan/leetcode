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
    // follow-up : may less than O(n)
    public List<Integer> closestKValues1(TreeNode root, double target, int k) {
        LinkedList<Integer> list = new LinkedList<>();
        inorder(root, list, target, k);

        return list;
    }
    private void inorder(TreeNode node, LinkedList<Integer> list, double target, int k) {
        if(node == null) return;
        inorder(node.left, list, target, k);
        if(list.size() == k) {
            if(Math.abs(target - list.getFirst()) > Math.abs(target - node.val)) {
                list.removeFirst();
            } else return;
        }
        list.add(node.val);
        inorder(node.right, list, target, k);
    }
}
