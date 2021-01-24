package challenge.jan21;
// Get Maximum in Generated Array
public class Q1646 {
    // O(n) O(n)
    public int getMaximumGenerated0(int n) {
        if(n == 0) {
            return 0;
        }
        int[] nums = new int[n + 1];
        nums[1] = 1;
        int max = 0;
        for(int i = 1; i <= n; i++) {
            if(2 * i <= n) {
                nums[2 * i] = nums[i];
            }
            if(2 * i + 1 <= n) {
                nums[2 * i + 1] = nums[i] + nums[i + 1];
            }
            max = Math.max(max, nums[i]);
        }
        return max;
    }

    public int getMaximumGenerated1(int n) {
        if(n == 0) {
            return 0;
        }
        int[] nums = new int[n + 1];
        nums[1] = 1;
        int max = 1;
        for(int i = 2; i <= n; i++) {
            if(i % 2 == 0) {
                nums[i] = nums[i / 2];
            } else {
                nums[i] = nums[i / 2 + 1] + nums[i / 2];
            }
            max = Math.max(max, nums[i]);
        }
        return max;
    }

    public int getMaximumGenerated(int n) {
        if(n == 0) {
            return 0;
        }
        int[] nums = new int[n + 1];
        nums[1] = 1;
        int max = 1;
        for(int i = 1; i <= n / 2; i++) {
            nums[2 * i] = nums[i];
            if(2 * i + 1 <= n) {
                nums[2 * i + 1] = nums[i] + nums[i + 1];
                max = Math.max(max, nums[2 * i + 1]);
            }
        }
        return max;
    }
}
