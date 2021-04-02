package amazon;
// Product of Array Except Self
public class Q238 {
    // O(n) O(n)
    // 1 2 3 4
    // 1 1 2 6
    // 24 12 4 1
    public int[] productExceptSelf(int[] nums) {

        // The length of the input array
        int length = nums.length;

        // The left and right arrays as described in the algorithm
        int[] L = new int[length];
        int[] R = new int[length];

        // Final answer array to be returned
        int[] answer = new int[length];
        L[0] = 1;
        for (int i = 1; i < length; i++) {
            L[i] = nums[i - 1] * L[i - 1];
        }
        R[length - 1] = 1;
        for (int i = length - 2; i >= 0; i--) {

            R[i] = nums[i + 1] * R[i + 1];
        }
        for (int i = 0; i < length; i++) {
            answer[i] = L[i] * R[i];
        }

        return answer;
    }
    // method2
    public int[] productExceptSelf1(int[] nums) {
        if(nums == null || nums.length == 0) {
            return nums;
        }
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        for(int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        for(int i = n - 1; i > 0; i--) {
            res[i] *= res[0];
            res[0] *= nums[i];
        }
        return res;
    }
}
