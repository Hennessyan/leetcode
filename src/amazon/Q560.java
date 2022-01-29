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
    // O(n^2) O(1)
    public int subarraySum1(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum=0;
            for (int end = start; end < nums.length; end++) {
                sum+=nums[end];
                if (sum == k)
                    count++;
            }
        }
        return count;
    }
}
