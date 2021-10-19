package google;

import java.util.*;

// Shortest Path in a Grid with Obstacles Elimination
// Q1730, Q1091
public class Q1293 {

    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    // BFS : O(nk) O(nk)
    public int shortestPath(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][][] seen = new int[m][n][k + 1];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, k});
        seen[0][0][k] = 1;
        int step = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int[] t = queue.poll();
                if(t[0] == m - 1 && t[1] == n - 1) return step;
                for(int[] d : dirs) {
                    int x = t[0] + d[0];
                    int y = t[1] + d[1];
                    if(x < 0 || x == m || y < 0 || y == n) {
                        continue;
                    }
                    int K = t[2] - grid[x][y];
                    if(K >= 0 && seen[x][y][K] == 0) {
                        seen[x][y][K] = 1;
                        queue.add(new int[]{x, y, K});
                    }
                }
            }
            step++;
        }
        return -1;
    }
    // A* : O(nklgnk) O(nk)
    int m, n, dr, dc;
    public int shortestPath1(int[][] grid, int k) {
        this.m = grid.length;
        this.n = grid[0].length;
        if(k >= m + n - 2) {
            return m + n - 2;
        }
        this.dr = this.m - 1;
        this.dc = this.n - 1;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Set<Node> seen = new HashSet<>();
        pq.offer(new Node(0, 0, k, 0, dist(0, 0)));
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(cur.est - cur.step <= cur.k) {
                return cur.step;
            }
            if(!seen.add(cur)) continue;
            for(int[] d : dirs) {
                int x = cur.r + d[0];
                int y = cur.c + d[1];
                int tk = cur.k;
                if(x < 0 || x == m || y < 0 || y == n) continue;
                tk -= grid[x][y];
                Node next = new Node(x, y, tk, cur.step + 1, cur.step + dist(x, y) + 1);
                if(tk >= 0 && !seen.contains(next)) {
                    pq.offer(next);
                }
            }
        }
        return -1;
    }
    private int dist(int row, int col) {
        return this.dr - row + this.dc - col;
    }
    class Node implements Comparable<Node> {
        int r;
        int c;
        int k;
        int step;
        int est;
        public Node(int r , int c, int k, int step, int est) {
            this.r = r;
            this.c = c;
            this.k = k;
            this.step = step;
            this.est = est;
        }
        @Override
        public int hashCode() {
            return (this.r + 1) * (this.c + 1) * this.k;
        }
        // be careful: equals(Object o)
        @Override
        public boolean equals(Object o) {
            Node that = (Node) o;
            return this.r == that.r && this.c == that.c && this.k == that.k;
        }
        @Override
        public int compareTo(Node that) {
            return this.est - that.est;
        }
    }
}
