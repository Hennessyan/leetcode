package google;

import java.util.HashMap;
import java.util.Map;

// Arithmetic Slices II - Subsequence
public class Q446 {
    // https://leetcode.com/problems/arithmetic-slices-ii-subsequence/solution/ go through graph example
    // O(n^2) O(n^2)
    public int numberOfArithmeticSlices(int[] A) {
        if(A == null || A.length < 3) {
            return 0;
        }
        int n = A.length;
        int ans = 0;
        Map<Integer, Integer>[] cnt = new Map[n];
        for(int i = 0; i < n; i++) {    // start from 0, in order to init map.
            cnt[i] = new HashMap<>();
            for(int j = 0; j < i; j++) {
                long delta = (long)A[i] - (long)A[j];
                if (delta < Integer.MIN_VALUE || delta > Integer.MAX_VALUE) {
                    continue;
                }
                int diff = (int) delta;
                int sum = cnt[j].getOrDefault(diff, 0);     // this is the answer for current loop
                int origin = cnt[i].getOrDefault(diff, 0);  // need to use getOrDefault => [1,1,2]
                cnt[i].put(diff, origin + sum + 1);  // this can be used as answer for next round rather than this one,
                ans += sum;                          // because it adds the latest two pair result ([a[i], a[i-1]] => [4,5])
            }
        }
        return ans;
    }
}
