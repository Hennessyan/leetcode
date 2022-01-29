package linkedin.concurrent;

import java.util.concurrent.Semaphore;

// follow-up : Try writing a solution in which there's no possibility of starvation for threads of either gender.
public class UnisexBathroom {

    static String MEN = "men";
    static String WOMEN = "women";
    static String NONE = "none";

    String inUseBy = NONE;
    int empsInBathroom = 0;
    Semaphore maxEmps = new Semaphore(3);

    void useBathroom(String name) throws InterruptedException {
        System.out.println(name + " using bathroom. Current employees in bathroom = " + empsInBathroom);
        Thread.sleep(3000);
        System.out.println(name + " done using bathroom");
    }

    void maleUseBathroom(String name) throws InterruptedException {

        synchronized (this) {
            while (inUseBy.equals(WOMEN)) { // while (inUseBy.equals(WOMEN) || empsInBathroom == 3) avoid starvation??
                this.wait();
            }
            maxEmps.acquire();  // L27 can move to L31, but either case we may get the max empsInBathroom as 4.
            empsInBathroom++;
            inUseBy = MEN;
        }

        useBathroom(name);
        maxEmps.release();

        synchronized (this) {
            empsInBathroom--;

            if (empsInBathroom == 0) inUseBy = NONE;
            this.notifyAll();
        }
    }

    void femaleUseBathroom(String name) throws InterruptedException {

        synchronized (this) {
            while (inUseBy.equals(MEN)) {
                this.wait();
            }
            maxEmps.acquire();
            empsInBathroom++;
            inUseBy = WOMEN;
        }

        useBathroom(name);
        maxEmps.release();

        synchronized (this) {
            empsInBathroom--;

            if (empsInBathroom == 0) inUseBy = NONE;
            this.notifyAll();
        }
    }
}
