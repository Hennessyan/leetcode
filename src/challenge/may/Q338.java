package challenge.may;

import java.util.Arrays;

// Counting Bits
public class Q338 {

    public static void main(String[] args) {
        Q338 q = new Q338();
        System.out.println(Arrays.toString(q.countBits(5)));// [0,1,1,2,1,2]
    }
    //O(n) O(n)
    //x=(1001011101)_2 = (605)_10
    //x' = (100101110)_2 = (302)_10
    public int[] countBits(int num) {
        int[] dp = new int[num + 1];
        for(int i = 1; i <= num; i++) {
            dp[i] = dp[i >> 1] + (i & 1);
        }
        return dp;
    }

    public int[] countBits1(int num) {
        int[] ans = new int[num + 1];
        for (int i = 1; i <= num; ++i)
            ans[i] = ans[i & (i - 1)] + 1;
        return ans;
    }

    // O(nk) O(n)
    public int[] countBits0(int num) {
        int[] ans = new int[num + 1];
        for (int i = 0; i <= num; ++i)
            ans[i] = popcount(i);
        return ans;
    }
    private int popcount(int x) {
        int count;
        for (count = 0; x != 0; ++count)
            x &= x - 1; //zeroing out the least significant nonzero bit
        return count;
    }
}
