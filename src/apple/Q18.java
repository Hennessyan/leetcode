package apple;

import java.util.*;

// 4Sum
public class Q18 {
    // O(n^3) O(lgn) / O(n)
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length < 4) {
            return res;
        }

        Arrays.sort(nums);
        int len = nums.length;
        for(int i = 0; i < len - 3; i++) {
            if(i > 0 && nums[i] == nums[i - 1]) {  // 避免重复
                continue;
            }
            for(int j = i + 1; j < len - 2; j++) {
                if(j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int l = j + 1, r = len - 1;
                while(l < r) {
                    int sum = nums[i] + nums[j] + nums[l] + nums[r];
                    if(sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        l++;
                        r--;
                        while(l < r && nums[l] == nums[l - 1]) {
                            l++;
                        }
                        while(l < r && nums[r] == nums[r + 1]) {
                            r--;
                        }
                    } else if(sum > target) {
                        r--;
                    } else {
                        l++;
                    }
                }
            }
        }
        return res;
    }

    // basic method - O(n^(k-1)) O(n) - stack for recursion
    public List<List<Integer>> fourSum1(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 0, 4);
    }
    public List<List<Integer>> kSum(int[] nums, int target, int start, int k) {
        List<List<Integer>> res = new ArrayList<>();
        // avoid overflow which causes wrong result
        // [0, 0, 0, 1000000000, 1000000000, 1000000000] 1000000000
        // or [-1000000000, -1000000000, -1000000000, 0, 0, 0] -1000000000
        if (start == nums.length || nums[start] > target / k || target / k > nums[nums.length - 1] ) // division rather than multiplication to avoid overflow
            return res;
        if (k == 2)
            return twoSum(nums, target, start);
        for (int i = start; i < nums.length; ++i)
            if (i == start || nums[i - 1] != nums[i])
                for (List<Integer> set : kSum(nums, target - nums[i], i + 1, k - 1)) {
                    res.add(new ArrayList<>(Arrays.asList(nums[i])));
                    res.get(res.size() - 1).addAll(set);
                    // can't use set.add(nums[i]) as set is abstractList, does not overwrite add() method
                }
        return res;
    }
    public List<List<Integer>> twoSum(int[] nums, int target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> s = new HashSet<>();
        for (int i = start; i < nums.length; ++i) {
            if (res.isEmpty() || res.get(res.size() - 1).get(1) != nums[i]) // can't use : if(i > start && !s.contains(nums[i])) -> [0,0,0,0] 0
                if (s.contains(target - nums[i]))
                    res.add(Arrays.asList(target - nums[i], nums[i]));
            s.add(nums[i]);
        }
        return res;
    }
    // 这个比上边那个用SET的好记!!!
    public List<List<Integer>> twoSum1(int[] nums, int target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        int l = start, r = nums.length - 1;
        while(l < r) {
            int sum = nums[l] + nums[r];
            if(sum == target) {
                res.add(Arrays.asList(nums[l++], nums[r--]));
                while(l < r && nums[l] == nums[l - 1]) {
                    l++;
                }
                while(l < r && nums[r] == nums[r + 1]) {
                    r--;
                }
            } else if(sum > target) {
                r--;
            } else {
                l++;
            }
        }
        return res;
    }
}
