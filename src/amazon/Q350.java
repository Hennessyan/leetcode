package amazon;

import java.util.*;

// Intersection of Two Arrays II
// Q1213
public class Q350 {
    // https://leetcode.com/problems/intersection-of-two-arrays-ii/solution/
    // O(m+n) O(min(m, n))
    public int[] intersect(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int k = 0;
        for(int num : nums2) {
            int c = map.getOrDefault(num, 0);
            if(c > 0) {
                nums1[k++] = num;
                map.put(num, c - 1);
            }
        }
        return Arrays.copyOf(nums1, k);
    }

    public int[] intersect1(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 == null) return new int[0];
        int n1 = nums1.length, n2 = nums2.length;
        List<Integer> list = new ArrayList<>();
        int i = 0, j = 0;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        while(i < n1 && j < n2) {
            if(nums1[i] == nums2[j]) {
                list.add(nums1[i]);
                i++;
                j++;
            } else if(nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
