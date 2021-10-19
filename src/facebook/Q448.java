package facebook;

import java.util.ArrayList;
import java.util.List;

// Find All Numbers Disappeared in an Array
// Q442
public class Q448 {
    // O(n) O(1)
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for(int num : nums) {
            int val = Math.abs(num) - 1;
            if(nums[val] > 0) {
                nums[val] *= -1;
            }
        }
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] > 0) {
                res.add(i + 1);
            }
        }
        return res;
    }
}
