package google;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

// Shortest Path in Binary Matrix
public class Q1091 {
    // https://leetcode.com/problems/shortest-path-in-binary-matrix/solution/


    // O(n) O(n)
    private int[][] dirs = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    // BFS assigns distances to cells that represent the shortest path from the top-left corner to that cell
    // 可以看作以此为"ESTIMATE"来决定对谁来继续下一步,但是有可能它往后的结果离终点越来越远.
    public int shortestPathBinaryMatrix1(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0][0] == 1) {   // corner case !!!
            return -1;
        }
        int m = grid.length, n = grid[0].length;
        int step = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        grid[0][0] = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int tmp = queue.poll();
                int r = tmp / n;
                int c = tmp % n;
                if(r == m - 1 && c == n - 1) {
                    return step;
                }
                for(int[] d : dirs) {
                    int x = d[0] + r;
                    int y = d[1] + c;
                    if(x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 0) {
                        grid[x][y] = 1;
                        queue.add(x * n + y);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    // A* - O(nlgn) O(n)
    int m, n;
    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0][0] == 1) {
            return -1;
        }
        m = grid.length;
        n = grid[0].length;
        PriorityQueue<Candidate> pq = new PriorityQueue<>((c1, c2) -> c1.est - c2.est);
        pq.add(new Candidate(0, 0, 1, getEstimate(0, 0)));
        while(!pq.isEmpty()) {
            Candidate c = pq.poll();
            if(c.x == m - 1 && c.y == n - 1) {
                return c.step;
            }
            if(grid[c.x][c.y] == 1) {
                continue;
            }
            grid[c.x][c.y] = 1;
            for(int[] d : dirs) {
                int x = c.x + d[0];
                int y = c.y + d[1];
                if(x < 0 || y < 0 || x >= m || y >= n || grid[x][y] == 1) {
                    continue;
                }
                int step = c.step + 1;
                int est = step + getEstimate(x, y);
                pq.add(new Candidate(x, y, step, est));
            }
        }
        return -1;
    }
    private int getEstimate(int x, int y) {
        return Math.max(m - 1 - x, n - 1 - y);
    }
    class Candidate {
        int x;
        int y;
        int step;
        int est;
        public Candidate(int x, int y, int step, int est) {
            this.x = x;
            this.y = y;
            this.step = step;
            this.est = est;
        }
    }
}
