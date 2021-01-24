package challenge.dec;
// spiral-matrix-ii
public class Q59 {

    public int[][] generateMatrix0(int n) {
        int[][] matrix = new int[n][n];
        int val = 1;
        int rs = 0, re = n - 1, cs = 0, ce = n - 1;
        while(rs <= re && cs <= ce) {
            for(int c = cs; c <= ce; c++) {
                matrix[rs][c] = val++;
            }
            for(int r = rs + 1; r <= re; r++) {
                matrix[r][ce] = val++;
            }
            // if(rs < re && cs < ce) {     // do not need this line as it's n * n matrix
            for(int c = ce - 1; c > cs; c--) {
                matrix[re][c] = val++;
            }
            for(int r = re; r > rs; r--) {
                matrix[r][cs] = val++;
            }
            // }
            rs++;
            re--;
            cs++;
            ce--;
        }
        return matrix;
    }

    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        int val = 1, r = 0, c = 0, d = 0;
        while(val <= n * n) {
            matrix[r][c] = val++;
            int tr = (r + dirs[d][0]) % n;
            int tc = Math.floorMod(c + dirs[d][1], n);
            if(matrix[tr][tc] != 0) {
                d = (d + 1) % 4;
            }
            r += dirs[d][0];
            c += dirs[d][1];
        }
        return matrix;
    }
}
