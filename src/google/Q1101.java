package google;

import java.util.Arrays;

// The Earliest Moment When Everyone Become Friends
public class Q1101 {
    // O(nlgn) O(n)
    public int earliestAcq(int[][] logs, int N) {
        if(logs.length < N - 1) {
            return -1;
        }
        UnionFind uf = new UnionFind(N);
        Arrays.sort(logs, (l1, l2) -> l1[0] - l2[0]);
        for(int[] log : logs) {
            uf.union(log[1], log[2]);
            if(uf.count == 1) {
                return log[0];
            }
        }
        return -1;
    }
    class UnionFind {
        int[] parent;
        int[] rank;
        int count;
        public UnionFind(int N) {
            parent = new int[N];
            rank = new int[N];
            count = N;
            for(int i = 0; i < N; i++) {
                parent[i] = i;
            }
        }
        public void union(int n1, int n2) {
            int r1 = find(n1);
            int r2 = find(n2);
            if(r1 != r2) {
                count--;
                if(rank[r1] == rank[r2]) {
                    parent[r2] = r1;
                    rank[r1]++;
                } else if(rank[r1] > rank[r2]) {
                    parent[r2] = r1;
                } else {
                    parent[r1] = r2;
                }
            }
        }
        public int find(int n) {
            if(n != parent[n]) {
                parent[n] = find(parent[n]);
            }
            return parent[n];
        }
    }
}
