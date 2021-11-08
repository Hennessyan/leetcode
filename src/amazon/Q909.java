package amazon;

import java.util.LinkedList;
import java.util.Queue;

// Snakes and Ladders
public class Q909 {
    // O(n^2) O(n^2)
    public int snakesAndLadders(int[][] board) {
        Queue<Integer> queue = new LinkedList<>();
        int n = board.length;
        int[] seen = new int[n * n + 1];
        int step = 0;
        queue.add(1);
        seen[1] = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            step++;
            for(int i = 0; i < size; i++) {
                int cur = queue.poll();
                for(int v = cur + 1; v <= Math.min(cur + 6, n * n); v++) {
                    int x = (v - 1) / n;    // row from bottom to up (image)
                    int y = (v - 1) % n;    // col from left to right (board)
                    int r = n - x - 1;      // row from top to down (baord)
                    int c = x % 2 == 0 ? y : (n - y - 1);   // col for board (left / right direction)

                    int next = board[r][c] != -1 ?  board[r][c] : v;
                    if(next == n * n) return step;
                    if(seen[next] == 0) {
                        seen[next] = 1;
                        queue.add(next);
                    }
                }
            }
        }
        return -1;
    }
}
