package google;

import java.util.*;

// Parallel Courses
// TODO : Q1494(M) PC II
public class Q1136 {
    // BFS (Kahn's algorithm) - O(V+E) O(V+E)
    public int minimumSemesters(int n, int[][] relations) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] indegree = new int[n + 1];
        for(int[] r : relations) {
            map.computeIfAbsent(r[0], x -> new ArrayList<>()).add(r[1]);
            indegree[r[1]]++;
        }

        int semester = 0;
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 1; i <= n; i++) {
            if(indegree[i] == 0) {
                queue.add(i);
            }
        }

        while(!queue.isEmpty()) {
            semester++;
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int u = queue.poll();
                n--;
                if(map.containsKey(u)) {
                    for(int v : map.get(u)) {
                        if(--indegree[v] == 0) {
                            queue.add(v);
                        }
                    }
                }
            }

        }
        return n == 0 ? semester : -1;
    }
    // DFS : O(V+E) O(V+E)
    Map<Integer, List<Integer>> map;
    int[] visited;
    public int minimumSemesters1(int n, int[][] relations) {
        map = new HashMap<>();
        for(int[] r : relations) {
            map.computeIfAbsent(r[0], x -> new ArrayList<>()).add(r[1]);
        }
        visited = new int[n + 1];
        int maxLen = 1;
        for(int i = 1; i <= n; i++) {
            int length = dfs(i);
            if(length == -1) {
                return -1;
            }
            maxLen = Math.max(maxLen, length);
        }
        return maxLen;
    }
    private int dfs(int u) {
        if(visited[u] != 0) {
            return visited[u];
        }
        int max = 1;
        if(map.containsKey(u)) {
            visited[u] = -1;
            for(int v : map.get(u)) {
                int tmp = dfs(v);
                if(tmp == -1) {
                    return -1;
                }
                max = Math.max(max, tmp + 1);
            }
        }
        return visited[u] = max;
    }
}
