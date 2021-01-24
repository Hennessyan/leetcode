package google;

import java.util.Arrays;
import java.util.PriorityQueue;

// Kth Largest Element in an Array
public class Q215 {
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
    // quick sort, not stable: O(nlgn) - in average O(n^2) O(1)
    public int findKthLargest2(int[] nums, int k) {
        int l = 0, r = nums.length - 1;
        k = nums.length - k;
        while(l < r) {
            int pivot = partition(nums, l, r);
            if(pivot == k) {
                return nums[pivot];
            } else if(pivot > k) {
                r = pivot - 1;
            } else {
                l = pivot + 1;
            }
        }
        return nums[l];
    }

    private int partition(int[] nums, int l, int r) {
        int midValue = nums[l];
        while(l < r) {
            while(l < r && nums[r] >= midValue) {
                r--;
            }
            nums[l] = nums[r];
            while(l < r && nums[l] < midValue) {
                l++;
            }
            nums[r] = nums[l];
        }
        nums[l] = midValue;
        return l;
    }

}
