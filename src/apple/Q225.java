package apple;

import java.util.LinkedList;
import java.util.Queue;

// Implement Stack using Queues
public class Q225 {
    // One Queue : push - O(2n) pop - O(1)
    class MyStack {
        private Queue<Integer> queue;
        /** Initialize your data structure here. */
        public MyStack() {
            queue = new LinkedList<>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            queue.offer(x);
            for(int i = 0; i < queue.size() - 1; i++) {
                queue.offer(queue.poll());
            }
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            return queue.poll();
        }

        /** Get the top element. */
        public int top() {
            return queue.peek();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queue.isEmpty();
        }
    }
    // Two Queue : push - O(n) pop - O(1) (fast : 100% 81.84%)
    class MyStack1 {

        private Queue<Integer> queue;
        private Queue<Integer> q1 = new LinkedList<>();
        /** Initialize your data structure here. */
        public MyStack1() {
            queue = new LinkedList<>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            q1.offer(x);
            while(!queue.isEmpty()) {
                q1.offer(queue.poll());
            }
            Queue<Integer> tmp = queue;
            queue = q1;
            q1 = tmp;
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            return queue.poll();
        }

        /** Get the top element. */
        public int top() {
            return queue.peek();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queue.isEmpty();
        }
    }
    // Two Queue : push - O(1) pop - O(n) (fast : 100% 81.84%)
    class MyStack2 {
        Queue<Integer> queue;
        Queue<Integer> q1 = new LinkedList<>();
        private int top;
        /** Initialize your data structure here. */
        public MyStack2() {
            queue = new LinkedList<>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            queue.offer(x);
            top = x;
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            while(queue.size() > 1) {
                top = queue.poll();
                q1.offer(top);
            }
            int val = queue.poll();
            Queue<Integer> tmp = queue;
            queue = q1;
            q1 = tmp;
            return val;
        }

        /** Get the top element. */
        public int top() {
            return top;
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queue.isEmpty();
        }
    }
}
