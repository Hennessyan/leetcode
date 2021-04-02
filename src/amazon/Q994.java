package amazon;

import java.util.LinkedList;
import java.util.Queue;

// Rotting Oranges
public class Q994 {
    // O(n) O(n) - BFS
    private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0,1}};
    public int orangesRotting(int[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }
        Queue<Integer> queue = new LinkedList<>();
        int r = grid.length, c = grid[0].length;
        int fresh = 0, count = -1;
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(grid[i][j] == 2) {
                    queue.offer(i*c + j);
                } else if(grid[i][j] == 1) {
                    fresh++;
                }
            }
        }
        if(queue.size() == 0 && fresh == 0) {
            return 0;
        }
        while(!queue.isEmpty()) {
            int size = queue.size();
            count++;
            for(int k = 0; k < size; k++) {
                int val = queue.poll();
                for(int[] dir : dirs) {
                    int x = dir[0] + val / c;
                    int y = dir[1] + val % c;
                    if(x >= 0 && x < r && y >= 0 && y < c && grid[x][y] == 1) {
                        grid[x][y] = 2;
                        queue.offer(x*c + y);
                        fresh--;
                    }
                }
            }

        }
        return fresh == 0 ? count : -1;
    }
    // O(n^2) O(1)
    class Solution {
        // run the rotting process, by marking the rotten oranges with the timestamp
        public boolean runRottingProcess(int timestamp, int[][] grid, int ROWS, int COLS) {
            int[][] directions = { {-1, 0}, {0, 1}, {1, 0}, {0, -1}};
            // flag to indicate if the rotting process should be continued
            boolean toBeContinued = false;
            for (int row = 0; row < ROWS; ++row)
                for (int col = 0; col < COLS; ++col)
                    if (grid[row][col] == timestamp)
                        // current contaminated cell
                        for (int[] d : directions) {
                            int nRow = row + d[0], nCol = col + d[1];
                            if (nRow >= 0 && nRow < ROWS && nCol >= 0 && nCol < COLS)
                                if (grid[nRow][nCol] == 1) {
                                    // this fresh orange would be contaminated next
                                    grid[nRow][nCol] = timestamp + 1;
                                    toBeContinued = true;
                                }
                        }
            return toBeContinued;
        }

        public int orangesRotting(int[][] grid) {
            int ROWS = grid.length, COLS = grid[0].length;
            int timestamp = 2;
            while (runRottingProcess(timestamp, grid, ROWS, COLS))
                timestamp++;

            // end of process, to check if there are still fresh oranges left
            for (int[] row : grid)
                for (int cell : row)
                    // still got a fresh orange left
                    if (cell == 1)
                        return -1;


            // return elapsed minutes if no fresh orange left
            return timestamp - 2;
        }
    }
}
