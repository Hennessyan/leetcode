package amazon;

import java.util.Arrays;

// Sort Array By Parity
public class Q905 {

    // O(nlgn) O(n)
    public int[] sortArrayByParity0(int[] A) {
        Integer[] B = new Integer[A.length];
        for (int t = 0; t < A.length; ++t)
            B[t] = A[t];

        Arrays.sort(B, (a, b) -> Integer.compare(a%2, b%2));

        for (int t = 0; t < A.length; ++t)
            A[t] = B[t];
        return A;

        /* Alternative:
        return Arrays.stream(A)
                     .boxed()
                     .sorted((a, b) -> Integer.compare(a%2, b%2))
                     .mapToInt(i -> i)
                     .toArray();
        */
    }
    // O(n) O(1)
    public int[] sortArrayByParity(int[] A) {
        int l = 0, r = A.length - 1;
        while(l < r) {
            if(A[l] % 2 == 0) {
                l++;
            } else {
                int tmp = A[l];
                A[l] = A[r];
                A[r--] = tmp;
            }
        }
        return A;
    }
}
