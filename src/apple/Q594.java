package apple;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Longest Harmonious Subsequence
public class Q594 {
    // O(n^2) O(1)
    public int findLHS0(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            boolean flag = false;
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == nums[i])
                    count++;
                else if (nums[j] + 1 == nums[i]) {
                    count++;
                    flag = true;
                }
            }
            if (flag)
                res = Math.max(count, res);
        }
        return res;
    }
    // O(nlgn)
    // O(n) - worst case, O(lgn) - ave case.
    public int findLHS1(int[] nums) {
        if(nums == null || nums.length < 2) {
            return 0;
        }
        Arrays.sort(nums);
        int preCount = 1, count = 0, max = 0;
        for(int i = 0; i < nums.length; i++) {
            count = 1;
            if(i > 0 && nums[i] == nums[i - 1] + 1) {
                while(i < nums.length - 1 && nums[i] == nums[i + 1]) {
                    count++;
                    i++;
                }
                max = Math.max(max, count + preCount);
                preCount = count;
            } else {
                while(i < nums.length - 1 && nums[i] == nums[i + 1]) {
                    count++;
                    i++;
                }
                preCount = count;
            }
        }
        return max;
    }
    // two pass : O(n) O(n)
    public int findLHS2(int[] nums) {
        HashMap < Integer, Integer > map = new HashMap < > ();
        int res = 0;
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int key: map.keySet()) {
            if (map.containsKey(key + 1))
                res = Math.max(res, map.get(key) + map.get(key + 1));
        }
        return res;
    }
    // O(n) O(1)
    public int findLHS(int[] nums) {
        if(nums == null || nums.length < 2) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if(map.containsKey(num + 1)) {
                max = Math.max(max, map.get(num) + map.get(num + 1));
            }
            if(map.containsKey(num - 1)) {
                max = Math.max(max, map.get(num) + map.get(num - 1));
            }
        }
        return max;
    }
}
