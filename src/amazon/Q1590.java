package amazon;

import java.util.HashMap;
import java.util.Map;

// Make Sum Divisible by P
public class Q1590 {
    // O(n) O(n)
    public int minSubarray(int[] nums, int p) {
        int len = nums.length;
        long[] sum = new long[len + 1];
        for(int i = 1; i <= len; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        if(sum[len] % p == 0) {
            return 0;
        }

        Map<Long, Integer> map = new HashMap<>();
        map.put(0l, len + 1);
        int min = len;
        long total = 0; // sum from right side

        // sum(0,i) % p + sum(j, n) % p = p
        for(int i = len; i >= 0; i--) {  // we need to check i == 0 as we may need to remove first # elements: [8,32,31,18,34,20,21,13,1,27,23,22,11,15,30,4,2]  148 => 7
            long remainder = sum[i] % p;
            long target = remainder == 0 ? 0 : p - remainder;
            if(map.containsKey(target)) {
                min = Math.min(min, map.get(target) - i - 1);
            }
            if(i > 0) {
                total += nums[i - 1];
            }
            map.put(total % p, i);
        }
        return min == len ? -1 : min;
    }
}
