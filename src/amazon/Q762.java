package amazon;
// Prime Number of Set Bits in Binary Representation
public class Q762 {

    public static void main(String[] args) {
        Q762 q = new Q762();
        System.out.println(q.countPrimeSetBits(6, 10)); // 4
    }

    public int countPrimeSetBits(int L, int R) {
        int ans = 0;
        for(int i = L; i <= R; i++) {
            if(isPrime(count(i))) {
                ans++;
            }
        }
        return ans;
    }
    private int count(int num) {
        int c = 0;
        while(num != 0) {
            c++;
            num = num & (num - 1);
        }
        return c;
    }
    private boolean isPrime(int n) {
        return (n == 2) || (n == 3) || (n == 5) || (n == 7) ||
                (n == 11) || (n == 13) || (n == 17) || (n == 19);
    }

    private boolean isPrime1(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        for (int i = 2; i <= (int)Math.sqrt(n); ++i)
            if (n % i == 0) return false;
        return true;
    }
}
