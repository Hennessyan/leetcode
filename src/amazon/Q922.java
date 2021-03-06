package amazon;

import java.util.Arrays;

// Sort Array By Parity II
public class Q922 {

    public static void main(String[] args) {
        Q922 q = new Q922();
        int[] A = {4,2,5,7};
        System.out.println(Arrays.toString(q.sortArrayByParityII(A)));
    }

    // O(n) O(1)
    public int[] sortArrayByParityII(int[] A) {
        int j = 1;
        for (int i = 0; i < A.length; i += 2)
            if (A[i] % 2 == 1) {
                while (A[j] % 2 == 1)
                    j += 2;

                // Swap A[i] and A[j]
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }

        return A;
    }
}
