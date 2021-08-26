package amazon;

import java.util.Arrays;

// Maximum Gap
public class Q164 {

    /*method1 bucket sort*/
    //O(n) O(n)
    //因为我们不把min与max放到bucket之中,len-1个bucket对应len-2个数至少有一个bucket是空的;
    //也就是说这个gap一定大于等于我们计算的bucket的容量:(int)Math.ceil((double)(max - min) / (len - 1))
    //因此我们也不用在乎同一bucket的最大与最小值的差值是多少了=》不用考虑 bucketsMax[i] - bucketsMin[i]
    public int maximumGap(int[] nums) {
        if(nums == null || nums.length < 2){
            return 0;
        }
        int min = nums[0], max = nums[0];
        for(int num : nums){
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        int len = nums.length;
        int gap = (int)Math.ceil((double)(max - min) / (len - 1));
        int[] bucketsMin = new int[len - 1];
        int[] bucketsMax = new int[len - 1];
        Arrays.fill(bucketsMin, Integer.MAX_VALUE);
        Arrays.fill(bucketsMax, Integer.MIN_VALUE);
        for(int num : nums){
            if(num == min || num == max){
                continue;
            }
            int index = (num - min) / gap;
            bucketsMin[index] = Math.min(bucketsMin[index], num);
            bucketsMax[index] = Math.max(bucketsMax[index], num);
        }
        int ans = Integer.MIN_VALUE, prev = min;
        for(int i = 0; i < len - 1; i++){
            if(bucketsMin[i] == Integer.MAX_VALUE && bucketsMax[i] == Integer.MIN_VALUE){
                continue;
            }
            ans = Math.max(ans, bucketsMin[i] - prev);
            prev = bucketsMax[i];
        }
        ans = Math.max(ans, max - prev);
        return ans;
    }
    /*method2*/
    //O(nlgn) O(1)
    public int maximumGap1(int[] nums) {
        if(nums == null || nums.length < 2){
            return 0;
        }
        Arrays.sort(nums);
        int gap = Integer.MIN_VALUE;
        for(int i = 1; i < nums.length; i++){
            gap = Math.max(gap, nums[i] - nums[i - 1]);
        }
        return gap;
    }
}
