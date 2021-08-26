package challenge.aug21;

import java.util.HashMap;
import java.util.Map;

// Paint Fence
public class Q276 {
    // O(n) O(1)
    public int numWays(int n, int k) {
        int same = 0, diff = k;
        for(int i = 1; i < n; i++) {
            int nsame = diff;
            int ndiff = (same + diff) * (k - 1);
            same = nsame;
            diff = ndiff;
        }
        return same + diff;
    }
    // Top-Down : O(n) O(n)
    // when we calculating the total of i-index =>
    //  diff with previous => totalWay(i-1) * (k - 1)
    //          +
    //  same as previous
    //      => 1 * totalWay(i-1) but only different with i-2
    //      => i-1 is different with i-2 => totalWay(i-2) * (k -1)
    //  =>  (k-1) * (totalWay(i-1) + totalWay(i-2))
    Map<Integer, Integer> memo = new HashMap<>();
    public int numWays1(int n, int k) {
        return totalWay(n, k);
    }
    private int totalWay(int n, int k) {
        if(n == 1) return k;
        if(n == 2) return k * k;
        if(memo.containsKey(n)) return memo.get(n);

        int total = (k - 1) * (totalWay(n - 1, k) + totalWay(n - 2, k));
        memo.put(n, total);
        return total;
    }

    public int numWays2(int n, int k) {
        if(n == 1) return k;
        int a = k;
        int b = k * k;
        for(int i = 3; i <= n; i++) {
            int c = (k - 1) * (a + b);
            a = b;
            b = c;
        }
        return b;
    }
}
