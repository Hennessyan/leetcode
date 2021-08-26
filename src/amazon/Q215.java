package amazon;

import java.util.Arrays;
import java.util.PriorityQueue;

// Kth Largest Element in an Array
public class Q215 {
    // quick sort, not stable: O(n) - in average O(n^2) O(1)
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        k = n - k;  // kth largest is (n-k)th smallest, and n-k is 0 based index.

        int l = 0, r = n - 1;
        while(l < r) {
            int pivot = partition(nums, l, r);
            if(pivot == k) {
                return nums[k];
            } else if(pivot > k) {
                r = pivot - 1;
            } else {
                l = pivot + 1;
            }
        }
        return nums[l];
    }
    private int partition(int[] nums, int l, int r) {
        int m = nums[l];
        while(l < r) {
            while(l < r && nums[r] >= m) {  // at lease one equal is needed
                r--;
            }
            nums[l] = nums[r];
            while(l < r && nums[l] <= m) {  // at lease one equal is needed
                l++;
            }
            nums[r] = nums[l];
        }
        nums[l] = m;
        return l;
    }
    // variant of quick sort: O(nlgn) O(lgn)
    public int findKthLargest0(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
    // O(nlgk) O(k)
    public int findKthLargest1(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();  // poll small by default
        for(int num : nums) {
            pq.offer(num);
            if(pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }
}
