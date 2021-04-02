package apple;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

// Advantage Shuffle
public class Q870 {
    // O(nlgn) O(n)
    public int[] advantageCount(int[] A, int[] B) {
        Arrays.sort(A);
        int len = A.length;
        long[] tmp = new long[len];
        for(int i = 0; i < len; i++) {
            tmp[i] = (long)B[i] * 10000 + i;
        }
        Arrays.sort(tmp);
        int l = 0, r = len - 1;
        for(int a: A) {
            if(a > tmp[l] / 10000) {
                B[(int) (tmp[l++] % 10000)] = a;
            } else {
                B[(int) (tmp[r--] % 10000)] = a;
            }
        }
        return B;
    }

    public int[] advantageCount1(int[] A, int[] B) {
        Arrays.sort(A);
        Queue<Integer> queue = new PriorityQueue<>((a, b)->Integer.compare(B[b], B[a]));
        for(int i = 0; i < B.length; i++){
            queue.add(i);
        }

        int[] result = new int[A.length];
        int i = 0;
        int j = A.length - 1;

        while(!queue.isEmpty()){
            int index = queue.remove();

            result[index] = A[j] > B[index] ? A[j--] : A[i++];
        }

        return result;
    }
}
