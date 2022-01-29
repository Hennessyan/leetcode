package linkedin.concurrent;

// mark the daemon thread as background so that it exits when the application terminates.
// thread.setDaemon(true)
public class MultithreadedTokenBucketFilter {

    private long possibleTokens = 0;
    private int MAX_TOKENS;
    private final int ONE_SECOND = 1000;

    /*  https://www.educative.io/courses/java-multithreading-for-senior-engineering-interviews/3jywK8qNGP4
        The problem with the above solution is that we start our thread in the constructor.
        Never start a thread in a constructor as the child thread can attempt to use the not-yet-fully
        constructed object using this. This is an anti-pattern.
     */
    public MultithreadedTokenBucketFilter(int max) {
        MAX_TOKENS = max;
        Thread thread = new Thread(() -> {
            daemonThread();
        });
        thread.setDaemon(true);
        thread.start();
    }

    private void daemonThread() {
        while(true) {
            synchronized(this) {
                if(possibleTokens < MAX_TOKENS) {
                    possibleTokens++;
                }
                this.notify();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
            }
        }
    }

    public void getToken() throws InterruptedException {
        synchronized(this) {
            while(possibleTokens == 0) {
                this.wait();
            }
            possibleTokens--;
        }
    }
}
