package challenge.aug;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// The Maze
public class Q490 {

    // DFS : O(mn) O(mn)
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    Set<Integer> set;   // do not ues int[] !!! MLE
    int m, n;
    public boolean hasPath(int[][] maze, int[] start, int[] dest) {
        m = maze.length;
        n = maze[0].length;
        set = new HashSet<>();
        set.add(start[0] * n + start[1]);
        return dfs(maze, start[0], start[1], dest);
    }

    private boolean dfs(int[][] maze, int r, int c, int[] dst) {
        if (dst[0] == r && dst[1] == c) {
            return true;
        }
        for (int[] dir : dirs) {
            int x = r + dir[0];
            int y = c + dir[1];
            while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0) {
                x += dir[0];
                y += dir[1];
            }
            x -= dir[0];
            y -= dir[1];
            if (set.add(x * n + y) && dfs(maze, x, y, dst)) {
                return true;
            }
        }
        return false;
    }
    // BFS
    public boolean hasPath1(int[][] maze, int[] start, int[] dest) {
        m = maze.length;
        n = maze[0].length;
        set = new HashSet<>();
        set.add(start[0] * n + start[1]);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start[0] * n + start[1]);
        while(!queue.isEmpty()) {
            int val = queue.poll();
            int r = val / n;
            int c = val % n;
            if(r == dest[0] && c == dest[1]) {
                return true;
            }
            for(int[] dir : dirs) {
                int x = r + dir[0];
                int y = c + dir[1];
                while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                }
                x -= dir[0];
                y -= dir[1];
                int tmp = x * n + y;
                if (set.add(tmp)) {
                    queue.add(tmp);
                }
            }
        }
        return false;
    }
}
