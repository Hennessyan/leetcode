package linkedin.concurrent;

public class ReadWriteLock {
    boolean isWriteLocked = false;
    int readers = 0;

    public synchronized void acquireReadLock() throws InterruptedException {
        while(isWriteLocked) {
            this.wait();
        }
        readers++;
    }

    public synchronized void releaseReadLock() {
        readers--;
        this.notify();
    }

    public synchronized void acquireWriteLock() throws InterruptedException {
        while(isWriteLocked || readers != 0) {
            this.wait();
        }
        isWriteLocked = true;
    }

    public synchronized void releaseWriteLock() {
        isWriteLocked = false;
        notify();
    }
}
