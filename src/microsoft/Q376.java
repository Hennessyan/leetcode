package microsoft;
// Wiggle Subsequence
public class Q376 {
    // DP : O(n) O(n)
    public int wiggleMaxLength(int[] nums) {
        int len = nums.length;
        if(len < 2) {   // [0, 0] => return 1.
            return len;
        }
        int[] pos = new int[len];
        int[] neg = new int[len];
        pos[0] = neg[0] = 1;
        for(int i = 1; i < len; i++) {
            if(nums[i] - nums[i - 1] > 0) {
                pos[i] = neg[i - 1] + 1;
                neg[i] = neg[i - 1];
            } else if(nums[i] - nums[i - 1] < 0) {
                neg[i] = pos[i - 1] + 1;
                pos[i] = pos[i - 1];
            } else {
                pos[i] = pos[i - 1];
                neg[i] = neg[i - 1];
            }
        }
        return Math.max(pos[len - 1], neg[len - 1]);
    }
    // DP : O(n) O(1)
    public int wiggleMaxLength1(int[] nums) {
        int len = nums.length;
        if(len < 2) {   // [0, 0] => return 1.
            return len;
        }
        int pos = 1, neg = 1;
        for(int i = 1; i < len; i++) {
            if(nums[i] - nums[i - 1] > 0) {
                pos = neg + 1;
            } else if(nums[i] - nums[i - 1] < 0) {
                neg = pos + 1;
            }
        }
        return Math.max(pos, neg);
    }
    // Greedy : O(n) O(1)
    public int wiggleMaxLength2(int[] nums) {
        int len = nums.length;
        if(len < 2) {   // [0, 0] => return 1.
            return len;
        }
        int prediff = nums[1] - nums[0];
        int count = prediff != 0 ? 2 : 1;
        for(int i = 2; i < len; i++) {
            int diff = nums[i] - nums[i - 1];
            if((diff > 0 && prediff <= 0) || (diff < 0 && prediff >= 0)) {
                count++;
                prediff = diff;
            }
        }
        return count;
    }
}
