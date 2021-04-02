package amazon;
// Design Tic-Tac-Toe
public class Q348 {

    class TicTacToe {
        int n, l2r, r2l;
        int[] rows, cols;
        /** Initialize your data structure here. */
        public TicTacToe(int n) {
            this.n = n;
            this.l2r = 0;
            this.r2l = 0;
            this.rows = new int[n];
            this.cols = new int[n];
        }

        /** Player {player} makes a move at ({row}, {col}).
         @param row The row of the board.
         @param col The column of the board.
         @param player The player, can be either 1 or 2.
         @return The current winning condition, can be either:
         0: No one wins.
         1: Player 1 wins.
         2: Player 2 wins. */
        public int move(int row, int col, int player) {
            int mark = player == 1 ? 1 : -1;
            rows[row] += mark;
            cols[col] += mark;
            if(row == col) {
                l2r += mark;
            }
            if(row + col == n - 1) {
                r2l += mark;
            }
            if(Math.abs(rows[row]) == n || Math.abs(cols[col]) == n || Math.abs(l2r) == n || Math.abs(r2l) == n) {
                return player;
            }
            return 0;
        }
    }
}
