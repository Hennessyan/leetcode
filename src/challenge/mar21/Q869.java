package challenge.mar21;

import java.util.Arrays;

// Reordered Power of 2
public class Q869 {
    // O(logN) - 10 based.
    public boolean reorderedPowerOf2(int N) {
        System.out.println((1 << 30));  // 1073741824

        int[] digitsN = getDigits(N);
        for(int i = 0; i < 31; i++) {
            if(Arrays.equals(digitsN, getDigits(1 << i))) {
                return true;
            }
        }
        return false;
    }
    private int[] getDigits(int N) {
        int[] res = new int[10];
        while(N > 0) {
            res[N % 10]++;
            N /= 10;
        }
        return res;
    }
    // O(logN!*logN) O(logN)
    public boolean reorderedPowerOf21(int N) {
        // Build eg. N = 128 -> A = [1, 2, 8]
        String S = Integer.toString(N);
        int[] A = new int[S.length()];
        for (int i = 0; i < S.length(); ++i)
            A[i] = S.charAt(i) - '0';
        return permutations(A, 0);
    }

    // Return true if A represents a valid power of 2
    public boolean isPowerOfTwo(int[] A) {
        if (A[0] == 0) return false;  // no leading zero

        // Build eg. A = [1, 2, 8] -> N = 128
        int N = 0;
        for (int x: A)
            N = 10 * N + x;

        // Remove the largest power of 2
        while (N > 0 && ((N & 1) == 0))
            N >>= 1;

        // Check that there are no other factors besides 2
        return N == 1;
    }

    public boolean permutations(int[] A, int start) {
        if (start == A.length)
            return isPowerOfTwo(A);

        // Choose some index i from [start, A.length - 1]
        // to be placed into position A[start].
        for (int i = start; i < A.length; ++i) {
            // Place A[start] with value A[i].
            swap(A, start, i);

            // For each such placement of A[start], if a permutation
            // of (A[start+1], A[start+2], ...) can result in A
            // representing a power of 2, return true.
            if (permutations(A, start + 1))
                return true;

            // Restore the array to the state it was in before
            // A[start] was placed with value A[i].
            swap(A, start, i);
        }

        return false;
    }

    public void swap(int[] A, int i, int j) {
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
}
