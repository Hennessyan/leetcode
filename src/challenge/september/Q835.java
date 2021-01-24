package challenge.september;

import javafx.util.Pair;

import java.util.*;

// Image Overlap
public class Q835 {

    // O(n^4) O(1)
    // 2(n-1) * 2(n-1) * n^2
    // x-axis 1 -> left
    // y-axis 1 -> up
    public int largestOverlap0(int[][] A, int[][] B) {
        int overlap = 0;
        for (int y = 0; y < A.length; y++) {
            for (int x = 0; x < A.length; x++) {
                overlap = Math.max(overlap, shiftAndCount(x, y, A, B));
                overlap = Math.max(overlap, shiftAndCount(x, y, B, A));
            }
        }
        return overlap;
    }

    // n^2
    private int shiftAndCount(int x, int y, int[][] A, int[][] B) {
        int count = 0;
        int r = 0;
        for (int i = y; i < A.length; i++) {
            int c = 0;
            for (int j = x; j < A.length; j++) {
                if (A[i][j] == 1 && A[i][j] == B[r][c]) {
                    count++;
                }
                c++;
            }
            r++;
        }

        return count;
    }

    // method2
    // O(n^4) => n^2 + n^2 * n^2
    // O(n^2)
    // 不能使用int[],因为它没有重写equals() method.
    protected List<Pair<Integer, Integer>> non_zero_cells(int[][] M) {
        List<Pair<Integer, Integer>> ret = new ArrayList<>();
        for (int row = 0; row < M.length; ++row)
            for (int col = 0; col < M.length; ++col)
                if (M[row][col] == 1)
                    ret.add(new Pair<>(row, col));
        return ret;
    }

    public int largestOverlap(int[][] A, int[][] B) {

        List<Pair<Integer, Integer>> A_ones = non_zero_cells(A);
        List<Pair<Integer, Integer>> B_ones = non_zero_cells(B);

        int maxOverlaps = 0;
        HashMap<Pair<Integer, Integer>, Integer> groupCount = new HashMap<>();

        for (Pair<Integer, Integer> a : A_ones)
            for (Pair<Integer, Integer> b : B_ones) {
                Pair<Integer, Integer> vec =
                        new Pair<>(b.getKey() - a.getKey(), b.getValue() - a.getValue());

                if (groupCount.containsKey(vec)) {
                    groupCount.put(vec, groupCount.get(vec) + 1);
                } else {
                    groupCount.put(vec, 1);
                }
                maxOverlaps = Math.max(maxOverlaps, groupCount.get(vec));
            }

        return maxOverlaps;
    }

    // method3： https://leetcode.com/problems/image-overlap/solution/ (DOT PRODUCT, N + 2(N -1) just review)
}
