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
                if(dfs(board, i, j, 0, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int r, int c, int i, String word) {
        if(i == word.length()) return true;
        if(r < 0 || r == m || c < 0 || c == n || board[r][c] != word.charAt(i)) {
            return false;
        }
        board[r][c] ^= 256;
        boolean ans = false;
        for(int[] d : dirs) {
            ans = dfs(board, r + d[0], c + d[1], i + 1, word);
            if(ans) break;
        }
        board[r][c] ^= 256;
        return ans;
    }
}
