package google;
// Patching Array
public class Q330 {
    // O(m+lgn) O(1)
    // choose miss number as large as possible:
    // Suppose the number we added is xx then, the ranges [1, miss) and [x, x + miss) are both covered.
    // And since we know that x <= miss, the two ranges will cover the range [1, x + miss).
    // We want to choose xx as large as possible so that the range can cover as large as possible.
    // Therefore, the best option is x = miss.
    public int minPatches(int[] nums, int n) {
        int patch = 0, i = 0, len = nums.length;
        long miss = 1;          // use long to avoid integer overflow error
        while(miss <= n) {      // we may need patch after traversing all elements in nums
            if(i < len && nums[i] <= miss) {
                miss += nums[i++];      // m - length of nums
            } else {
                patch++;                // lgn
                miss += miss;
            }
        }
        return patch;
    }
}
