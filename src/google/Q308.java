package google;
// Range Sum Query 2D - Mutable
public class Q308 {
    // Segment Tree
    class NumMatrix {

        int[] tree;
        int[][] matrix;
        int len, m, n;
        // O(m*n) O(2*m*n)
        public NumMatrix(int[][] matrix) {
            if(matrix == null || matrix.length == 0) {
                return;
            }
            m = matrix.length;
            n = matrix[0].length;
            len = m * n;
            int index = len;
            tree = new int[2 * len];
            this.matrix = matrix;
            for(int[] row : matrix) {
                for(int val : row) {
                    tree[index++] = val;
                }
            }

            for(int i = len - 1; i > 0; i--) {
                tree[i] = tree[2 * i] + tree[2 * i + 1];
            }
        }
        // O(lg(m*n)
        public void update(int row, int col, int val) {
            this.matrix[row][col] = val;
            int index = row * n + col;
            update(index, val);
        }
        // O(m*lgn)
        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sum = 0;
            for(int r = row1; r <= row2; r++) {
                sum += query(r * n + col1, r * n + col2);
            }
            return sum;
        }
        private void update(int index, int val) {
            index += len;
            int diff = val - tree[index];
            tree[index] = val;
            while(index > 1) {
                index /= 2;
                tree[index] += diff;
            }
        }

        private int query(int l, int r) {
            int sum = 0;
            l += len;
            r += len;
            while(l <= r) {
                if((l & 1) == 1) {
                    sum += tree[l++];
                }
                if((r & 1) == 0) {
                    sum += tree[r--];
                }
                l /= 2;
                r /= 2;
            }
            return sum;
        }

    }
    // BIT
    class NumMatrix1 {
        int[][] data;
        int[] bit;
        int m, n, len;
        public NumMatrix1(int[][] matrix) {
            if(matrix == null || matrix.length == 0) {
                return;
            }
            data = matrix;
            m = matrix.length;
            n = matrix[0].length;
            len = m * n;
            bit = new int[len + 1];

            int index = 0;
            for(int r = 0; r < m; r++) {
                for(int c = 0; c < n; c++) {
                    index = r * n + c + 1;
                    bit[index] += matrix[r][c];
                    int p = index + (index & -index);   //注意这里
                    if(p <= len) {
                        bit[p] += bit[index];           //注意这里
                    }
                }
            }
        }
        // O(n)
        public void update(int row, int col, int val) {
            int diff = val - data[row][col];
            update(row * n + col + 1, diff);
            data[row][col] = val;
        }
        // O(nlgn)
        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sum = 0;
            for(int r = row1; r <= row2; r++) {
                sum += query(r * n + col2 + 1) - query(r * n + col1);
            }
            return sum;
        }

        private void update(int i, int diff) {
            while(i <= len) {
                bit[i] += diff;
                i += i & -i;
            }
        }

        private int query(int i) {
            int sum = 0;
            while(i > 0) {
                sum += bit[i];
                i -= i & -i;
            }
            return sum;
        }
    }
    // 2D - BIT
    class NumMatrix2 {
        private int rows;
        private int cols;
        private int[][] bit; // The BIT matrix

        private int lsb(int n) {    // least significant bit
            // the line below allows us to directly capture the right most non-zero bit of a number
            return n & (-n);
        }

        private void updateBIT(int r, int c, int val) {
            // keep adding lsb(i) to i, lsb(j) to j and add val to bit[i][j]
            // Using two nested for loops, one for the rows and one for the columns
            for (int i = r; i <= rows; i += lsb(i)) {
                for (int j = c; j <= cols; j += lsb(j)) {
                    this.bit[i][j] += val;
                }
            }
        }
        // O(lgn * lgm)
        private int queryBIT(int r, int c) {
            int sum = 0;
            // keep subtracting lsb(i) to i, lsb(j) to j and obtain the final sum as the sum of non-overlapping sub-rectangles
            // Using two nested for loops, one for the rows and one for the columns
            for (int i = r; i > 0; i -= lsb(i)) {
                for (int j = c; j > 0; j -= lsb(j)) {
                    sum += this.bit[i][j];
                }
            }
            return sum;
        }
        // O(mn * lgm * lgn)
        private void buildBIT(int[][] matrix) {
            for (int i = 1; i <= rows; ++i) {
                for (int j = 1; j <= cols; ++j) {
                    // call update function on each of the entries present in the matrix
                    int val = matrix[i - 1][j - 1];
                    updateBIT(i, j, val);
                }
            }
        }

        public NumMatrix2(int[][] matrix) {
            rows = matrix.length;
            if (rows == 0) return;
            cols = matrix[0].length;
            bit = new int[rows + 1][];
            // Using 1 based indexing, hence resizing the bit array to (rows + 1, cols + 1)
            for (int i = 1; i <= rows; ++i)
                bit[i] = new int[cols + 1];
            buildBIT(matrix);
        }
        //O(lgn * lgm)
        public void update(int row, int col, int val) {
            int old_val = sumRegion(row, col, row, col);
            // handling 1-based indexing
            row++; col++;
            int diff = val - old_val;
            updateBIT(row, col, diff);
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            // handling 1-based indexing
            row1++; col1++; row2++; col2++;
            int a = queryBIT(row2, col2);
            int b = queryBIT(row1 - 1, col1 - 1);
            int c = queryBIT(row2, col1 - 1);
            int d = queryBIT(row1 - 1, col2);
            return (a + b) - (c + d);
        }
    };
}
