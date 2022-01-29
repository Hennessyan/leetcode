package linkedin.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class BarberShopProblem {

    final int CHAIRS = 3;
    int hairCutsGiven = 0;
    int waitingCustomers = 0;
    ReentrantLock lock = new ReentrantLock();
    Semaphore waitForCustomerToEnter =  new Semaphore(0);
    Semaphore waitForBarberToGetReady =  new Semaphore(0);
    Semaphore waitForBarberToCutHair =  new Semaphore(0);
    Semaphore waitForCustomerToLeave =  new Semaphore(0);

    void customerWalksIn() throws InterruptedException {
        lock.lock();
        if(waitingCustomers == CHAIRS) {
            System.out.println("Customer walks out, all chairs occupied.");
            // Remember to unlock before leaving
            lock.unlock();
            return;
        }
        waitingCustomers++;
        lock.unlock();
        waitForCustomerToEnter.release();
        waitForBarberToGetReady.acquire();

        // If move L39-l41 here: the chair in the waiting area becomes available =>
        // Three threads wait on chairs in the waiting area and one thread occupies
        // the barber chair where it undergoes a hair-cut.
        // lock.lock();
        // waitingCustomers--;
        // lock.unlock();

        waitForBarberToCutHair.acquire();
        waitForCustomerToLeave.release();

        lock.lock();
        waitingCustomers--;
        lock.unlock();
    }

    void barber() throws InterruptedException {

        while(true) {
            waitForCustomerToEnter.acquire();
            waitForBarberToGetReady.release();

            hairCutsGiven++;
            System.out.println("Barber cutting hair..." + hairCutsGiven);
            Thread.sleep(50);
            waitForBarberToCutHair.release();
            waitForCustomerToLeave.acquire();
        }
    }
}
