package amazon;

import java.util.Arrays;

// 1099. Two Sum Less Than K
public class Q1099 {
    // count sort : O(n + 1000) O(1000)
    public int twoSumLessThanK(int[] A, int k) {
        int[] count = new int[1001];
        for(int a : A) {
            count[a]++;
        }
        int l = 1, r = 1000, sum = -1;
        while(l <= r) {
            if(l + r >= k || count[r] == 0) {
                r--;
            } else {
                if(count[l] > (l < r ? 0 : 1)) {
                    sum = Math.max(sum, l + r);
                }
                l++;
            }
        }
        return sum;
    }
    // two pointers : O(nlgn) O(lgn)
    public int twoSumLessThanK1(int[] nums, int k) {
        Arrays.sort(nums);
        int answer = -1;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum < k) {
                answer = Math.max(answer, sum);
                left++;
            } else {
                right--;
            }
        }
        return answer;
    }
}
