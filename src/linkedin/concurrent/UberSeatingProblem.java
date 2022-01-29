package linkedin.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class UberSeatingProblem {

    private int republicans = 0;
    private int democrats = 0;

    private Semaphore demsWaiting = new Semaphore(0);
    private Semaphore repubsWaiting = new Semaphore(0);

    ReentrantLock lock = new ReentrantLock();
    CyclicBarrier cb = new CyclicBarrier(4);

    void seatDemocrat() throws InterruptedException {
        boolean rideLeader = false;
        lock.lock();
        democrats++;

        if(democrats == 4) {
            demsWaiting.release(3);
            democrats -= 4;
            rideLeader = true;
        } else if(democrats == 2 && republicans >= 2) {
            demsWaiting.release(1);
            repubsWaiting.release(2);
            rideLeader = true;
            democrats -= 2;
            republicans -= 2;
        } else {
            demsWaiting.acquire();
            lock.unlock();
        }
        seated();
        cb.await();

        if(rideLeader) {
            drive();
            lock.unlock();
        }
    }

    void seatRepublican() throws InterruptedException {
        lock.lock();
        boolean rideLeader = false;
        republicans++;

        if(republicans == 4) {
            repubsWaiting.release(3);
            republicans -= 4;
            rideLeader = true;
        } else if(republicans == 2 && democrats >= 2) {
            repubsWaiting.release(1);
            demsWaiting.release(2);
            republicans -= 2;
            democrats -= 2;
            rideLeader = true;
        } else {
            repubsWaiting.acquire();
            lock.unlock();
        }
        seated();
        cb.await();

        if(rideLeader) {
            drive();
            lock.unlock();
        }
    }

    void seated() {
        System.out.println(Thread.currentThread().getName() + "  seated");
        System.out.flush();
    }

    void drive() {
        System.out.println("Uber Ride on Its wayyyy... with ride leader " + Thread.currentThread().getName());
        System.out.flush();
    }
}

class CyclicBarrier {

    int count, threshold, released;

    public CyclicBarrier(int max) {
        this.count = 0;
        this.threshold = max;
        this.released = 0;
    }

    public synchronized void await() throws InterruptedException {
        while(count == threshold) {
            this.wait();
        }

        count++;

        if(count == threshold) {
            released = threshold;
            this.notifyAll();
        } else {
            while(count < threshold) {
                this.wait();
            }
        }
        released--;
        if(released == 0) {
            count = 0;
            this.notifyAll();
        }
    }
}