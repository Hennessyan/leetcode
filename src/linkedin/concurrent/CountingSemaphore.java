package linkedin.concurrent;

/*
Java does provide its own implementation of Semaphore, however,
Java's semaphore is initialized with an initial number of permits,
rather than the maximum possible permits and the developer is expected
to take care of always releasing the intended number of maximum permits.
 */
public class CountingSemaphore {

    int usedPermits = 0; // permits given out
    int maxCount; // max permits to give out

    public CountingSemaphore(int count) {
        this.maxCount = maxCount;
    }

    public synchronized void acquire() throws InterruptedException {
        while(usedPermits == maxCount) {
            this.wait();
        }
        usedPermits++;
        this.notify();
    }

    public synchronized void release() throws InterruptedException {
        while(usedPermits == 0) {
            this.wait();
        }
        this.notify();
        usedPermits++;
    }
}
// follow-up: Does it matter if we change the order of L22-23, L30-31 ? =>
// No. Because critical section still synchronized on the semaphore, thread waits until release() / acquire() is done.