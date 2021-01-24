package google;

import java.util.*;

// Course Schedule
public class Q207 {

    public static void main(String[] args) {
        Q207 q = new Q207();
        int[][] pres = {{1,0}, {2,0}, {3,1}, {3,2}};
        System.out.println(q.canFinish(4, pres));
    }

    //  O(V+E) O(V+E)

    // BFS
    public boolean canFinish1(int numCourses, int[][] pres) {
        if(pres == null || pres.length == 0) {
            return true;
        }
        List<Integer>[] graph = new ArrayList[numCourses];
        int[] indegree = new int[numCourses];
        int count = 0;
        for(int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int[] p : pres) {
            graph[p[1]].add(p[0]);
            indegree[p[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++) {
            if(indegree[i] == 0) {
                queue.add(i);
            }
        }
        while(!queue.isEmpty()) {
            int u = queue.poll();
            count++;
            for(int v : graph[u]) {
                if(--indegree[v] == 0) {
                    queue.add(v);
                }
            }
        }

        return count == numCourses;
    }
    // DFS
    public boolean canFinish(int numCourses, int[][] pres) {
        if(pres == null || pres.length == 0) {
            return true;
        }
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        int[] visited = new int[numCourses];
        for(int[] p : pres) {
            graph.computeIfAbsent(p[1], x -> new HashSet<>()).add(p[0]);
        }
        for(int i = 0; i < numCourses; i++) {
            if(!dfs(graph, visited, i)) {
                return false;
            }
        }
        return true;
    }
    private boolean dfs(Map<Integer, Set<Integer>> graph, int[] visited, int i) {
        if(visited[i] == 2) {
            return true;
        }
        if(visited[i] == 1) {
            return false;
        }
        visited[i] = 1;
        if(graph.containsKey(i)) {
            for(int v : graph.get(i)) {
                if(!dfs(graph, visited, v)) {
                    return false;
                }
            }
        }
        visited[i] = 2;
        return true;
    }

}
