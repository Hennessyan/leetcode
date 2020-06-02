package challenge.april;

import java.util.HashMap;
import java.util.Map;

// Majority Element
//Q229 Q1150
public class Q169 {

    public static void main(String[] args) {
        Q169 q = new Q169();
        int[] nums = {2, 2, 1, 1, 1, 2, 2};
        System.out.println(q.majorityElement(nums));    //2
    }

    public int majorityElement(int[] nums) {
        int major = nums[0], count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                major = nums[i];
                count++;
            } else if (major == nums[i]) {
                count++;
            } else {
                count--;
            }
        }
        return major;
    }

    public int majorityElement1(int[] nums) {
        int[] bits = new int[32];
        for(int i = 0; i < 32; i++) {
            for(int num : nums) {
                if((num >> (31 - i) & 1) == 1) {
                    bits[i]++;
                }
            }
        }
        int res = 0, half = nums.length / 2;
        for(int i = 0; i < 32; i++) {
            int val = (bits[i] > half) ? 1 : 0;
            res |= val << (31 - i);
        }
        return res;
    }

    public int majorityElement2(int[] nums) {
        int temp = nums.length/2;
        Map<Integer, Integer> hm = new HashMap<>();
        for(int i : nums){
            if(hm.containsKey(i))
                hm.put(i, hm.get(i)+1);
            else
                hm.put(i, 1);
            if(hm.get(i) > temp){
                return i;
            }
        }
        return 0;
    }
}
