package google;

import java.util.ArrayDeque;
import java.util.Deque;

// Find the Most Competitive Subsequence
public class Q1673 {

    // O(n) O(n)
    public int[] mostCompetitive(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new ArrayDeque<>();  // pop(), poll(), remove()都是FIRST ELE IN QUEUE
        int additionalOp = n - k;
        for(int num : nums) {
            while(!deque.isEmpty() && deque.peekLast() > num && additionalOp > 0) {
                deque.pollLast();
                additionalOp--;
            }
            deque.offerLast(num);
        }
        // Be careful : deque.size() >= k
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = deque.pollFirst();
        }
        return result;
    }
}
