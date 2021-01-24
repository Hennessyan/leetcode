package apple;

import java.util.Stack;

// Implement Queue using Stacks
public class Q232 {

    class MyQueue {
        private Stack<Integer> stack;
        private Stack<Integer> stack1 = new Stack<>();
        /** Initialize your data structure here. */
        public MyQueue() {
            stack = new Stack<>();
        }
        // O(n)
        /** Push element x to the back of queue. */
        public void push(int x) {
            while(!stack.isEmpty()) {
                stack1.push(stack.pop());
            }
            stack.push(x);
            while(!stack1.isEmpty()) {
                stack.push(stack1.pop());
            }
        }
        // O(1)
        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            return stack.pop();
        }

        /** Get the front element. */
        public int peek() {
            return stack.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stack.isEmpty();
        }
    }
    // Push - O(1) per operation, Pop - Amortized O(1) per operation.
    class MyQueue1 {
        private Stack<Integer> stack;
        private Stack<Integer> stack1 = new Stack<>();
        private int front;  // JVM will initialize global variable.
        /** Initialize your data structure here. */
        public MyQueue1() {
            stack = new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            if(stack.isEmpty()) {
                front = x;
            }
            stack.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if(stack1.isEmpty()) {
                while(!stack.isEmpty()) {
                    stack1.push(stack.pop());
                }
            }
            return stack1.pop();
        }

        /** Get the front element. */
        public int peek() {
            return stack1.isEmpty() ? front : stack1.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stack.isEmpty() && stack1.isEmpty();
        }
    }
}
