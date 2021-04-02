package amazon;
// Valid Tic-Tac-Toe State
public class Q794 {
    // O(1) O(1)
    public boolean validTicTacToe(String[] board) {
        int xc = 0, oc = 0;
        for(String s : board) {
            for(char c : s.toCharArray()) {
                if(c == 'X') {
                    xc++;
                } else if(c == 'O') {
                    oc++;
                }
            }
        }
        if((xc != oc) && oc + 1 != xc) return false;
        if(win(board, 'X') && oc + 1 != xc) return false;
        if(win(board, 'O') && oc != xc) return false;
        return true;
    }
    private boolean win(String[] board, char c) {
        for(int i = 0; i < board.length; i++) {
            if(board[0].charAt(i) == c && board[1].charAt(i) == c && board[2].charAt(i) == c) {
                return true;
            }
            if(board[i].charAt(0) == c && board[i].charAt(1) == c && board[i].charAt(2) == c) {
                return true;
            }
        }
        if(board[0].charAt(0) == c && board[1].charAt(1) == c && board[2].charAt(2) == c) {
            return true;
        }
        if(board[0].charAt(2) == c && board[1].charAt(1) == c && board[2].charAt(0) == c) {
            return true;
        }
        return false;
    }
}
