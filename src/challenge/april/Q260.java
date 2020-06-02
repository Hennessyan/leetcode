package challenge.april;

import java.util.Arrays;

// Single Number III
public class Q260 {

    public static void main(String[] args) {
        Q260 q = new Q260();
        int[] nums = {1,2,1,3,2,5};
        System.out.println(Arrays.toString(q.singleNumber(nums)));
    }

    public int[] singleNumber(int[] nums) {
        // difference between two numbers (x and y) which were seen only once
        int bitmask = 0;
        for (int num : nums) bitmask ^= num;

        // rightmost 1-bit diff between x and y
        int diff = bitmask & (-bitmask);

        int x = 0;
        // bitmask which will contain only x
        for (int num : nums) if ((num & diff) != 0) x ^= num;

        return new int[]{x, bitmask^x};
    }

    // O(n) O(n)
//    public int[] singleNumber(int[] nums) {
//        Map<Integer, Integer> map = new HashMap<>();
//        for(int n : nums) {
//            map.put(n, map.getOrDefault(n, 0) + 1);
//        }
//        int[] res = new int[2];
//        int index = 0;
//        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
//            if(entry.getValue() == 1) res[index++] = entry.getKey();
//        }
//        return res;
//    }
}
