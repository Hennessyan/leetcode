package amazon;

import java.util.*;

// Number of Distinct Islands II
public class Q711 {
    //O(r*c*lg(r*c)) O(r*c)
    //https://leetcode.com/problems/number-of-distinct-islands-ii/discuss/108799/Java-version-time-complexity-worst-case-O(n2-log(n2))
    //开一维数组,重复利用,更好一些:https://leetcode.com/problems/number-of-distinct-islands-ii/solution/
    int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
    int[][] trans = {{1, -1}, {-1, 1}, {1,1}, {-1, -1}};
    public int numDistinctIslands2(int[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        Queue<Integer> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                if(grid[r][c] == 1) {
                    List<Point> points = new ArrayList<>();
                    grid[r][c] = 0;
                    queue.add(r * n + c);
                    points.add(new Point(r, c));
                    while(!queue.isEmpty()) {
                        int val = queue.poll();
                        int x1 = val / n;
                        int y1 = val % n;
                        for(int[] d : dirs) {
                            int x2 = d[0] + x1;
                            int y2 = d[1] + y1;
                            if(x2 >= 0 && x2 < m && y2 >= 0 && y2 < n && grid[x2][y2] == 1) {
                                grid[x2][y2] = 0;
                                queue.add(x2 * n + y2);
                                points.add(new Point(x2, y2));
                            }
                        }
                    }
                    set.add(helper(points));
                }
            }
        }

        return set.size();
    }
    private String helper(List<Point> points) {
        List<Point>[] islands = new ArrayList[8];
        for(int i = 0; i < 4; i++) {
            islands[i] = new ArrayList<>();
            islands[i + 4] = new ArrayList<>();
            for(Point p : points) {
                islands[i].add(new Point(p.i * trans[i][0], p.j * trans[i][1]));
                islands[i + 4].add(new Point(p.j * trans[i][0], p.i * trans[i][1]));
            }
        }
        for(List<Point> island :islands) {
            Collections.sort(island);
        }
        String[] strs = new String[8];
        for(int i = 0; i < 8; i++) {
            StringBuilder sb = new StringBuilder();
            Point fp = islands[i].get(0);
            for(int j = 1; j < islands[i].size(); j++) {
                Point p = islands[i].get(j);
                sb.append(p.i - fp.i).append("-").append(p.j - fp.j).append("|");
            }
            strs[i] = sb.toString();
        }
        Arrays.sort(strs);
        return strs[0];
    }
    class Point implements Comparable<Point> {
        int i, j;
        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
        @Override
        public int compareTo(Point that) {
            int val = this.i - that.i;
            return val == 0 ? this.j - that.j : val;
        }
    }
}
