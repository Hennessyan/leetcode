package challenge.april;

import java.util.HashSet;
import java.util.Set;

// Single Number II
public class Q137 {

    public static void main(String[] args) {
        Q137 q = new Q137();
        int[] nums = {0,1,0,1,0,1,99};
        System.out.println(q.singleNumber(nums));
    }

    // O(n) O(1)
//    public int singleNumber(int[] nums) {
//        int one = 0, two = 0, mask = 0;
//        for(int n : nums) {
//            two |= one & n; //注意这两行的顺序
//            one ^= n;       //先对two操作然后one.
//            mask = ~ (one & two);
//            one &= mask;
//            two &= mask;
//        }
//        return one;
//    }

    // O(n) O(n)
//    public int singleNumber(int[] nums) {
//        Map<Integer, Integer> map = new HashMap<>();
//        for(int n : nums) {
//            map.put(n, map.getOrDefault(n, 0) + 1);
//        }
//        for(int key : map.keySet()) {
//            if(map.get(key) == 1) {
//                return key;
//            }
//        }
//        return -1;
//    }
    // O(n) O(1)
    public int singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        long sum = 0;
        long total = 0;
        for(int n : nums) {
            if(set.add(n)) {
                sum += n;
            }
            total += n;
        }
        return (int) ((3 * sum - total) / 2);
    }
}
