package challenge.june;

import java.util.Random;

// Random Pick with Weight
public class Q528 {
    class Solution {
        int[] sum;
        int total;
        Random random;

        public Solution(int[] w) {
            int n = w.length;
            sum = new int[n];
            for (int i = 0; i < n; i++) {
                total += w[i];
                sum[i] = total;
            }
            random = new Random();
        }

        //注意BINARY SEARCH的边界.
        public int pickIndex() {
            int val = random.nextInt(total) + 1;
            int l = 0, r = sum.length - 1;
            while (l <= r) {
                int m = l + (r - l) / 2;
                if (sum[m] >= val) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            return l;
        }
    }
}
