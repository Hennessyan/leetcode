package amazon;
// N-Queens II
public class Q52 {
    // O(n!) O(n)
    // first row has n choices,
    // second row has n - 2
    // max possible in third row is n - 4
    // => almost O(n!)
    int count = 0;
    public int totalNQueens(int n) {
        boolean[] cols = new boolean[n];
        boolean[] dl = new boolean[2 * n];
        boolean[] dr = new boolean[2 * n];
        helper(0, n, cols, dl, dr);
        return count;
    }
    private void helper(int row, int n, boolean[] cols, boolean[] dl, boolean[] dr) {
        if(row == n) {
            count++;
            return;
        }
        for(int c = 0; c < n; c++) {
            int indexl = row - c + n;   //or c - row + n
            int indexr = row + c;
            if(cols[c] || dl[indexl] || dr[indexr]) {
                continue;
            }
            cols[c] = true; dl[indexl] = true; dr[indexr] = true;
            helper(row + 1, n, cols, dl, dr);
            cols[c] = false; dl[indexl] = false; dr[indexr] = false;
        }
    }

    public int totalNQueens1(int n) {
        boolean[] cols = new boolean[n];
        boolean[] dl = new boolean[2 * n];
        boolean[] dr = new boolean[2 * n];
        helper(0, n, 0, 0, 0);
        return count;
    }
    private void helper(int i, int n, int col, int l2r, int r2l) {
        if(i == n) {
            count++;
            return;
        }
        for(int j = 0; j < n; j++) {

            if((l2r & (1 << (j + n - i))) > 0 || (r2l & (1 << (i + j))) > 0 || (col & (1 << j)) > 0) {
                continue;
            }
            l2r ^= 1 << (j + n - i);
            r2l ^= 1 << (i + j);
            col ^= 1 << j;
            helper(i + 1, n, col, l2r, r2l);
            l2r ^= 1 << (j + n - i);
            r2l ^= 1 << (i + j);
            col ^= 1 << j;
        }
    }
}
