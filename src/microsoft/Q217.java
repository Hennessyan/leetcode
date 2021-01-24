package microsoft;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// Contains Duplicate
public class Q217 {

    public static void main(String[] args) {
        Q217 q = new Q217();
        int[] nums = {1, 2, 3, 1};
        System.out.println(q.containsDuplicate(nums));
    }

    // O(n) O(n)
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return true;
            }
        }
        return false;
    }

    // O(nlgn) O(1)
    public boolean containsDuplicate1(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i] == nums[i + 1]) return true;
        }
        return false;
    }
}
