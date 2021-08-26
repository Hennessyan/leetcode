package amazon;
// Fibonacci Number
public class Q509 {

    // method1 : recursion: O(2^n) O(n)
    public int fib(int N) {
        if (N <= 1) {
            return N;
        }
        return fib(N-1) + fib(N-2);
    }
    // bottom up: O(n) O(n)
    public int fib1(int N) {
        if (N <= 1) {
            return N;
        }
        return memoize(N);
    }

    public int memoize(int N) {
        int[] cache = new int[N + 1];
        cache[1] = 1;

        for (int i = 2; i <= N; i++) {
            cache[i] = cache[i-1] + cache[i-2];
        }
        return cache[N];
    }

    //top-down
    public int fib2(int n) {
        if(n < 2) {
            return n;
        }
        Integer[] memo = new Integer[n + 1];
        return fib(n, memo);
    }
    private int fib(int n, Integer[] memo) {
        if(n < 2) {
            return n;
        }
        if(memo[n] != null) {
            return memo[n];
        }
        memo[n] = fib(n - 1) + fib(n - 2);
        return memo[n];
    }

    // O(n) O(1)
    public int fib3(int n) {
        if(n < 2) {
            return n;
        }
        int a = 0, b = 1;
        for(int i = 2; i <= n; i++) {
            int c = a + b;
            a = b;
            b =c;
        }
        return b;
    }
}
