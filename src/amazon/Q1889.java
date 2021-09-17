package amazon;

import java.util.Arrays;

// Minimum Space Wasted From Packaging
public class Q1889 {
    // O(sort(p) + n * (sort(b) + m*lg-p)) SC - O(sort)
    // length of boxes - n
    // length of boxes[i] - m
    // length of packages - p
    public int minWastedSpace(int[] packages, int[][] boxes) {
        Arrays.sort(packages);
        long res = Long.MAX_VALUE, M = 1_000_000_007l, sum = 0;
        for(int p : packages) {
            sum += p;
        }
        int pl = packages.length;
        for(int[] box : boxes) {
            Arrays.sort(box);
            int bl = box.length;
            int index = 0;
            long boxSpace = 0l;
            if(box[bl - 1] < packages[pl - 1]) continue;
            for(int b : box) {
                int j = binarysearch(packages, index, b);
                if(j != -1) {
                    boxSpace += (long) (j - index + 1) * b;
                    index = j + 1;
                }
            }
            res = Math.min(res, boxSpace);
        }
        return res == Long.MAX_VALUE ? -1 : (int) ((res - sum) % M);
    }
    private int binarysearch(int[] arr, int l, int val) {
        int r = arr.length - 1, res = -1;
        while(l <= r) {
            int m = l + (r - l) / 2;
            if(arr[m] <= val) {
                res = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return res;
    }
    // another binary search:
    public int minWastedSpace1(int[] A, int[][] boxes) {
        Arrays.sort(A);
        long inf = (long)1e11, res = inf, mod = (long)1e9 + 7, sumA = 0L;
        for (int a : A)
            sumA += a;
        for (int[] B : boxes) {
            Arrays.sort(B);
            if (B[B.length - 1] < A[A.length - 1]) continue;
            long cur = 0, i = 0, j;
            for (int b : B) {
                j = binarySearch(A, b + 1);
                cur += b * (j - i);
                i = j;
            }
            res = Math.min(res, cur);
        }
        return res < inf ? (int)((res - sumA) % mod) : -1;
    }

    private int binarySearch(int[] A, int b) {
        int l = 0, r = A.length;
        while (l < r) {
            int m = (l + r) / 2;
            if (A[m] < b)
                l = m + 1;
            else
                r = m;
        }
        return l;
    }
}
