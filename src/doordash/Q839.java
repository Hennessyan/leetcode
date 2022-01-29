package doordash;

import java.util.LinkedList;
import java.util.Queue;

// Similar String Groups
public class Q839 {

    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        if(n < 2) return n;
        boolean[] seen = new boolean[n];
        int count = 0;
        Queue<String> queue = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            if(!seen[i]) {
                seen[i] = true;
                count++;
                queue.add(strs[i]);
                while(!queue.isEmpty()) {
                    String cur = queue.poll();
                    for(int j = 0; j < n; j++) {
                        if(seen[j]) continue;
                        if(similar(cur, strs[j])) {
                            seen[j] = true;
                            queue.add(strs[j]);
                        }
                    }
                }
            }
        }
        return count;
    }
    private boolean similar(String a, String b) {
        if(a.equals(b)) return true;
        int diff = 0, n = a.length();
        for(int i = 0; i < n; i++) {
            if(a.charAt(i) != b.charAt(i)) {
                if(++diff > 2) return false;
            }
        }
        return diff == 2;
    }
    // O(n^2m) O(n)
    public int numSimilarGroups1(String[] A) {
        int len = A.length;
        UnionFind uf = new UnionFind(len);
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (isSimilar(A[i],A[j])) uf.union(i,j);
            }
        }
        return uf.size();
    }

    public boolean isSimilar(String a, String b) {
        int res = 0, len = a.length();
        for (int i = 0; i < len; i++)
            if (a.charAt(i) != b.charAt(i) && ++res > 2)
                return false;
        return true;
    }
    class UnionFind {
        int[] p, rank;
        int num;
        public UnionFind(int num){
            p = new int[num];
            for (int i = 0; i < num; i++) p[i] = i;
            rank = new int[num];
            this.num = num;
        }
        public int find(int index){
            if (p[index] == index) return index;
            int res = find(p[index]);
            p[index] = res;
            return res;
        }
        public void union(int i, int j) {
            int ri = find(i), rj = find(j);
            if (ri == rj) return;
            if (rank[ri] > rank[rj]) p[rj] = ri;
            else {
                p[ri] = rj;
                if (rank[ri] == rank[rj]) rank[rj]++;
            }
            num--;
        }
        public int size(){
            return num;
        }
    }
}
