package google;

import common.ListNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// Next Greater Node In Linked List
public class Q1019 {
    // O(n) O(n)
    public int[] nextLargerNodes(ListNode head) {
        List<Integer> list = convertToArray(head);
        int[] res = new int[list.size()];
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < list.size(); i++) {
            while(!stack.isEmpty() && list.get(i) > list.get(stack.peek())) {
                res[stack.pop()] = list.get(i);
            }
            stack.push(i);
        }
        // we don't need to check whether stack is empty by using while, because rest value of res array is zero by default.
        return res;
    }
    private List<Integer> convertToArray(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while(head != null) {
            list.add(head.val);
            head = head.next;
        }
        return list;
    }
}
