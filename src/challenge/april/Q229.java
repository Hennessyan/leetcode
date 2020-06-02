package challenge.april;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Majority Element II
public class Q229 {

    public static void main(String[] args) {
        Q229 q = new Q229();
        int[] nums = {1, 1, 1, 3, 3, 2, 2, 2};
        List<Integer> res = q.majorityElement(nums);
        System.out.println(Arrays.asList(res));
    }

    public List<Integer> majorityElement(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return list;
        }
        //注意初始化的值
        int m1 = nums[0], m2 = nums[0], c1 = 0, c2 = 0;
        for (int n : nums) {
            if (m1 == n) {
                c1++;
            } else if (m2 == n) {
                c2++;
            } else if (c1 == 0) {
                m1 = n;
                c1++;
            } else if (c2 == 0) {
                m2 = n;
                c2++;
            } else {
                c1--;
                c2--;
            }
        }
        c1 = 0;
        c2 = 0;
        for (int n : nums) {
            if (n == m1) {
                c1++;
            } else if (n == m2) {       //需要用else if
                c2++;
            }
        }
        if (c1 > nums.length / 3) list.add(m1);
        if (c2 > nums.length / 3) list.add(m2);
        return list;
    }
}
