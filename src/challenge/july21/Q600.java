package challenge.july21;
// Non-negative Integers without Consecutive Ones
public class Q600 {
    // https://leetcode.com/problems/non-negative-integers-without-consecutive-ones/
    // dp -> need check the example in last solution.f
    // O(32) O(32)
    public class Solution {
        public int findIntegers(int num) {
            // f[n] = f[n - 1] + f[n - 2]
            // xxxx    xxx0        xx01
            int[] f = new int[32];
            f[0] = 1;       // 0
            f[1] = 2;       // 00 01
            for (int i = 2; i < f.length; i++)
                f[i] = f[i - 1] + f[i - 2];
            int i = 30, sum = 0, prev_bit = 0;
            while (i >= 0) {
                if ((num & (1 << i)) != 0) {
                    sum += f[i];
                    if (prev_bit == 1) {
                        sum--;      // if two consecutive 1, we will not consider num itself, so deduct 1 before last adding in L29.
                        break;
                    }
                    prev_bit = 1;
                } else
                    prev_bit = 0;
                i--;
            }
            // does not consider value itself, so we need to add 1
            return sum + 1;
        }
    }
    // TLE : O(x) O(lg(max_int))=O(32)
    // => x = result
    public int findIntegers(int n) {
        return find(0, 0, n, false);
    }
    private int find(int i, int sum, int n, boolean prev) {
        if(sum > n) return 0;
        if(1 << i > n) return 1;
        if(prev) {
            return find(i + 1, sum, n, false);
        }
        return find(i + 1, sum, n, false) + find(i + 1, sum + (1 << i), n, true);
    }
}
