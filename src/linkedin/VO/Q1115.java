package linkedin.VO;

import java.util.concurrent.Semaphore;

// Print FooBar Alternately
// https://leetcode.com/problems/print-foobar-alternately/discuss/348546/Java-4-Java-threading-solutions-(SynchronizedLockVolatileCAS)
public class Q1115 {
    class FooBar {
        private int n;
        private Semaphore foosema, barsema;
        public FooBar(int n) {
            this.n = n;
            this.foosema = new Semaphore(1);
            this.barsema = new Semaphore(0);
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                foosema.acquire();
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                barsema.release();
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                barsema.acquire();
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                foosema.release();
            }
        }
    }

//    class FooBar {
//        private int n, flag;
//        private Lock lock;
//        private Condition fc, bc;
//        public FooBar(int n) {
//            this.n = n;
//            this.flag = 0;
//            this.lock = new ReentrantLock();
//            this.fc = lock.newCondition();
//            this.bc = lock.newCondition();
//        }
//
//        public void foo(Runnable printFoo) throws InterruptedException {
//
//            for (int i = 0; i < n; i++) {
//                lock.lock();
//                try {
//                    while(flag == 1) {
//                        fc.await();
//                    }
//                    flag = 1;
//                    // printFoo.run() outputs "foo". Do not change or remove this line.
//                    printFoo.run();
//                    bc.signal();
//                } finally {
//                    lock.unlock();
//                }
//            }
//        }
//
//        public void bar(Runnable printBar) throws InterruptedException {
//
//            for (int i = 0; i < n; i++) {
//
//                lock.lock();
//                try {
//                    while(flag == 0) {
//                        bc.await();
//                    }
//                    flag = 0;
//                    // printFoo.run() outputs "foo". Do not change or remove this line.
//                    printBar.run();
//                    fc.signal();
//                } finally {
//                    lock.unlock();
//                }
//            }
//        }
//    }

}
