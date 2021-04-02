package amazon;
// Arithmetic Slices
public class Q413 {
    // O(n^2) O(1)
    public int numberOfArithmeticSlices(int[] A) {
        if(A == null || A.length < 3) {
            return 0;
        }
        int count = 0;
        for(int i = 0; i < A.length - 2; i++) {
            int d = A[i + 1] - A[i];
            for(int j = i + 2; j < A.length; j++) {
                if(A[j] - A[j - 1] == d) {
                    count++;
                } else {
                    break;
                }
            }
        }
        return count;
    }
    // O(n) O(1)
    public int numberOfArithmeticSlices1(int[] A) {
        if(A == null || A.length < 3) {
            return 0;
        }
        int d = 0, sum = 0;
        for(int i = 2; i < A.length; i++) {
            if(A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                d++;
                sum += d;
            } else {
                d = 0;
            }
        }
        return sum;
    }
    // O(n) O(1)
    public int numberOfArithmeticSlices2(int[] A) {
        if(A == null || A.length < 3) {
            return 0;
        }
        int d = 0, sum = 0;
        for(int i = 2; i < A.length; i++) {
            if(A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                d++;
            } else {
                sum += d * (d + 1) / 2;
                d = 0;
            }
        }
        return sum += d * (d + 1) / 2;
    }
}
