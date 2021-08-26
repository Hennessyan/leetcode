package amazon;

import java.util.HashMap;
import java.util.Map;

// Subarray Sum Equals K
// Q1074(H)
public class Q560 {
    // O(n) O(n)
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0, total = 0;
        for(int num : nums) {
            total += num;
            if(map.containsKey(total - k)) {
                sum += map.get(total - k);
            }
            map.put(total, map.getOrDefault(total, 0) + 1);
        }
        return sum;
    }
}
