package challenge.september;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

// Contains Duplicate III
public class Q220 {

    public static void main(String[] args) {
        Q220 q = new Q220();
        int[] nums = {1, 5, 9, 1, 5, 9};
        int[] nums1 = {1, 2, 3, 1};
        System.out.println(q.containsNearbyAlmostDuplicate(nums, 2, 3));    // false
        System.out.println(q.containsNearbyAlmostDuplicate(nums1, 3, 0));    // true
    }


    // TreeSet O(nlg(min(n,k)) O(min(n,k))
    //  [-1,2147483647]
    //  1
    //  2147483647
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Integer head = set.ceiling(nums[i]);
//            if (head != null && head - nums[i] <= t) { overflow
            if (head != null && head <= t + nums[i]) {
                return true;
            }
            Integer tail = set.floor(nums[i]);
//            if (tail != null && nums[i] - tail <= t) { overflow
            if (tail != null && nums[i] <= t + tail) {
                return true;
            }
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    // Long to avoid overflow
    // bucket : O(n) O(min(n, k))
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        if (nums.length < 2 || k <= 0 || t < 0) {
            return false;
        }
        Map<Long, Long> map = new HashMap<>();
        long w = t + 1;
        for (int i = 0; i < nums.length; i++) {
            long m = convert((long) nums[i], w);
            if (map.containsKey(m)) {
                return true;
            }
            if (map.containsKey(m - 1) && Math.abs((long) nums[i] - map.get(m - 1)) <= t) {
                return true;
            }
            if (map.containsKey(m + 1) && Math.abs((long) nums[i] - map.get(m + 1)) <= t) {
                return true;
            }
            map.put(m, (long) nums[i]);
            if (i >= k) {
                map.remove(convert((long) nums[i - k], w));
            }
        }
        return false;
    }

    private long convert(long val, long w) {    // val can be int type
        return val < 0 ? (val + 1) / w - 1 : val / w;
    }
}
