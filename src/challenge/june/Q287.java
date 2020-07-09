package challenge.june;

import java.util.Arrays;

// Find the Duplicate Number
public class Q287 {

    public static void main(String[] args) {
        Q287 q = new Q287();
        int[] nums = {1,3,4,2,2};
        System.out.println(q.findDuplicate(nums));  // 2
    }
    // O(nlgn) O(1)
    public int findDuplicate1(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) {
                return nums[i];
            }
        }

        return -1;
    }
    // Each new element in the sequence is an element in nums at the index of the previous element.
    // O(n) O(1)
    public int findDuplicate(int[] nums) {
        int a = nums[0];
        int b = nums[0];
        do {
            a = nums[a];
            b = nums[nums[b]];
        } while(a != b);
        System.out.println(a + " " + b);
        a = nums[0];             // 注意是a = nums[0]
        while(a != b) {
            a = nums[a];
            b = nums[b];
        }
        return a;
    }

//    public int findDuplicate(int[] nums) {
//        int a = nums[0];
//        int b = nums[nums[0]];
//        while(a != b) {
//            a = nums[a];
//            b = nums[nums[b]];
//        }
//        System.out.println(a + " " + b);
//        a = 0;            //注意是a = 0
//        while(a != b) {
//            a = nums[a];
//            b = nums[b];
//        }
//        return a;
//    }
}
