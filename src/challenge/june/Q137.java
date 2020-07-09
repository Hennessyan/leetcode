package challenge.june;

import java.util.HashSet;
import java.util.Set;

// Single Number II
public class Q137 {

    public static void main(String[] args) {
        Q137 q = new Q137();
        int[] nums = {0,1,0,1,0,1,99};
        System.out.println(q.singleNumber(nums));   // 99
    }
    // O(n) O(n)
    public int singleNumber1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        long sum = 0, total = 0;
        for(int n : nums) {
            total += n;
            if(set.add(n)) {
                sum += n;
            }
        }
        return (int) ((sum * 3 - total) / 2);
    }
    // O(n) O(1)
    public int singleNumber(int[] nums) {
        int one = 0, two = 0, mask = 0;
        for(int n : nums) {
            two |= one & n;
            one ^= n;

            mask = ~ (one & two);
            one &= mask;
            two &= mask;
        }
        return one;
    }
}
