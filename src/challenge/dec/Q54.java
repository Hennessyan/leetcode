package challenge.dec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Spiral Matrix
public class Q54 {

    public static void main(String[] args) {
        Q54 q = new Q54();
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
//        int[][] matrix = {{1,2,3}};
        List<Integer> res = q.spiralOrder(matrix);
        System.out.println(Arrays.toString(res.toArray()));
    }
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if(matrix == null || matrix.length == 0) {
            return res;
        }
        int rs = 0, re = matrix.length - 1;
        int cs = 0, ce = matrix[0].length - 1;
        while(rs <= re && cs <= ce) {
            for(int c = cs; c <= ce; c++) {
                res.add(matrix[rs][c]);
            }
            for(int r = rs + 1; r <= re; r++) {
                res.add(matrix[r][ce]);
            }
            if(rs < re && cs < ce) {            // avoid single row or single column
                for(int c = ce - 1; c > cs; c--) {
                    res.add(matrix[re][c]);
                }
                for(int r = re; r > rs; r--) {
                    res.add(matrix[r][cs]);
                }
            }
            rs++;
            re--;
            cs++;
            ce--;
        }

        return res;
    }
}
