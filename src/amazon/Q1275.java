package amazon;
// Find Winner on a Tic Tac Toe Game
public class Q1275 {
    // O(m) O(1)
    public String tictactoe(int[][] moves) {
        int[] row = new int[3];
        int[] col = new int[3];
        int l2r = 0, r2l = 0;
        int n = moves.length;
        for (int i = 0; i < n; i++) {
            int val = i % 2 == 0 ? 1 : -1;
            row[moves[i][0]] += val;
            col[moves[i][1]] += val;
            if (moves[i][0] == moves[i][1]) l2r += val;
            if (moves[i][0] + moves[i][1] == 2) r2l += val;
            if (Math.abs(row[moves[i][0]]) == 3 ||
                    Math.abs(col[moves[i][1]]) == 3 ||
                    Math.abs(l2r) == 3 ||
                    Math.abs(r2l) == 3) return val == 1 ? "A" : "B";
        }
        return n == 9 ? "Draw" : "Pending";

    }
}
