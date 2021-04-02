package amazon;
// Concatenation of Consecutive Binary Numbers
public class Q1680 {
    // O(nlgn) O(n)
    public int concatenatedBinary1(int n) {
        int mod = (int)1e9 + 7;
        int res = 0;
        for(int i = 1; i <= n; i++) {
            String binary = Integer.toBinaryString(i);  // lgn
            for(int j = 0; j < binary.length(); j++) {
                res = ((res << 1) | (binary.charAt(j) - '0')) % mod;    // 这里是一位一位的取余,所以不会OVERFLOW
            }
        }
        return res;
    }

    public int concatenatedBinary2(int n) {
        final int MOD = 1000000007;
        int length = 0; // bit length of addends
        long result = 0; // long accumulator
        for (int i = 1; i <= n; i++) {
            // when meets power of 2, increase the bit length
            if (Math.pow(2, (int) (Math.log(i) / Math.log(2))) == i) {  //(int)必须有
                length++;
            }
            result = ((result * (int) Math.pow(2, length)) + i) % MOD;
        }
        return (int) result;
    }

    // O(n) O(1)
    public int concatenatedBinary(int n) {
        int mod = (int)1e9 + 7;
        long res = 0;
        int length = 0;
        for(int i = 1; i <= n; i++) {
            if((i & (i - 1)) == 0) {
                length++;
            }
            res = ((res << length) + i) % mod;  //一次会移多位，所以得用LONG
        }
        return (int)res;
    }
}
