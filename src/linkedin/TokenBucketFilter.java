package linkedin;
// Uber asked in Interview
public class TokenBucketFilter {
    private int MAX_TOKENS;
    private long lastRequestTime = System.currentTimeMillis();
    long possibleTokens = 0;

    public TokenBucketFilter(int max) {
        MAX_TOKENS = max;
    }

    synchronized void getToken() throws InterruptedException {
        possibleTokens += (System.currentTimeMillis() - lastRequestTime) / 1000;

        if (possibleTokens > MAX_TOKENS) {
            possibleTokens = MAX_TOKENS;
        }

        if (possibleTokens == 0) {
            Thread.sleep(1000);
        } else {
            possibleTokens--;
        }
        lastRequestTime = System.currentTimeMillis();
    }

    /**
     * Follow-up:
     * Grant tokens to threads in a FIFO order
     * Generalize the solution for any rate of token generation
     */
}
