package challenge.dec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Random Pick Index
public class Q398 {
    // O(n) O(n)
//    private Map<Integer, List<Integer>> map;
//    public Solution(int[] nums) {
//        map = new HashMap<>();
//        for(int i = 0; i < nums.length; i++) {
//            map.computeIfAbsent(nums[i], x -> new ArrayList<>()).add(i);
//        }
//    }
//
//    public int pick(int target) {
//        List<Integer> tmp = map.get(target);
//        int index = (int) (Math.random() * tmp.size());
//        return tmp.get(index);
//    }
    // reservoir sampling
    // O(n) O(1)
    class Solution {
        int[] nums;
        public Solution(int[] nums) {
            this.nums = nums;
        }

        public int pick(int target) {
            int scope = 1, index = 0, i = 0;
            while(i < nums.length) {
                if(nums[i] == target) {
                    if(Math.random() < 1.0 / scope) {
                        index = i;
                    }
                    scope++;
                }
                i++;
            }
            return index;
        }
    }
}
