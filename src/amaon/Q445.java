package amaon;

import common.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

// Add Two Numbers II
public class Q445 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> s1 = new ArrayDeque<>();
        Deque<Integer> s2 = new ArrayDeque<>();
        while(l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while(l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        ListNode head = new ListNode(0);
        int sum = 0;
        while(!s1.isEmpty() || !s2.isEmpty()) {
            sum += s1.isEmpty() ? 0 : s1.pop();
            sum += s2.isEmpty() ? 0 : s2.pop();
            head.val = sum % 10;
            sum /= 10;
            ListNode node = new ListNode(sum);
            node.next = head;
            head = node;
        }
        return head.val == 0 ? head.next : head;
    }
    // take more times, and space is O(max(m,n))
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        // find the length of both lists
        int n1 = 0, n2 = 0;
        ListNode curr1 = l1, curr2 = l2;
        while (curr1 != null) {
            curr1 = curr1.next;
            ++n1;
        }
        while (curr2 != null) {
            curr2 = curr2.next;
            ++n2;
        }

        // parse both lists
        // and sum the corresponding positions
        // without taking carry into account
        // 3->3->3 + 7->7 --> 3->10->10--> 10->10->3
        curr1 = l1;
        curr2 = l2;
        ListNode head = null;
        while (n1 > 0 && n2 > 0) {
            int val = 0;
            if (n1 >= n2) {
                val += curr1.val;
                curr1 = curr1.next;
                --n1;
            }
            if (n1 < n2) {
                val += curr2.val;
                curr2 = curr2.next;
                --n2;
            }

            // update the result: add to front
            ListNode curr = new ListNode(val);
            curr.next = head;
            head = curr;
        }

        // take the carry into account
        // to have all elements to be less than 10
        // 10->10->3 --> 0->1->4 --> 4->1->0
        curr1 = head;
        head = null;
        int carry = 0;
        while (curr1 != null) {
            // current sum and carry
            int val = (curr1.val + carry) % 10;
            carry = (curr1.val + carry) / 10;

            // update the result: add to front
            ListNode curr = new ListNode(val);
            curr.next = head;
            head = curr;

            // move to the next elements in the list
            curr1 = curr1.next;
        }

        // add the last carry
        if (carry != 0) {
            ListNode curr = new ListNode(carry);
            curr.next = head;
            head = curr;
        }

        return head;
    }
}
