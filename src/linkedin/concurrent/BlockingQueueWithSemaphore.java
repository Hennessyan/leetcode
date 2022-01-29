//package linkedin.concurrent;
//
//public class BlockingQueueWithSemaphore<T> {
//
//    private T[] arr;
//    int capacity, size;
//    int head, tail;
//
//    CountingSemaphore semLock = new CountingSemaphore(1, 1);
//    CountingSemaphore semPro;
//    CountingSemaphore semCon;
//    public BlockingQueueWithSemaphore(int capacity) {
//        this.capacity = capacity;
//        this.size = 0;
//        this.head = 0;
//        this.tail = 0;
//        arr = (T[]) new Object[capacity];
//        semPro = new CountingSemaphore(capacity, capacity);
//        semCon = new CountingSemaphore(capacity, 0);
//    }
//
//    public void enqueue(T item) throws InterruptedException {
//        semProd.acquire();
//        semLock.acquire();
//        arr[tail++] = item;
//        if(tail == capacity) tail = 0;
//        size++;
//        semLock.release();
//        semCon.release();
//    }
//
//    public T dequeue() throws InterrutpedException {
//        T item = null;
//        semCon.acquire();
//        semLock.acquire();
//        item = arr[head];
//        arr[head++] = null;
//        if(head == capacity) head = 0;
//        size--;
//        semLock.release();
//        semPro.release();
//        return item;
//    }
//
//    class CountingSemaphore {
//
//        int usedPermits = 0;
//        int maxCount;
//
//        public CountingSemaphore(int count) {
//            this.maxCount = count;
//        }
//
//        public CountingSemaphore(int count, int initialPermits) {
//            this.maxCount = count;
//            this.usedPermits = this.maxCount - initialPermits;
//        }
//
//        public synchronized void acquire() throws InterruptedException {
//
//            while (usedPermits == maxCount)
//                wait();
//            // object.notify() is good enough as we can call release() multi times.
//            notify();
//            usedPermits++;
//        }
//
//        public synchronized void release() throws InterruptedException {
//
//            while (usedPermits == 0)
//                wait();
//
//            usedPermits--;
//            notify();
//        }
//    }
//}
