package amazon;
// Game of Life
public class Q289 {
    // O(mn) O(1)
    int m, n;
    public void gameOfLife(int[][] board) {
        m = board.length;
        n = board[0].length;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int count = getCount(board, i, j);
                if(board[i][j] == 1 && count >= 2 && count <= 3) {
                    board[i][j] = 3;
                }else if(board[i][j] == 0 && count == 3) {
                    board[i][j] = 2;
                }
            }
        }
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                board[i][j] >>= 1;
            }
        }
    }
    private int getCount(int[][] board, int i, int j) {
        int count = 0;
        for(int r = i-1; r <= i+1; r++) {
            for(int c = j-1; c <= j+1; c++) {
                if(r >= 0 && r < m && c >= 0 && c < n) {
                    count += board[r][c] & 1;
                }
            }
        }
        count -= board[i][j] & 1;
        return count;
    }
}
