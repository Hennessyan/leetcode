package google;

import java.util.*;

// 3Sum
public class Q15 {
    // O(n^2) O(lgn) / O(n)
    // space complexity of quick sort is O(lgn)
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }
        Arrays.sort(nums);
        int len = nums.length;
        for(int i = 0; i < len - 2; i++) {
            if(i > 0 && nums[i] == nums[i - 1]) {   // 避免重复
                continue;
            }
            int j = i + 1, k = len - 1;
            while(j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if(sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                    while(j < k && nums[j] == nums[j - 1]) {    //必须有,避免重复
                        j++;
                    }
                    while(j < k && nums[k] == nums[k + 1]) {    //加速
                        k--;
                    }
                } else if(sum > 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return res;
    }
    // method2: hashset O(n^2) O(n)
    public List<List<Integer>> threeSum1(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length && nums[i] <= 0; ++i)
            if (i == 0 || nums[i - 1] != nums[i]) {
                twoSum(nums, i, res);
            }
        return res;
    }
    void twoSum(int[] nums, int i, List<List<Integer>> res) {
        Set<Integer> seen = new HashSet<>();
        for (int j = i + 1; j < nums.length; ++j) {
            int complement = -nums[i] - nums[j];
            if (seen.contains(complement)) {
                res.add(Arrays.asList(nums[i], nums[j], complement));
                while (j + 1 < nums.length && nums[j] == nums[j + 1])
                    ++j;
            }
            seen.add(nums[j]);
        }
    }
}
