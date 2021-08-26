package amazon;
// Global and Local Inversions
public class Q775 {
    // brute force : O(n^2) O(1)
    // local inversion is global inversion, hence we only need to check if there is any non-local inversion.
    public boolean isIdealPermutation1(int[] A) {
        int N = A.length;
        for (int i = 0; i < N - 2; ++i)
            for (int j = i + 2; j < N; ++j)
                if (A[i] > A[j]) return false;
        return true;
    }
    // improve of method1
    public boolean isIdealPermutation(int[] A) {
        int n = A.length;
        int min = A[n - 1]; // min = n is fine.
        for(int i = n - 1; i >= 2; i--) {
            min = Math.min(min, A[i]);
            if(A[i - 2] > min) {
                return false;
            }
        }
        return true;
    }
    // linear scan
    public boolean isIdealPermutation2(int[] A) {
        for (int i = 0; i < A.length; ++i)
            if (Math.abs(A[i] - i) > 1)
                return false;
        return true;
    }

}
