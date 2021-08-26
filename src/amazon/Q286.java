package amazon;

import java.util.LinkedList;
import java.util.Queue;

// Walls and Gates
public class Q286 {
    // O(mn) O(mn)
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0,1}};
    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length, n = rooms[0].length;
        Queue<Integer> queue = new LinkedList<>();
        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                if(rooms[r][c] == 0) {
                    queue.add(r * n + c);
                }
            }
        }
        while(!queue.isEmpty()) {
            int val = queue.poll();
            int r = val / n, c = val % n;
            for(int[] d : dirs) {
                int x = d[0] + r;
                int y = d[1] + c;
//                if(x < 0 || x == m || y < 0 || y == n || rooms[x][y] == -1 || rooms[x][y] <= rooms[r][c]) {
                if(x < 0 || x == m || y < 0 || y == n || rooms[x][y] != Integer.MAX_VALUE) {    // use this condition rather than L26.
                    continue;
                }
                rooms[x][y] = rooms[r][c] + 1;
                queue.add(x * n + y);
            }
        }
    }
}
