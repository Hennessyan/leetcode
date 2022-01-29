package linkedin.tmp;

abstract class TokenBucketFilter {
    public void getToken() throws InterruptedException {}
}

/*
    simple factory pattern
    The problem with the above solution is that we start our thread in the constructor.
    Never start a thread in a constructor as the child thread can attempt to use the not-yet-fully
    constructed object using this. This is an anti-pattern.
 */

public final class TokenBucketFilterFactory {

    // Force users to interact with the factory
    // only through the static methods
    private TokenBucketFilterFactory() {
    }

     public static TokenBucketFilter makeTokenBucketFilter(int capacity) {
        MultithreadedTokenBucketFilter tbf = new MultithreadedTokenBucketFilter(capacity);
        tbf.initialize();
        return tbf;
    }

    private static class MultithreadedTokenBucketFilter extends TokenBucketFilter {
        private int MAX_TOKENS;
        private long possibletokens = 0;
        private int SECOND = 1000;

        MultithreadedTokenBucketFilter(int max) {
            this.MAX_TOKENS = max;
        }

        void initialize() {
            Thread thread = new Thread(() -> {
                daemonThread();
            });
            // mark the daemon thread as background so that it exits when the application terminates.
            thread.setDaemon(true);
            thread.start();
        }
        private void daemonThread() {
            while(true) {
                synchronized(this) {
                    if(possibletokens < MAX_TOKENS) {
                        possibletokens++;
                    }
                    this.notify();
                }
                try {
                    Thread.sleep(SECOND);
                } catch (InterruptedException e) {}
            }
        }
        public void getToken() throws InterruptedException {
            synchronized(this) {
                while(possibletokens == 0) {
                    this.wait();
                }
                possibletokens--;
            }
        }
    }
}
