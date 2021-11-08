package amazon;

import javafx.util.Pair;

import java.util.*;

// Min Cost to Connect All Points
public class Q1584 {
    // Kruskal's algorithm : O(n^2 * lgn) O(n^2)
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        if(n < 2) return 0;
        UnionFind uf = new UnionFind(1000);
        List<int[]> list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                list.add(new int[]{i, j, dist(points[i], points[j])});
            }
        }
        Collections.sort(list, (i1, i2) -> Integer.compare(i1[2], i2[2]));
        int cost = 0, total = 0;
        for(int[] e : list) {
            if(uf.union(e[0], e[1])) {
                cost += e[2];
                total++;
                if(total == n - 1) break;
            }
        }
        return cost;
    }
    private int dist(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }

    class UnionFind {
        int[] parent;
        int[] rank;
        public UnionFind(int n) {
            parent = new int[n];
            rank=  new int[n];
            for(int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        public int find(int i) {
            while(i != parent[i]) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
            return i;
        }
        public boolean union(int i1, int i2) {
            int f1 = find(i1);
            int f2 = find(i2);
            if(f1 == f2) return false;
            if(rank[f1] > rank[f2]) {
                parent[f2] = f1;
            } else if(rank[f1] < rank[f2]) {
                parent[f1] = f2;
            } else {
                parent[f2] = f1;
                rank[f1]++;
            }
            return true;
        }
    }

    // Prime's algorithm : O(ElgE) O(V + E) - E = V^2 = n^2
    public int minCostConnectPoints1(int[][] points) {
        int n = points.length;
        if(n < 2) return 0;
        List<List<int[]>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                int d = dist(points[i], points[j]);
                graph.get(i).add(new int[]{j, d});
                graph.get(j).add(new int[]{i, d});
            }
        }
        int cost = 0;
        Set<Integer> seen = new HashSet<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.offer(new int[]{0, 0});
        while(seen.size() != n) {
            int[] cur = pq.poll();
            if(!seen.add(cur[0])) continue;
            cost += cur[1];
            for(int[] next : graph.get(cur[0])) {
                if(seen.contains(next[0])) continue;
                pq.offer(next);
            }
        }
        return cost;
    }

    // Prime's algorithm : O(n^2) O(n)
    // dense graph => can use this way to deduct cost.
    /**
     *
     * 在特殊情况下，Prim有o(N^2)的实现，那就是可以用邻接表来表示任意的边。这样对于稠密图而言，Prim的这种实现在时间上会有优势。
     *
     * 本题是个完全图，任意两点之间都有边，所以是可以有o(N^2)的实现，具体方法是：我们在构建MST的过程中收入了一个新点k，此时收录的点集是{q}.
     * 查看所有未被收入的点pi到k点的距离，用来更新di，其中di就是pi到距离点集{q}最小的长度。
     * 这样我们就可以在{di}找到最小的那个，对应的点就是下一次要收入的新点k'. 因此这样的算法就不要使用priority_queue.
     */
    public int minCostConnectPoints2(int[][] points) {
        int n = points.length;
        if(n < 2) return 0;

        int cur = 0, cost = 0;

        Point[] ps = new Point[n];
        for(int i = 0; i < n; i++) {
            ps[i] = new Point(points[i][0], points[i][1], Integer.MAX_VALUE);
        }
        ps[0].dist = 0;

        while(cur < n) {
            Point p = ps[cur];
            cost += p.dist;

            int next = findNext(ps, cur);
            cur++;

            // last round is -1 as there is no more point can be next start point.
            if(next != -1)
                swap(ps, cur, next);
        }

        return cost;
    }
    private void swap(Point[] ps, int i, int j) {
        Point tmp = ps[i];
        ps[i] = ps[j];
        ps[j] = tmp;
    }
    // we need to save the previous dist as it might be the nex candidate
    // [[0,0],[2,2],[3,10],[5,2],[7,0]]
    // [3,10] is moved, but the min dist is [3,10] and [2,2]
    private int findNext(Point[] ps, int i) {
        int index = -1, min = Integer.MAX_VALUE;
        for(int j = i + 1; j < ps.length; j++) {
            int d = dist(ps[i], ps[j]);
            ps[j].dist = Math.min(ps[j].dist, d);
            if(ps[j].dist < min) {
                min = ps[j].dist;
                index = j;
            }
        }
        return index;
    }
    class Point {
        int x, y, dist;
        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    private int dist(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
}
