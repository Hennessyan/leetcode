package amazon;
// Word Search
public class Q79 {
    // O(mn*4*3^(min(k-1, mn-1)) O(k)
    int m, n;
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(find(board, i, j, 0, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean find(char[][] board, int i, int j, int index, String word) {
        if(index == word.length()) {
            return true;
        }
        if(i < 0 || i == m || j < 0 || j == n || board[i][j] != word.charAt(index)) {
            return false;
        }
        board[i][j] ^= 256;
        for(int[] d : dirs) {
            int x = d[0] + i;
            int y = d[1] + j;
            if(find(board, x, y, index + 1, word)) {
                return true;
            }
        }
        board[i][j] ^= 256;
        return false;
    }
}
