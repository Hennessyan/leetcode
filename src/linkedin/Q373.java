package linkedin;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

// Find K Pairs with Smallest Sums
public class Q373 {
    // O(klgk) O(k)
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        int m = nums1.length, n = nums2.length;
        if(m == 0 || n == 0 || k == 0) return res;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for(int i = 0; i < m && i < k; i++) {
            pq.offer(new int[]{i, 0, nums1[i] + nums2[0]});
        }
        for(int i = 0; i < k && i < m * n; i++) {
            int[] cur = pq.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[cur[0]]);
            list.add(nums2[cur[1]]);
            res.add(list);
            if(++cur[1] < n) {
                pq.offer(new int[]{cur[0], cur[1], nums1[cur[0]] + nums2[cur[1]]});
            }
        }
        return res;
    }
}
