package linkedin.concurrent;

public class MultithreadedFizzBuzz {

    private int n;
    private int num;
    public MultithreadedFizzBuzz(int n) {
        this.n = n;
        this.num = 1;
    }

    public synchronized void fizz() throws InterruptedException{
        while(num <= n) {
            if(num % 3 == 0 && num % 5 != 0) {
                System.out.print("fizz");
                this.notifyAll();
                num++;
            } else {
                wait();
            }
        }
    }

    public synchronized void buzz() throws InterruptedException {
        while(num <= n) {
            if(num % 3 != 0 && num % 5 == 0) {
                System.out.print("buzz");
                notifyAll();
                num++;
            } else {
                wait();
            }
        }
    }

    public synchronized void fizzbuzz() throws InterruptedException {
        while (num <= n) {
            if (num % 15 == 0) {
                System.out.println("fizzbuzz");
                num++;
                notifyAll();
            }
            else {
                wait();
            }
        }
    }

    public synchronized void number() throws InterruptedException {
        while (num <= n) {
            if (num % 3 != 0 && num % 5 != 0) {
                System.out.println(num);
                num++;
                notifyAll();
            }
            else {
                wait();
            }
        }
    }
}
