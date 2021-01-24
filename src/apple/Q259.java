package apple;

import java.util.Arrays;

// 3Sum Smaller
public class Q259 {

    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int res = 0;
        for(int i = 0; i < nums.length - 2; i++) {
            res += twoSumSmaller(nums, i + 1, target - nums[i]);
        }
        return res;
    }
    /*method1 binary search*/
    //O(n^2lgn) O(1)
    private int twoSumSmaller(int[] nums, int start, int target) {
        int sum = 0;
        for(int i = start; i < nums.length - 1; i++) {
            int j =  binarySearch(nums, i, target - nums[i]);
            sum += j - i;
        }
        return sum;
    }
    private int binarySearch(int[] nums, int start, int target) {
        int l = start, r = nums.length - 1;
        while(l < r) {
            int mid = (l + r + 1) / 2;  //注意这里取upper bound, otherwise will not terminate.
            if(nums[mid] < target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    private int twoSumSmaller1(int[] nums, int start, int target) {
        int sum = 0, l = start, r = nums.length - 1;
        while(l < r) {
            if(nums[l] + nums[r] < target) {
                sum += r - l;
                l++;
            } else {
                r--;
            }
        }
        return sum;
    }
}
