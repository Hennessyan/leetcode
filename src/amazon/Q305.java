package amazon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Number of Islands II
public class Q305 {

    //O(m*n+len) O(m*n)
    //注意这个题没有办法去将island赋1来进行判断,所以在parent那里先初始化-1,然后设置一个island,
    //赋一个index,从而帮助我们进行判断是否为有效的island.
    class UnionFind {
        int count; // # of connected components
        int[] parent;
        int[] rank;

        public UnionFind(int N) { // for problem 305 and others
            count = 0;
            parent = new int[N];
            rank = new int[N];
            for (int i = 0; i < N; ++i) {
                parent[i] = -1;
                rank[i] = 0;
            }
        }

        public boolean isValid(int i) { // for problem 305
            return parent[i] >= 0;
        }

        public void setParent(int i) {
            parent[i] = i;
            ++count;
        }

        public int find(int i) { // path compression
            if (parent[i] != i) parent[i] = find(parent[i]);
            return parent[i];
        }

        public void union(int x, int y) { // union with rank
            int rootx = find(x);
            int rooty = find(y);
            if (rootx != rooty) {
                if (rank[rootx] > rank[rooty]) {
                    parent[rooty] = rootx;
                } else if (rank[rootx] < rank[rooty]) {
                    parent[rootx] = rooty;
                } else {
                    parent[rooty] = rootx; rank[rootx] += 1;
                }
                --count;
            }
        }

        public int getCount() {
            return count;
        }
    }

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        UnionFind uf = new UnionFind(m * n);
        Set<Integer> set = new HashSet<>();	//  处理重复的position的情况.

        for (int[] pos : positions) {
            int r = pos[0], c = pos[1];
            int index = r * n + c;
            if(set.contains(index)) {
                ans.add(uf.getCount());	//  处理重复的position的情况.
                continue;
            }
            set.add(index);
            uf.setParent(index);

            if (r - 1 >= 0 && uf.isValid((r-1) * n + c)) uf.union((r-1)*n+c, index);
            if (r + 1 < m && uf.isValid((r+1) * n + c)) uf.union((r+1)*n+c, index);
            if (c - 1 >= 0 && uf.isValid(r * n + c - 1)) uf.union(r*n+c-1, index);
            if (c + 1 < n && uf.isValid(r * n + c + 1)) uf.union(r*n+c+1, index);


            ans.add(uf.getCount());
        }

        return ans;
    }
}
