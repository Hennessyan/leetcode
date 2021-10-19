package amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Intersection of Two Arrays
public class Q349 {
    // O(n + m) O(1)
    public int[] intersection0(int[] nums1, int[] nums2) {
        int[] fre = new int[1001];
        for(int num : nums1) {
            fre[num] = 1;
        }
        List<Integer> list = new ArrayList<>();
        for(int num : nums2) {
            if(fre[num] == 1) {
                list.add(num);
                fre[num] = 0;   // if we use set, need it for both nums1 and nums2 to avoid duplicates.
            }
        }
        int size = list.size();
        int[] res = new int[size];
        for(int i = 0; i < size; i++) {
            res[i] = list.get(i);
        }
        return res;
    }
    // O(nlgn + mlgm) O(lgn + lgm)
    public int[] intersection(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        if(n1 * n2 == 0) {
            return new int[0];
        }
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0;
        while(i < n1 && j < n2) {
            if(nums1[i] < nums2[j]) {
                i++;
                while(i < n1 && nums1[i] == nums1[i - 1]) {
                    i++;
                }
            } else if(nums1[i] > nums2[j]) {
                j++;
                while(j < n2 && nums2[j] == nums2[j - 1]) {
                    j++;
                }
            } else {
                list.add(nums1[i]);
                i++;
                j++;
                while(i < n1 && nums1[i] == nums1[i - 1]) {
                    i++;
                }
                while(j < n2 && nums2[j] == nums2[j - 1]) {
                    j++;
                }
            }
        }
        int size = list.size();
        int[] res = new int[size];
        for(int k = 0; k < size; k++) {
            res[k] = list.get(k);
        }
        return res;
    }
}
