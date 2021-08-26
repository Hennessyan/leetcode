package microsoft;

import java.util.ArrayList;
import java.util.List;

// Minimum Adjacent Swaps for K Consecutive Ones
// Q296(H)
public class Q1703 {
    // O(n+l) O(l)  l -> size of list for ones
    public int minMoves(int[] nums, int k) {
        if(nums == null || nums.length < k) {
            return 0;
        }
        List<Integer> list = new ArrayList<>();
        int n = nums.length;
        for(int i = 0; i < n; i++) {
            if(nums[i] == 1) {
                list.add(i);
            }
        }

        int sum = 0;
        // if k is even, choose either left median or right median is fine => same cost.
        for(int i = 0; i < k; i ++) {
            sum += Math.abs(list.get(i) - list.get(k / 2));
        }
        int min = sum;

        for (int i=0; i + k < list.size(); i++) {
            int mid = i+k/2;
            sum -= list.get(mid) - list.get(i);
            sum += (list.get(mid+1)-list.get(mid)) * (k/2);
            sum += list.get(i+k) - list.get(mid+1);
            sum -= (list.get(mid+1)-list.get(mid)) * (k-k/2-1); // the last one is L33
            min = Math.min(min, sum);
        }
        // constant offset because it's based on k, so let's deduct it finally.
        int offset = 0;
        for(int i = 0; i < k; i++) {
            offset += Math.abs(i - k/2);
        }
        return min - offset;
    }
}
// https://github.com/wisdompeak/LeetCode/tree/master/Math/1703.Minimum-Adjacent-Swaps-for-K-Consecutive-Ones
/*
 0 1 2 m 4 5 6
   1 2 3 m 5 6 7

 0 m 2 3
   1 m 3 4

If we try to find x in Pi (0 <= i <= k) to make all P to x has smallest distance,
x should be median whatever k is even or odd => Q296
=> min sum |Pi - x|

[p0, p1, p2, x,  p4, p5, p6]
[q0, q1, q2, q3, q4, q5, q6]

For this question, we want to find k adjacent scope.
=> min sum |Pi - x| + (k/2 + ... + 2 + 1 + 0 + 1 + 2 + ... + k/2)
=> min sum |Pi - x| + |i - k/2| (0 <= i <= k)

*/