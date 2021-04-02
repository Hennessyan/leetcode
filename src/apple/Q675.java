package apple;

import java.util.*;

// Cut Off Trees for Golf Event
public class Q675 {
    // BFS : O((RC)^2) O(RC)
    public int cutOffTree(List<List<Integer>> forest) {
        List<int[]> trees = new ArrayList<>();
        for(int i = 0; i < forest.size(); i++) {
            List<Integer> tmp = forest.get(i);
            for(int j = 0; j < tmp.size(); j++) {
                if(tmp.get(j) > 1) {
                    trees.add(new int[]{i, j});
                }
            }
        }
        Collections.sort(trees, (a,b) -> forest.get(a[0]).get(a[1]) - forest.get(b[0]).get(b[1]));
        int steps = 0, sr = 0, sc = 0;
        for(int[] tree : trees) {
            int step = bfs(forest, sr, sc, tree);
            if(step == -1) {
                return -1;
            }
            sr = tree[0];
            sc = tree[1];
            steps += step;
        }
        return steps;
    }
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int bfs(List<List<Integer>> forest, int r, int c, int[] tree) {
        int m = forest.size(), n = forest.get(0).size();
        boolean[][] seen = new boolean[m][n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(r * n + c);
        seen[r][c] = true;
        int step = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int val = queue.poll();
                int r1 = val / n;
                int c1 = val % n;
                if(r1 == tree[0] && c1 == tree[1]) {    // （0，0）就是2 那么这一步STEP=0.
                    return step;
                }
                for(int[] d : dirs) {
                    int x = r1 + d[0];
                    int y = c1 + d[1];
                    if(x >= 0 && x < m && y >= 0 && y < n && !seen[x][y] && forest.get(x).get(y) != 0) {
                        seen[x][y] = true;
                        queue.add(x * n + y);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    //https://leetcode.com/problems/cut-off-trees-for-golf-event/solution/
    // node.f = node.g + node.h
    // a* algorithm : should be O((RC)^2*lg(RC)), and we don't need node.h actually.
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    public int cutOffTree1(List<List<Integer>> forest) {
        List<int[]> trees = new ArrayList();
        for (int r = 0; r < forest.size(); ++r) {
            for (int c = 0; c < forest.get(0).size(); ++c) {
                int v = forest.get(r).get(c);
                if (v > 1) trees.add(new int[]{v, r, c});
            }
        }

        Collections.sort(trees, (a, b) -> Integer.compare(a[0], b[0]));

        int ans = 0, sr = 0, sc = 0;
        for (int[] tree: trees) {
            int d = dist(forest, sr, sc, tree[1], tree[2]);
            if (d < 0) return -1;
            ans += d;
            sr = tree[1]; sc = tree[2];
        }
        return ans;
    }
    public int dist(List<List<Integer>> forest, int sr, int sc, int tr, int tc) {
        int R = forest.size(), C = forest.get(0).size();
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>(
                (a, b) -> Integer.compare(a[0], b[0]));
        heap.offer(new int[]{0, 0, sr, sc});

        HashMap<Integer, Integer> cost = new HashMap();
        cost.put(sr * C + sc, 0);

        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            int g = cur[1], r = cur[2], c = cur[3];
            if (r == tr && c == tc) return g;
            for (int di = 0; di < 4; ++di) {
                int nr = r + dr[di], nc = c + dc[di];
                if (0 <= nr && nr < R && 0 <= nc && nc < C && forest.get(nr).get(nc) > 0) {
                    int ncost = g + 1;  // + Math.abs(nr-tr) + Math.abs(nc-tr);
                    if (ncost < cost.getOrDefault(nr * C + nc, 9999)) {
                        cost.put(nr * C + nc, ncost);
                        heap.offer(new int[]{ncost, g+1, nr, nc});  // arr[0] == arr[1], just keep first one is enough
                    }
                }
            }
        }
        return -1;
    }
}
