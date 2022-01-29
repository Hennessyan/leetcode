package linkedin.concurrent;

import java.util.concurrent.Semaphore;

/*
We were told to use three threads in the problem statement but the
solution can be achieved using two threads as well. Since zero is printed before every number,
we do not need to dedicate a special thread for it.
We can simply print a zero before printing every odd or even number.
 */
public class PrintNumberSeries {

    private int n;
    private Semaphore zeroSem, oddSem, evenSem;

    public PrintNumberSeries(int n) {
        this.n = n;
        zeroSem = new Semaphore(1);
        oddSem = new Semaphore(0);
        evenSem = new Semaphore(0);
    }

    public void PrintZero() throws InterruptedException {
        for(int i = 0; i < n; i++) {
            zeroSem.acquire();
            System.out.println("0");
            if(i % 2 == 0) {
                oddSem.release();
            } else {
                evenSem.release();
            }
        }
    }

    public void PrintOdd() throws InterruptedException {
        for(int i = 1; i <= n; i += 2) {
            oddSem.acquire();
            System.out.println(i);
            zeroSem.release();
        }
    }

    public void PrintEven() throws InterruptedException {
        for(int i = 2; i <= n; i += 2) {
            evenSem.acquire();
            System.out.println(i);
            zeroSem.release();
        }
    }
}
