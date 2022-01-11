package google;

import java.util.Arrays;
import java.util.PriorityQueue;

// The Maze III
public class Q499 {

    //DFS O(mn*max(m,n)) O(mn)
    int[][] dirs = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    String[] ways = {"d", "l", "r", "u"};
    int m, n;
    String res = "z";

    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        m = maze.length;
        n = maze[0].length;
        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dist[ball[0]][ball[1]] = 0;
        dfs(maze, ball, hole, dist, "");
        return res.equals("z") ? "impossible" : res;
    }

    private void dfs(int[][] maze, int[] b, int[] h, int[][] dist, String prefix) {
        for (int i = 0; i < 4; i++) {
            int[] d = dirs[i];
            int count = 0;
            String way = prefix + ways[i];
            int x = b[0] + d[0];
            int y = b[1] + d[1];
            while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0) {
                count++;
                if (x == h[0] && y == h[1]) {
                    if (dist[b[0]][b[1]] + count < dist[x][y]) {
                        dist[x][y] = dist[b[0]][b[1]] + count;
                        res = way;
                    } else if (dist[b[0]][b[1]] + count == dist[x][y] && res.compareTo(way) > 0) {
                        res = way;
                    }
                    return;
                }
                x += d[0];
                y += d[1];
            }
            x -= d[0];
            y -= d[1];
            if (dist[b[0]][b[1]] + count < dist[x][y]) {    // don't use <=, first one is best choice.
                dist[x][y] = dist[b[0]][b[1]] + count;
                dfs(maze, new int[]{x, y}, h, dist, way);
            }
        }
    }

    // O(mnlgmn) O(mn)
    // don't use int[][] dist, can't get correct solution without boolean[][] seen.
    public String findShortestWay2(int[][] maze, int[] ball, int[] hole) {
        int m = maze.length;
        int n = maze[0].length;
        // can't use int[][] dist => duplicates here
        boolean[][] seen = new boolean[m][n];
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(ball[0], ball[1], 0, ""));
        int[][] dirs = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
        char[] chs = {'d', 'l', 'r', 'u'};
        // both "lul" and "ul" are added into pq, and small path return first.
        while (!pq.isEmpty()) {
            Point e = pq.poll();
            if (e.row == hole[0] && e.col == hole[1]) {
                return e.path;
            }
             if (seen[e.row][e.col]) {    //需要的！！！
                 continue;
             }
            seen[e.row][e.col] = true;
            for (int i = 0; i < 4; i++) {
                int[] dir = dirs[i];
                int r = e.row;
                int c = e.col;
                int d = e.dist;
                String path = e.path;
                while (r + dir[0] >= 0 && r + dir[0] < m && c + dir[1] >= 0 && c + dir[1] < n && maze[r + dir[0]][c + dir[1]] != 1) {
                    r += dir[0];
                    c += dir[1];
                    d++;
                    if (r == hole[0] && c == hole[1]) {
                        break;
                    }
                }
                if (!seen[r][c]) {
                    pq.add(new Point(r, c, d, path + chs[i]));
                }
            }
        }
        return "impossible";
    }

    class Point implements Comparable<Point> { // sorted by its distance to ball and then lexicographical order
        int row;
        int col;
        int dist;
        String path;

        Point(int row, int col, int dist, String path) {
            this.row = row;
            this.col = col;
            this.dist = dist;
            this.path = path;
        }

        @Override
        public int compareTo(Point o) {
            return this.dist == o.dist ? this.path.compareTo(o.path) : this.dist - o.dist;
        }
    }
}
