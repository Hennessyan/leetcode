package linkedin.concurrent;

import java.util.Random;
import java.util.concurrent.Semaphore;
// Q1226
public class DiningPhilosophers {

    // This random variable is used for test purpose only
    private static Random random = new Random(System.currentTimeMillis());
    // Five semaphore represent the five forks.
    private Semaphore[] forks = new Semaphore[5];
    private Semaphore maxDiners = new Semaphore(4);

    // Initializing the semaphores with a permit of 1
    public DiningPhilosophers() {
        forks[0] = new Semaphore(1);
        forks[1] = new Semaphore(1);
        forks[2] = new Semaphore(1);
        forks[3] = new Semaphore(1);
        forks[4] = new Semaphore(1);
    }

    // Represents how a philosopher lives his life
    public void lifecycleOfPhilosopher(int id) throws InterruptedException {

        while (true) {
            contemplate();
            eat(id);
        }
    }

    // We can sleep the thread when the philosopher is thinking
    void contemplate() throws InterruptedException {
        Thread.sleep(random.nextInt(500));
    }

    // This method will have the meat of the solution, where the
    // philosopher is trying to eat.
    // A very simple fix is to allow only four philosophers at any given point in time to even try to acquire forks.
    void eat(int id) throws InterruptedException {
        // maxDiners allows only 4 philosphers to
        // attempt picking up forks.
        maxDiners.acquire();

        forks[id].acquire();
        forks[(id + 1) % 5].acquire();
        System.out.println("Philosopher " + id + " is eating");
        forks[id].release();
        forks[(id + 1) % 5].release();

        maxDiners.release();
    }

    // Ordering of fork pick-up (1 left-handed, 4 right-handed first)
    void eat1(int id) throws InterruptedException {
        if(id == 3) {
            acquireForkLeftHanded(id);
        } else {
            acquireForkForRightHanded(id);
        }
        System.out.println("Philosopher " + id + " is eating");
        forks[id].release();
        forks[(id + 1) % 5].release();
    }

    void acquireForkForRightHanded(int id) throws InterruptedException {
        forks[id].acquire();
        forks[(id + 1) % 5].acquire();
    }

    // Left-handed philosopher picks the left fork first and then
    // the right one.
    void acquireForkLeftHanded(int id) throws InterruptedException {
        forks[(id + 1) % 5].acquire();
        forks[id].acquire();
    }
}
