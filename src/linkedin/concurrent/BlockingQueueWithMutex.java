package linkedin.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
// Busy wait solution using Lock
public class BlockingQueueWithMutex<T> {

    T[] arr;
    int capacity, size;
    int head, tail;
    Lock lock;
    public BlockingQueueWithMutex(int capacity) {
        arr = (T[]) new Object[capacity];
        this.capacity = capacity;
        this.size = 0;
        this.head = 0;
        this.tail = 0;
        this.lock = new ReentrantLock();
    }

    public void enqueue(T item) throws InterruptedException {
        lock.lock();
        while(this.size == this.capacity) {
            lock.unlock();
            lock.lock();
        }
        arr[tail++] = item;
        if(tail == capacity) tail = 0;
        size++;
        lock.unlock();
    }

    public T dequeue() throws InterruptedException {
        T item = null;
        lock.lock();
        while(this.size == 0) {
            lock.unlock();
            lock.lock();
        }
        item = arr[head];
        arr[head++] = null;
        if(head == capacity) head = 0;
        size--;
        lock.unlock();
        return item;
    }
}
