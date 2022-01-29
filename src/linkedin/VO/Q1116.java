package linkedin.VO;
// Print Zero Even Odd
public class Q1116 {

//    class ZeroEvenOdd {
//        private int n;
//        Semaphore zeroSema, evenSema, oddSema;
//
//        public ZeroEvenOdd(int n) {
//            this.n = n;
//            this.zeroSema = new Semaphore(1);
//            this.evenSema = new Semaphore(0);
//            this.oddSema = new Semaphore(0);
//        }
//
//        // printNumber.accept(x) outputs "x", where x is an integer.
//        public void zero(IntConsumer printNumber) throws InterruptedException {
//            for(int x = 0; x < n; x++) {
//                zeroSema.acquire();
//                printNumber.accept(0);
//                if(x % 2 == 0) {
//                    oddSema.release();
//                } else {
//                    evenSema.release();
//                }
//            }
//        }
//
//        public void even(IntConsumer printNumber) throws InterruptedException {
//            for(int x = 2; x <= n; x += 2) {
//                evenSema.acquire();
//                printNumber.accept(x);
//                zeroSema.release();
//            }
//        }
//
//        public void odd(IntConsumer printNumber) throws InterruptedException {
//            for(int x = 1; x <= n; x += 2) {
//                oddSema.acquire();
//                printNumber.accept(x);
//                zeroSema.release();
//            }
//        }
//    }
}
