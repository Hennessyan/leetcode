package linkedin.VO;
// Building H2O
public class Q1117 {

    class H2O {
        private Object object;
        private int count;
        // random order
//        int c = (int) (Math.random() * 3);
        public H2O() {
            this.object = new Object();
            this.count = 0;
        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            synchronized(object) {
//                while(c % 3 == 0) {
//                    object.wait();
//                }
//                c++;
                while(count == 2) {
                    object.wait();
                }
                count++;
                // releaseHydrogen.run() outputs "H". Do not change or remove this line.
                releaseHydrogen.run();
                object.notifyAll();
            }
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            synchronized(object) {
//                while(c % 3 != 0) {
//                    object.wait();
//                }
//                c = 0;
//                c++;
                while(count != 2) {
                    object.wait();
                }
                count = 0;
                // releaseOxygen.run() outputs "O". Do not change or remove this line.
                releaseOxygen.run();
                object.notifyAll();
            }
        }
    }
}
