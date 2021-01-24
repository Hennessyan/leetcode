package apple;

import java.util.HashMap;
import java.util.Map;

// Two Sum
public class Q1 {

    // O(n) O(n)
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            int remain = target - nums[i];
            if(map.containsKey(remain)) {
                return new int[]{map.get(remain), i};
            }
            map.put(remain, i);
        }
        throw new IllegalArgumentException("no two sum");
    }
}
