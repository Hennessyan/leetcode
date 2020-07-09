package challenge.may;

import java.util.*;

// Possible Bipartition
// Q785(M)
public class Q886 {

    List<Integer>[] graph;
    int[] group;

    // O(N + dislikes.len) O(N + dislikes.len)
    public boolean possibleBipartition(int N, int[][] dislikes) {
        graph = new ArrayList[N + 1];
        group = new int[N + 1];
        Arrays.fill(group, -1); // no assigned group
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] d : dislikes) {
            graph[d[0]].add(d[1]);
            graph[d[1]].add(d[0]);
        }
        for (int i = 1; i <= N; i++) {
            if (group[i] == -1 && !dfs(i, 0)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int i, int flag) {
        group[i] = flag;
        for (int v : graph[i]) {
            if (group[v] == flag) {
                return false;
            }
            if (group[v] == - 1 && !dfs(v, flag ^ 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean possibleBipartition1(int N, int[][] dislikes) {
        graph = new ArrayList[N + 1];
        group = new int[N + 1];
        Arrays.fill(group, -1); // no assigned group
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] d : dislikes) {
            graph[d[0]].add(d[1]);
            graph[d[1]].add(d[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (group[i] == -1) {
                queue.add(i);
                group[i] = 0;
                while (!queue.isEmpty()) {
                    int size = queue.size();
                    for (int j = 0; j < size; j++) {
                        int u = queue.poll();
                        for (int v : graph[u]) {
                            if (group[v] == group[u]) {
                                return false;
                            }
                            if (group[v] == -1) {
                                group[v] = group[u] ^ 1;
                                queue.add(v);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

}
