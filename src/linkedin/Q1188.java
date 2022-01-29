package linkedin;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// Design Bounded Blocking Queue
public class Q1188 {

    class BoundedBlockingQueue {

        private Semaphore enq;

        private Semaphore deq;

        ConcurrentLinkedDeque<Integer> q;

        public BoundedBlockingQueue(int capacity) {
            q =  new ConcurrentLinkedDeque<>();
            enq = new Semaphore(capacity);
            deq = new Semaphore(0);
        }

        public void enqueue(int element) throws InterruptedException {
            enq.acquire();
            q.add(element);
            deq.release();
        }

        public int dequeue() throws InterruptedException {
            deq.acquire();
            int val = q.poll();
            enq.release();
            return val;
        }

        public int size() {
            return q.size();
        }
    }

    class BoundedBlockingQueue2 {

        private ConcurrentLinkedDeque<Integer> deque;
        private ReentrantLock lock = new ReentrantLock();
        private Condition full = lock.newCondition();
        private Condition empty = lock.newCondition();
        private int capacity;
        public BoundedBlockingQueue2(int capacity) {
            this.capacity = capacity;
            this.deque = new ConcurrentLinkedDeque<>();
        }

        public void enqueue(int element) throws InterruptedException {
            lock.lock();
            try {
                while(deque.size() == capacity) {
                    full.await();
                }
                deque.offer(element);
                empty.signal();
            } finally {
                lock.unlock();
            }
        }

        public int dequeue() throws InterruptedException {
            lock.lock();
            try {
                while(deque.isEmpty()) {
                    empty.await();
                }
                int val = deque.poll();
                full.signal();
                return val;
            } finally {
                lock.unlock();
            }
        }

        public int size() {
            lock.lock();
            try {
                return deque.size();
            } finally {
                lock.unlock();
            }
        }
    }

    class BoundedBlockingQueue1 {
        Deque<Integer> deq;
        int size;
        Object lock;
        public BoundedBlockingQueue1(int capacity) {
            deq = new LinkedList<>();
            size = capacity;
            lock = new Object();
        }

        public void enqueue(int element) throws InterruptedException {
            synchronized(lock) {
                while(deq.size() == size) {
                    lock.wait();
                }
                deq.addLast(element);
                lock.notify();
            }
        }

        public int dequeue() throws InterruptedException {
            int val = 0;
            synchronized(lock) {
                while(deq.isEmpty()) {
                    lock.wait();
                }
                val = deq.removeFirst();
                lock.notify();
            }
            return val;
        }

        public int size() {
            return deq.size();
        }
    }
}
