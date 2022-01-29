package linkedin.concurrent;

/*
    follow-up: we uses notifyAll() rather than notify() because below scenario:
    2 producers (P1, P2) and 1 consumer (C1).
    If P1 added item into the queue and it's full. then we can notify() which wake up the P2.
    P2 find queue is full, then wait for the consume. => deadlock !!!
 */
public class BlockingQueue<T> {
    private T[] arr;
    private int capacity, size;
    private int head, tail;
    // Each object in Java has an entity associated with it called the "monitor lock" or just monitor.
    // Once a thread gets hold of the monitor of an object, it has exclusive access to all the methods marked as synchronized
    private Object lock;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        arr = (T[]) new Object[capacity];
        this.head = 0;
        this.tail = 0;
        this.lock = new Object();
    }

    public void enqueue(T item) throws InterruptedException {
        synchronized(lock) {
            while(this.size == this.capacity) {
                lock.wait();
            }
            if(tail == capacity) tail = 0;
            arr[tail] = item;
            tail++;
            size++;

            lock.notifyAll();
        }
    }

    public T dequeue() throws InterruptedException {
        T item;
        synchronized(lock) {
            while(this.size == 0) {
                lock.wait();
            }
            if(head == capacity) {
                head = 0;
            }
            item = arr[head];
            arr[head++] = null;

            size--;
            lock.notifyAll();
        }
        return item;
    }
}
class Demo {

    public static void main(String[] args) throws InterruptedException {
        final BlockingQueue<Integer> queue = new BlockingQueue<>(5);
        Thread t1 = new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   for (int i = 0; i < 50; i++) {
                       queue.enqueue(i);
                       System.out.println("enqueing: " + i);
                   }
               } catch(InterruptedException e) {

               }
           }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i = 0; i < 25; i++) {
                        System.out.println("Thread2 dequeing: " + queue.dequeue());
                    }
                } catch(InterruptedException e) {}
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i = 0; i < 25; i++) {
                        System.out.println("Thread3 dequeing: " + queue.dequeue());
                    }
                } catch(InterruptedException e) {}
            }
        });

        t1.start();
        Thread.sleep(4000);
        t2.start();

        t2.join();

        t3.start();
        t1.join();
        t3.join();
    }
}