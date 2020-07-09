package challenge.june;

import java.util.Arrays;
import java.util.PriorityQueue;

// Maximize Sum Of Array After K Negations
public class Q1005 {

    public int largestSumAfterKNegations0(int[] A, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int a : A) {
            pq.offer(a);
        }
        while(--K >= 0) {
            pq.offer(-pq.poll());
        }
        int sum = 0;
        while(!pq.isEmpty()) {
            sum += pq.poll();
        }
        return sum;
    }

    public int largestSumAfterKNegations(int[] A, int K) {
        Arrays.sort(A);
        for(int i = 0; i < A.length && A[i] < 0 && K > 0; i++, K--) {
            A[i] *= -1;
        }
        int sum = 0, min = A[0];
        for(int i = 0; i < A.length; i++) {
            sum += A[i];
            min = Math.min(min, A[i]);
        }
        return K % 2 == 0 ? sum : sum - 2 * min;
    }
}
