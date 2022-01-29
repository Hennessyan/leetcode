package linkedin;
// Bomb Enemy
public class Q361 {
    // DP : O(mn) O(n)
    // each node has been visited 3 times -> visit current node + row kills count + col kills count
    public int maxKilledEnemies(char[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int[] colkills = new int[n];
        int rowkills = 0, max = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(j == 0 || grid[i][j-1] == 'W') {
                    rowkills = 0;
                    for(int k = j; k < n && grid[i][k] != 'W'; k++) {
                        if(grid[i][k] == 'E') {
                            rowkills++;
                        }
                    }
                }
                if(i == 0 || grid[i-1][j] == 'W') {
                    colkills[j] = 0;
                    for(int k = i; k < m && grid[k][j] != 'W'; k++) {
                        if(grid[k][j] == 'E') {
                            colkills[j]++;
                        }
                    }
                }
                if(grid[i][j] == '0') {
                    max = Math.max(max, rowkills + colkills[j]);
                }
            }
        }
        return max;
    }
}
