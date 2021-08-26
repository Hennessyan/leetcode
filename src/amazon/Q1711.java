package amazon;

import java.util.HashMap;
import java.util.Map;

// Count Good Meals
// Q1
public class Q1711 {
    // two pointers : O(n) O(n)
    public int countPairs(int[] deliciousness) {
        Map<Integer, Integer> map = new HashMap<>();
        long ans = 0;
        int mod = (int) (1e9 + 7);
        for(int d : deliciousness) {
            int sum = 1;
            for(int i = 0; i <= 21; i++) { // 0 <= val <= 2^20 => upper_bound of sum is 2^21
                int key = sum - d;
                ans += map.getOrDefault(key, 0);
                sum *= 2;
            }
            map.put(d, map.getOrDefault(d, 0) + 1);
        }
        return (int) (ans % mod);
    }
}
