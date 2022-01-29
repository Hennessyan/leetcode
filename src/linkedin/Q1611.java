package linkedin;
// Minimum One Bit Operations to Make Integers Zero
public class Q1611 {

    // 1 -> 1 = 1
    // 2 -> 3 = 2 + 1
    // 4 -> 7 = 4 + 2 + 1
    // 2^k needs 2^(k+1) - 1 operations
    // 1xxxxxx - > 1100000 -> 100000 -> 0

    // O(lgn) O(1)
    public int minimumOneBitOperations(int n) {
        int ans = 0;
        while(n > 0) {
            int b = 1;
            while((b << 1) <= n) {
                b <<= 1;
            }
            ans += b;
            n = n ^ b ^ (b >> 1); // 1xxxxxx - > 1100000 => xxxxxx -> 100000
        }
        return ans;
    }
}
