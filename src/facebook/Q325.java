package facebook;

import java.util.HashMap;
import java.util.Map;

// Maximum Size Subarray Sum Equals k
public class Q325 {
    // O(n) O(n)
    public int maxSubArrayLen(int[] nums, int k) {
        int sum = 0, max = 0, n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for(int i = 0; i < n; i++) {
            sum += nums[i];
            int target = sum - k;
            if(map.containsKey(target)) {
                max = Math.max(max, i - map.get(target));
            }
            map.putIfAbsent(sum, i);        // 注意这里!
        }
        return max;
    }
}
