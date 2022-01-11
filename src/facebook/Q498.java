package facebook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Diagonal Traverse
// todo : Q1424
public class Q498 {

    public int[] findDiagonalOrder(int[][] mat) {
        if(mat == null || mat.length == 0) return new int[0];
        int m = mat.length, n = mat[0].length;
        int[] res = new int[m * n];
        List<Integer> list = new ArrayList<>();
        int i = 0;
        for(int d = 0; d < m + n - 1; d++) {
            int r = d < n ? 0 : d - n + 1;
            int c = d < n ? d : n - 1;
            while(r < m && c >= 0) {
                list.add(mat[r][c]);
                r++;
                c--;
            }
            if(d % 2 == 0) {
                Collections.reverse(list);
            }
            for(int val : list) {
                res[i++] = val;
            }
            list.clear();
        }
        return res;
    }
    // O(mn) O(1)
    public int[] findDiagonalOrder1(int[][] mat) {
        if(mat == null || mat.length == 0) return new int[0];
        int m = mat.length, n = mat[0].length;
        int r = 0, c = 0, i = 0, direction = 1;
        int[] res = new int[m * n];
        while(r < m && c < n) {
            res[i++] = mat[r][c];
            int nr = r + (direction == 1 ? -1 : 1);
            int nc = c + (direction == 1 ? 1 : -1);
            if(nr < 0 || nr == m || nc < 0 || nc == n) {
                if(direction == 1) {
                    r += (c == n - 1 ? 1 : 0);
                    c += (c < n - 1 ? 1 : 0);
                } else {
                    // order of L51 and L52 is important, otherwise r is updated already
                    c += (r == m - 1 ? 1 : 0);
                    r += (r < m - 1 ? 1 : 0);
                }
                direction = 1 - direction;
            } else {
                r = nr;
                c = nc;
            }
        }
        return res;
    }
}
