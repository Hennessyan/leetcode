package challenge.may;

import java.util.LinkedList;
import java.util.List;

// Interval List Intersections
public class Q986 {


    public int[][] intervalIntersection(int[][] A, int[][] B) {
        int m = A.length, n = B.length;
        int i = 0, j = 0;
        List<int[]> list = new LinkedList<>();
        while(i < m && j < n) {
            int l = Math.max(A[i][0], B[j][0]);
            int h = Math.min(A[i][1], B[j][1]);
            if(l <= h) {
                list.add(new int[]{l, h});
            }
            if(A[i][1] < B[j][1]) {
                i++;
            }else {
                j++;
            }
        }
        return list.toArray(new int[list.size()][2]);
    }
}

