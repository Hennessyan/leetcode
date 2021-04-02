package amazon;
// N-Queens II
public class Q52 {
    // O(n!) O(n)
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
            int indexl = row - c + n;
            int indexr = row + c;
            if(cols[c] || dl[indexl] || dr[indexr]) {
                continue;
            }
            cols[c] = true; dl[indexl] = true; dr[indexr] = true;
            helper(row + 1, n, cols, dl, dr);
            cols[c] = false; dl[indexl] = false; dr[indexr] = false;
        }
    }
}
