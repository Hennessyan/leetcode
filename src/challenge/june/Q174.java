package challenge.june;

import java.util.Arrays;

// Dungeon Game
public class Q174 {

    public static void main(String[] args) {
        Q174 q = new Q174();
        int[][] d = {{-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}};
        System.out.println(q.calculateMinimumHP(d));    // 7
    }

    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        int[] dp = new int[n];
        for(int r = m - 1; r >= 0; r--) {
            for(int c = n - 1; c >= 0; c--) {
                if(r == m - 1 && c == n - 1) {
//                    dungeon[r][c] = dungeon[r][c] < 0 ? dungeon[r][c] : 0;
                    dp[c] = dungeon[r][c] < 0 ? dungeon[r][c] : 0;
                } else if(r == m - 1) {
//                    dungeon[r][c] = dungeon[r][c] + dungeon[r][c + 1] < 0 ? dungeon[r][c] + dungeon[r][c + 1] : 0;
                    dp[c] = dungeon[r][c] + dp[c + 1] < 0 ? dungeon[r][c] + dp[c + 1] : 0;
                } else if(c == n - 1) {
//                    dungeon[r][c] = dungeon[r][c] + dungeon[r + 1][c] < 0 ? dungeon[r][c] + dungeon[r + 1][c] : 0;
                      dp[c] = dungeon[r][c] + dp[c] < 0 ? dungeon[r][c] + dp[c] : 0;
                } else {
//                    int tmp = Math.max(dungeon[r][c + 1], dungeon[r + 1][c]);
//                    dungeon[r][c] = tmp + dungeon[r][c] < 0 ? tmp + dungeon[r][c] : 0;
                    int tmp = Math.max(dp[c], dp[c + 1]);
                    dp[c] = tmp + dungeon[r][c] < 0 ? tmp + dungeon[r][c] : 0;
                }
            }
        }
//        return 1 - dungeon[0][0];
        return 1 - dp[0];
    }

    // method2 : O(m*n) O(m*n) => use dp[m] replace dp[m][n]
    int inf = Integer.MAX_VALUE;
    int[][] dp;
    int rows, cols;

    public int getMinHealth(int currCell, int nextRow, int nextCol) {
        if (nextRow >= this.rows || nextCol >= this.cols)
            return inf;
        int nextCell = this.dp[nextRow][nextCol];
        // hero needs at least 1 point to survive
        return Math.max(1, nextCell - currCell);
    }

    public int calculateMinimumHP1(int[][] dungeon) {
        this.rows = dungeon.length;
        this.cols = dungeon[0].length;
        this.dp = new int[rows][cols];
        for (int[] arr : this.dp) {
            Arrays.fill(arr, this.inf);
        }
        int currCell, rightHealth, downHealth, nextHealth, minHealth;
        for (int row = this.rows - 1; row >= 0; --row) {
            for (int col = this.cols - 1; col >= 0; --col) {
                currCell = dungeon[row][col];

                rightHealth = getMinHealth(currCell, row, col + 1);
                downHealth = getMinHealth(currCell, row + 1, col);
                nextHealth = Math.min(rightHealth, downHealth);

                if (nextHealth != inf) {
                    minHealth = nextHealth;
                } else {
                    minHealth = currCell >= 0 ? 1 : 1 - currCell;
                }
                this.dp[row][col] = minHealth;
            }
        }
        return this.dp[0][0];
    }
}
