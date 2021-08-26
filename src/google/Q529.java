package google;

import java.util.LinkedList;
import java.util.Queue;

// Minesweeper
public class Q529 {
    // O(mn) O(mn)
    char[][] board;
    int m, n;
    int[][] dirs = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        this.board = board;
        this.m = board.length;
        this.n = board[0].length;
        dfs(click[0], click[1]);
        return this.board;
    }
    private void dfs(int r, int c) {
        int total = count(r, c);    // check total count before next round.
        if(total == 0) {
            this.board[r][c] = 'B';
            for(int[] d : dirs) {
                int x = r + d[0];
                int y = c + d[1];
                if(x >= 0 && x < m && y >= 0 && y < n && this.board[x][y] == 'E') {
                    // DFS changes visited node, so this.board[x][y] == 'E' is good enough
                    // to avoid calculating same node multiple times.
                    dfs(x, y);
                }
            }
        } else {
            this.board[r][c] = (char) ('0' + total);    // !!!
        }
    }
    private int count(int r, int c) {
        int total = 0;
        for(int[] d : dirs) {
            int x = r + d[0];
            int y = c + d[1];
            if(x >= 0 && x < m && y >= 0 && y < n
                    && (this.board[x][y] == 'M' || this.board[x][y] == 'X')) {
                total++;
            }
        }
        return total;
    }
    // BFS : O(mn) O(min(m, n))
    public char[][] updateBoard1(char[][] board, int[] click) {
        if(board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        this.board = board;
        this.m = board.length;
        this.n = board[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{click[0], click[1]});
        while(!queue.isEmpty()) {
            int[] p = queue.poll();
//            if(this.board[p[0]][p[1]] != 'E') { // we will add same node into queue multiple times,
//                continue;                       // hence need this condition to avoid duplicated operations.
//            }
            int total = count(p[0], p[1]);    // check total count before next round.
            if(total == 0) {
                this.board[p[0]][p[1]] = 'B';
                for(int[] d : dirs) {
                    int x = p[0] + d[0];
                    int y = p[1] + d[1];
                    if(x >= 0 && x < m && y >= 0 && y < n && this.board[x][y] == 'E') {
                        this.board[x][y] = '9'; // avoid add some node into the queue, then we don't need L63-L65
                        queue.add(new int[]{x, y});
                    }
                }
            } else {
                this.board[p[0]][p[1]] = (char) ('0' + total);    // !!!
            }
        }
        return this.board;
    }
}
