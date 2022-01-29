package linkedin.concurrent;

import java.util.concurrent.CountDownLatch;
// Q1114
public class OrderedPrinting {
    int count;
    Object object = new Object();
    public OrderedPrinting() {
        count = 1;
    }

    public void printFirst() throws InterruptedException {
        synchronized(this){
            System.out.println("First");
            count++;
            this.notifyAll();
        }
    }

    public void printSecond() throws InterruptedException {
        synchronized(this){
            while(count != 2){
                this.wait();
            }
            System.out.println("Second");
            count++;
            this.notifyAll();
        }
    }

    public void printThird() throws InterruptedException {
        synchronized(this){
            while(count != 3){
                this.wait();
            }
            System.out.println("Third");
        }
    }
}
class OrderedPrinting1 {
    CountDownLatch l1 = new CountDownLatch(1);
    CountDownLatch l2 = new CountDownLatch(1);

    public void printFirst() {
        System.out.println("First");
        l1.countDown();
    }

    public void printSecond() throws InterruptedException {
        l1.await();
        System.out.println("Second");
        l2.countDown();
    }

    public void printThird() throws InterruptedException {
        l2.await();
        System.out.println("Third");
    }
}