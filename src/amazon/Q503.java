package amazon;

import java.util.Stack;

// Next Greater Element II
public class Q503 {
    // O(n^2) O(n)
    public int[] nextGreaterElements0(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        for(int i = 0; i < len; i++) {
            res[i] = -1;
            for(int j = 1; j < len; j++) {
                if(nums[(i + j) % len] > nums[i]) {
                    res[i] = nums[(i + j) % len];
                    break;
                }
            }
        }
        return res;
    }
    // O(n) O(n)
    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        Stack<Integer> stack = new Stack<>();
        for(int i = 2 * len - 1; i >= 0; i--) {
            while(!stack.isEmpty() && stack.peek() <= nums[i % len]) {
                stack.pop();
            }
            res[i % len] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i % len]);
        }
        return res;
    }
}
