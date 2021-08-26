package amazon;

import java.util.concurrent.locks.ReentrantLock;

// Design Circular Queue
public class Q622 {
    // array method
    class MyCircularQueue {
        int[] arr;
        int head;
        int count;
        int capacity;
        public MyCircularQueue(int k) {
            this.arr = new int[k];
            this.head = 0;
            this.count = 0;
            this.capacity = k;
        }

        public boolean enQueue(int value) {
            if(this.count == this.capacity) {
                return false;
            }
            int index = (this.head + this.count) % this.capacity;
            this.count++;
            arr[index] = value;
            return true;
        }

        public boolean deQueue() {
            if(this.count == 0) {
                return false;
            }
            this.count--;
            this.head = (this.head + 1) % this.capacity;
            return true;
        }

        public int Front() {
            if(this.count == 0) {
                return -1;
            }
            return arr[this.head];
        }

        public int Rear() {
            if(this.count == 0) {
                return -1;
            }
            return arr[(this.head + this.count - 1) % this.capacity];
        }

        public boolean isEmpty() {
            return this.count == 0;
        }

        public boolean isFull() {
            return this.count == this.capacity;
        }
    }
    // thread-safe example with lock
//    class MyCircularQueue {
//
//        private Node head, tail;
//        private int count;
//        private int capacity;
//        // Additional variable to secure the access of our queue
//        private ReentrantLock queueLock = new ReentrantLock();
//
//        /** Initialize your data structure here. Set the size of the queue to be k. */
//        public MyCircularQueue(int k) {
//            this.capacity = k;
//        }
//
//        /** Insert an element into the circular queue. Return true if the operation is successful. */
//        public boolean enQueue(int value) {
//            // ensure the exclusive access for the following block.
//            queueLock.lock();
//            try {
//                if (this.count == this.capacity)
//                    return false;
//
//                Node newNode = new Node(value);
//                if (this.count == 0) {
//                    head = tail = newNode;
//                } else {
//                    tail.nextNode = newNode;
//                    tail = newNode;
//                }
//                this.count += 1;
//
//            } finally {
//                queueLock.unlock();
//            }
//            return true;
//        }
//    }
    // single list
    class MyCircularQueue1 {
        Node head, tail;
        int count, capacity;

        public MyCircularQueue1(int k) {
            this.capacity = k;
            this.count = 0;
        }

        public boolean enQueue(int value) {
            if(this.count == this.capacity) {
                return false;
            }
            Node node = new Node(value);
            if(count == 0) {
                head = tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
            this.count++;
            return true;
        }

        public boolean deQueue() {
            if(this.count == 0) {
                return false;
            }
            head = head.next;
            this.count--;
            return true;
        }

        public int Front() {
            if(this.count == 0) {
                return -1;
            }
            return head.val;
        }

        public int Rear() {
            if(this.count == 0) {
                return -1;
            }
            return tail.val;
        }

        public boolean isEmpty() {
            return this.count == 0;
        }

        public boolean isFull() {
            return this.count == this.capacity;
        }

        class Node {
            int val;
            Node next;
            public Node(int val) {
                this.val = val;
                this.next = null;
            }
        }
    }
}
