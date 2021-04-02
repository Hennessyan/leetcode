package google;
// Sudoku Solver
public class Q37 {
    // O((9!)^9) O(1) -> 9! per row
    public void solveSudoku(char[][] board) {
        if(board == null || board.length == 0){
            return;
        }
        solve(board);
    }
    private boolean solve(char[][] board){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] == '.'){
                    for(char c = '1'; c <= '9'; c++){
                        if(isValid(board, i, j, c)){
                            board[i][j] = c;
                            if(solve(board)){
                                return true;
                            }
                            board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    private boolean isValid(char[][] board, int i, int j, char c){
        for(int k = 0; k < 9; k++){
            if(board[k][j] != '.' && board[k][j] == c){	//可以没有前半部分'.'的判断
                return false;
            }
            if(board[i][k] != '.' && board[i][k] == c){
                return false;
            }
            if(board[3*(i/3)+k/3][3*(j/3)+k%3] != '.' && board[3*(i/3)+k/3][3*(j/3)+k%3] == c){
                return false;
            }
        }
        return true;
    }
    // O((9!)^9) O(1)
    char[][] board;
    int[][] row = new int[9][10];
    int[][] col = new int[9][10];
    int[][] box = new int[9][10];
    boolean solved = false;

    public void solveSudoku2(char[][] board) {
        this.board = board;
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j] != '.') {
                    setNumber(i, j, board[i][j] - '0');
                }
            }
        }

        backtrack(0, 0);
    }
    private void backtrack(int i, int j) {
        if(board[i][j] == '.') {
            for(int k = 1; k < 10; k++) {
                if(isValidNumber(i, j, k)) {
                    setNumber(i, j, k);
                    putNextNumber(i, j);
                    if(!solved) {
                        removeNumber(i, j, k);
                    }
                }
            }
        } else {
            putNextNumber(i, j);
        }
    }
    private void putNextNumber(int i, int j) {
        if(i == 8 && j == 8) {
            solved = true;
        } else {
            if(j == 8) {
                backtrack(i + 1, 0);
            } else {
                backtrack(i, j + 1);
            }
        }
    }
    private boolean isValidNumber(int i, int j, int k) {
        int index = i / 3 * 3 + j / 3;
        return row[i][k] + col[j][k] + box[index][k] == 0;
    }
    private void setNumber(int i, int j, int val) {
        row[i][val]++;
        col[j][val]++;
        int index = i / 3 * 3 + j / 3;
        box[index][val]++;
        board[i][j] = (char) (val + '0');
    }
    private void removeNumber(int i, int j, int val) {
        row[i][val]--;
        col[j][val]--;
        int index = i / 3 * 3 + j / 3;
        box[index][val]--;
        board[i][j] = '.';  // backtrack will check if it's . again, so we need to reset it if current round is invalid.
    }
}
