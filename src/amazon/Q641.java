package amazon;
// Design Circular Deque
public class Q641 {
    class MyCircularDeque {

        Node head, tail;
        int count, capacity;
        /** Initialize your data structure here. Set the size of the deque to be k. */
        public MyCircularDeque(int k) {
            this.head = new Node(-1);
            this.tail = new Node(-1);
            head.next = tail;
            tail.prev = head;
            this.capacity = k;
            this.count = 0;
        }

        /** Adds an item at the front of Deque. Return true if the operation is successful. */
        public boolean insertFront(int value) {
            if(this.count == this.capacity) {
                return false;
            }
            Node node = new Node(value);
            head.next.prev = node;
            node.next = head.next;
            head.next = node;
            node.prev = head;
            this.count++;
            return true;
        }

        /** Adds an item at the rear of Deque. Return true if the operation is successful. */
        public boolean insertLast(int value) {
            if(this.count == this.capacity) {
                return false;
            }
            Node node = new Node(value);
            node.prev = tail.prev;
            tail.prev.next = node;
            node.next = tail;
            tail.prev = node;
            this.count++;
            return true;
        }

        /** Deletes an item from the front of Deque. Return true if the operation is successful. */
        public boolean deleteFront() {
            if(this.count == 0) {
                return false;
            }
            Node next = head.next.next;
            next.prev = head;
            head.next = next;
            this.count--;
            return true;
        }

        /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
        public boolean deleteLast() {
            if(this.count == 0) {
                return false;
            }
            Node prev = tail.prev.prev;
            prev.next = tail;
            tail.prev = prev;
            this.count--;
            return true;
        }

        /** Get the front item from the deque. */
        public int getFront() {
            // if(this.count == 0) {    // no need this part if we initial value as -1.
            //     return -1;
            // }
            return head.next.val;
        }

        /** Get the last item from the deque. */
        public int getRear() {
            return tail.prev.val;
        }

        /** Checks whether the circular deque is empty or not. */
        public boolean isEmpty() {
            return this.count == 0;
        }

        /** Checks whether the circular deque is full or not. */
        public boolean isFull() {
            return this.count == this.capacity;
        }
        class Node {
            int val;
            Node prev, next;
            public Node(int val) {
                this.val = val;
                this.prev = null;
                this.next = null;
            }
        }
    }
}
