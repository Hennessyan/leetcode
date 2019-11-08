package lyft;

import java.util.ArrayList;
import java.util.List;

//Find All Duplicates in an Array
public class Q442 {

    public static void main(String[] args) {
        Q442 q = new Q442();
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> res = q.findDuplicates(nums); //2,3
    }
    //O(n) O(n)
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length < 2) {
            return res;
        }

        for (int i = 0; i < nums.length; i++) {
            int val = Math.abs(nums[i]) - 1;
            if (nums[val] < 0) {
                res.add(val + 1);
            }
            nums[val] *= -1;
        }
        return res;
    }
}
