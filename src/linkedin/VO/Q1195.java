package linkedin.VO;
// Fizz Buzz Multithreaded
public class Q1195 {

//    class FizzBuzz {
//        private int n;
//        private int i;
//        public FizzBuzz(int n) {
//            this.n = n;
//            this.i = 1;
//        }
//
//        // printFizz.run() outputs "fizz".
//        public void fizz(Runnable printFizz) throws InterruptedException {
//            synchronized(this) {
//                while(i <= n) {
//                    if(i % 3 == 0 && i % 5 != 0) {
//                        printFizz.run();
//                        this.notifyAll();
//                        i++;
//                    } else {
//                        this.wait();
//                    }
//                }
//            }
//        }
//
//        // printBuzz.run() outputs "buzz".
//        public void buzz(Runnable printBuzz) throws InterruptedException {
//            synchronized(this) {
//                while(i <= n) {
//                    if(i % 5 == 0 && i % 3 != 0) {
//                        printBuzz.run();
//                        this.notifyAll();
//                        i++;
//                    } else {
//                        this.wait();
//                    }
//                }
//            }
//        }
//
//        // printFizzBuzz.run() outputs "fizzbuzz".
//        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
//            synchronized(this) {
//                while(i <= n) {
//                    if(i % 15 == 0) {
//                        printFizzBuzz.run();
//                        this.notifyAll();
//                        i++;
//                    } else {
//                        this.wait();
//                    }
//                }
//            }
//        }
//
//        // printNumber.accept(x) outputs "x", where x is an integer.
//        public void number(IntConsumer printNumber) throws InterruptedException {
//            synchronized(this) {
//                while(i <= n) {
//                    if(i % 5 != 0 && i % 3 != 0) {
//                        printNumber.accept(i);
//                        this.notifyAll();
//                        i++;
//                    } else {
//                        this.wait();
//                    }
//                }
//            }
//        }
//    }
}
