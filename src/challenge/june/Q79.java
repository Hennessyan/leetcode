package challenge.june;
// Word Search
public class Q79 {
    int m, n;
    int[][] dirs = {{-1, 0}, {1,0}, {0,1},{0,-1}};

    // O(m*n*4*3^(k-1)) O(k)
    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(dfs(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    // Wrong method, e.g., [[a]] "a"
//    private boolean dfs(char[][] board, int i, int j, String word, int index) {
//        if(index == word.length()) {
//            return true;
//        }
//        if(board[i][j] != word.charAt(index)) {
//            return false;
//        }
//        board[i][j] ^= 256;
//        for(int[] dir : dirs) {
//            int x = dir[0] + i;
//            int y = dir[1] + j;
//            if(x >= 0 && x < m && y >= 0 && y < n && dfs(board, x, y, word, index + 1)) {
//                return true;
//            }
//        }
//        board[i][j] ^= 256;
//        return false;
//    }
    // Correct method
    private boolean dfs(char[][] board, int i, int j, String word, int index) {
        if(index == word.length()) {
            return true;
        }
        if(i < 0 || i >= m || j < 0 || j >= n || word.charAt(index) != board[i][j]) {
            return false;
        }
        board[i][j] ^= 256;
        for(int[] d : dirs) {
            int x = i + d[0];
            int y = j + d[1];
            if(dfs(board, x, y, word, index + 1)) {
                return true;
            }
        }
        board[i][j] ^= 256;
        return false;
    }
}
