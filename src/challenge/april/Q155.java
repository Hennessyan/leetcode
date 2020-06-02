package challenge.april;

import java.util.Stack;

// Min Stack
public class Q155 {

//    class MinStack {
//        Stack<Integer> stack;
//        int min;
//        /** initialize your data structure here. */
//        public MinStack() {
//            min = Integer.MAX_VALUE;
//            stack = new Stack<>();
//        }
//
//        public void push(int x) {
//            if(min >= x) {
//                stack.push(min);
//                min = x;
//            }
//            stack.push(x);
//        }
//
//        public void pop() {
//            if(min == stack.pop()) {
//                min = stack.pop();
//            }
//        }
//
//        public int top() {
//            return stack.peek();
//        }
//
//        public int getMin() {
//            return min;
//        }
//    }

    class MinStack {

        private Node head;
        public MinStack() {
            head = new Node(0, Integer.MAX_VALUE);
        }

        public void push(int x) {
            Node node = new Node(x, Math.min(x, head.min));
            node.next = head;
            head = node;
        }

        public void pop() {
            head = head.next;
        }

        public int top() {
            return head.val;
        }

        public int getMin() {
            return head.min;
        }
    }

    class Node {
        int val, min;
        Node next;
        public Node(int val, int min) {
            this.val = val;
            this.min = min;
        }
    }
}
