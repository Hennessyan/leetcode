package challenge.dec;

import java.util.Arrays;

// Squares of a Sorted Array
public class Q977 {

    public static void main(String[] args) {
        Q977 q = new Q977();
        int[] A = {-4,-1,0,3,10};
        int[] res = q.sortedSquares(A);
        System.out.println(Arrays.toString(res));
    }
    // O(n) O(n)
    public int[] sortedSquares(int[] A) {
        int len = A.length;
        int[] res = new int[len];
        int i = 0, j = len - 1;
        int k = len - 1;
        while(i <= j) {
            if(A[i] * A[i] < A[j] * A[j]) {
                res[k--] = A[j] * A[j];
                j--;
            } else {
                res[k--] = A[i] * A[i];
                i++;
            }
        }
        return res;
    }
}
