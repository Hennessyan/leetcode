package microsoft;

import java.util.HashSet;
import java.util.Set;

// Contains Duplicate II
public class Q219 {

    public static void main(String[] args) {
        Q219 q = new Q219();
        int[] nums = {1, 2, 3, 1, 2, 3};
        System.out.println(q.containsNearbyDuplicate(nums, 2)); // false
    }
    // O(n) O(min(n,k))
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i])) {
                return true;
            }
            if (set.size() == k + 1) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }
}
