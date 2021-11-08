package amazon;

import java.util.HashMap;
import java.util.Map;

// 4Sum II
public class Q454 {
    //brute force - O(n^4) => for loop A+B+C & hashMap - O(n^3) => A+B & C+D & hashMap - O(n^2)

    // O(n^2) O(n^2)
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int a : A) {
            for(int b : B) {
                map.put(a +b, map.getOrDefault(a+b, 0) + 1);
            }
        }
        int count = 0;
        for(int c : C) {
            for(int d : D) {
                count += map.getOrDefault(-(c+d), 0);
            }
        }
        return count;
    }
    // O(n^(k/2)) O(n^(k/2))
    public int fourSumCount1(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        return kSumCount(new int[][]{nums1, nums2, nums3, nums4});
    }
    private int kSumCount(int[][] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        addToMap(nums, map, 0, 0);
        return count(nums, map, nums.length / 2, 0);
    }
    private void addToMap(int[][] nums, Map<Integer, Integer> map, int start, int sum) {
        if(start == nums.length / 2) {
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        } else {
            for(int val : nums[start]) {
                addToMap(nums, map, start + 1, sum + val);
            }
        }
    }
    private int count(int[][] nums, Map<Integer, Integer> map, int start, int sum) {
        if(start == nums.length) {
            return map.getOrDefault(sum, 0);
        }
        int count = 0;
        for(int val : nums[start]) {
            count += count(nums, map, start + 1 ,sum - val);
        }
        return count;
    }
}
