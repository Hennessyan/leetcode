package amazon;

import java.util.Arrays;

// Count Primes
public class Q204 {
    // Sieve of Eratosthenes
    // O(n) ? O(n) -> O(sqrt(n)*lglgn + n) O(n)
    public int countPrimes(int n) {
        if(n < 2) return 0;
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        // Loop's ending condition is i * i < n instead of i < sqrt(n)
        // to avoid repeatedly calling an expensive function sqrt().
        for(int i = 2; i * i < n; i++) {    // don't need <= n
            if(!isPrime[i]) {
                continue;
            }
            for(int j = i * i; j < n; j += i) {
                isPrime[j] = false;
            }
        }
        int count = 0;
        for(boolean p : isPrime) {
            if(p) count++;
        }
        return count;
    }

    public int countPrimes1(int n) {
        if(n < 2) return 0;
        boolean[] p = new boolean[n];
        for(int i = 2; i * i < n; i++) {
            if(p[i]) continue;
            for(int j = i * i; j < n; j += i) {
                p[j] = true;
            }
        }
        int count = 0;
        for(int i = 2; i < n; i++) {
            if(!p[i]) count++;
        }
        return count;
    }
    // method2
    public int countPrimes2(int n) {
		if(n <= 2) return 0;

        boolean[] flags = new boolean[n];
        for(int i = 2; i < n; i++)
        	flags[i] = true;

        int sqrt = (int)Math.sqrt(n);
        int prime = 2;
        while(prime <= sqrt){
        	crossOff(flags, prime);
        	prime = getNextPrime(flags, prime);
        }
        int count = 0;
        for(boolean flag : flags)
        	if(flag) count++;
        return count;
    }
	private void crossOff(boolean[] flags, int prime){
		for(int i = prime*prime; i < flags.length; i += prime)
			flags[i] = false;
	}
	private int getNextPrime(boolean[] flags, int prime){
		prime++;
		while(prime < flags.length && !flags[prime])
			prime++;
		return prime;
	}
}
