package challenge.jan21;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Max Number of K-Sum Pairs
public class Q1679 {

    public static void main(String[] args) {
        Q1679 q = new Q1679();
        int[] nums = {3,1,3,4,3};
        System.out.println(q.maxOperations(nums, 6));       // 1
    }
    // https://leetcode.com/problems/max-number-of-k-sum-pairs/solution/
    // O(n) O(n)
    public int maxOperations(int[] nums, int k) {
        if(nums == null || nums.length < 2) {
            return 0;
        }
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums) {
            int remainder = k - num;
            if(map.containsKey(remainder)) {
                res++;
                int c = map.get(remainder);
                if(--c == 0) {
                    map.remove(remainder);
                } else {
                    map.put(remainder, c);
                }
            } else {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }
        return res;
    }
    // O(nlgn) O(lgn)
    public int maxOperations0(int[] nums, int k) {
        Arrays.sort(nums);      // variant of quick sort, SC is O(lgn)
        int count = 0;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] < k) {
                left++;
            } else if (nums[left] + nums[right] > k) {
                right--;
            } else {
                left++;
                right--;
                count++;
            }
        }
        return count;
    }

    // 1 <= nums[i] <= 100000
    // O(n^2) O(1)
    public int maxOperations1(int[] nums, int k) {
        int count = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                continue;
            }
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[j] == 0) {
                    continue;
                }
                if(nums[i] + nums[j] == k) {
                    nums[i] = nums[j] = 0;
                    count++;
                    break;
                }
            }
        }
        return count;
    }
}
