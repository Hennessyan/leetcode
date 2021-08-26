package amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Triangle
public class Q120 {
    // O(n^2) O(n) n - # of elements in last list.
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.size() == 0) {
            return 0;
        }
        int len = triangle.size();
        List<Integer> last = triangle.get(len - 1);
        int lastLen = last.size();
        int[] memo = new int[lastLen];
        for(int i = 0; i < lastLen; i++) {
            memo[i] = last.get(i);
        }

        for(int i = len - 2; i >= 0; i--) {
            List<Integer> list = triangle.get(i);
            for(int j = 0; j < list.size(); j++) {
                memo[j] = Math.min(memo[j], memo[j + 1]) + list.get(j);
            }
        }
        return memo[0];
    }

    public int minimumTotal1(List<List<Integer>> triangle) {
        List<Integer> prevRow = triangle.get(0);
        for (int row = 1; row < triangle.size(); row++) {
            List<Integer> currRow = new ArrayList<>();
            for (int col = 0; col <= row; col++) {
                int smallestAbove = Integer.MAX_VALUE;
                if (col > 0) {
                    smallestAbove = prevRow.get(col - 1);
                }
                if (col < row) {
                    smallestAbove = Math.min(smallestAbove, prevRow.get(col));
                }
                currRow.add(smallestAbove + triangle.get(row).get(col));
            }
            prevRow = currRow;
        }
        return Collections.min(prevRow);
    }
}
