package amazon;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Set Mismatch
public class Q645 {
    // sort
    public int[] findErrorNums(int[] nums) {
        Arrays.sort(nums);
        int dup = -1, missing = 1;  // missing must be initialized as 1.
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1])
                dup = nums[i];
            else if (nums[i] > nums[i - 1] + 1)
                missing = nums[i - 1] + 1;
        }
        return new int[] {dup, nums[nums.length - 1] != nums.length ? nums.length : missing};
    }
    // hashmap
    public int[] findErrorNums1(int[] nums) {
        Map< Integer, Integer > map = new HashMap();
        int dup = -1, missing = 1;
        for (int n: nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        for (int i = 1; i <= nums.length; i++) {
            if (map.containsKey(i)) {
                if (map.get(i) == 2)
                    dup = i;
            } else
                missing = i;
        }
        return new int[]{dup, missing};
    }
    // extra array
    public int[] findErrorNums2(int[] nums) {
        int[] arr = new int[nums.length + 1];
        int dup = -1, missing = 1;
        for (int i = 0; i < nums.length; i++) {
            arr[nums[i]] += 1;
        }
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == 0)
                missing = i;
            else if (arr[i] == 2)
                dup = i;
        }
        return new int[]{dup, missing};
    }
    // O(n) O(1)
    public int[] findErrorNums3(int[] nums) {
        int dup = -1, miss = 1;
        for(int num : nums) {
            if(nums[Math.abs(num) - 1] < 0) {
                dup = Math.abs(num);
            } else {
                nums[Math.abs(num) - 1] *= -1;
            }
        }
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] > 0) {
                miss = i + 1;
            }
        }
        return new int[]{dup, miss};
    }
    // O(n) O(1)
    public int[] findErrorNums4 (int[] nums) {
        int xor = 0, xor1 = 0, xor0 = 0;
        for(int num : nums) {
            xor ^= num;
        }
        for(int i = 1; i <= nums.length; i++) {
            xor ^= i;
        }
        int lsb = xor & -xor;
        for (int n: nums) {
            if ((n & lsb) != 0)
                xor1 ^= n;
            else
                xor0 ^= n;
        }
        for (int i = 1; i <= nums.length; i++) {
            if ((i & lsb) != 0)
                xor1 ^= i;
            else
                xor0 ^= i;
        }
        for(int num : nums) {
            if(num == xor0) {
                return new int[]{xor0, xor1};
            }
        }
        return new int[]{xor1, xor0};
    }
}
