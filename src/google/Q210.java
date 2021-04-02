package google;

import java.util.*;

// Course Schedule II
public class Q210 {

    public static void main(String[] args) {
        Q210 q = new Q210();
        int[][] pres = {{1,0}, {2,0}, {3,1}, {3,2}};
        int[] res = q.findOrder(4, pres);
        System.out.println(Arrays.toString(res));
    }

    // O(V+E) O(V+E)

    // DFS + LIST
    Map<Integer, List<Integer>> graph;
    List<Integer> courses;
    int[] visited;
    public int[] findOrder(int numCourses, int[][] pres) {
        graph = new HashMap<>();
        courses = new ArrayList<>();
        visited = new int[numCourses];
        for(int[] p : pres) {
            // Can use list for courses, then no need to reverse order.
            graph.computeIfAbsent(p[0], x -> new ArrayList<>()).add(p[1]);  //注意是P[0], P[1]
        }

        for(int i = 0; i < numCourses; i++) {
            if(!dfs(i)) {
                return new int[0];
            }
        }

        int[] res = new int[numCourses];
        int i = 0;
        for(int c : courses) {
            res[i++] = c;
        }
        return res;
    }
    private boolean dfs(int u) {
        if(visited[u] == 2) {
            return true;
        }
        if(visited[u] == 1) {
            return false;
        }
        if(graph.containsKey(u)) {
            visited[u] = 1;
            for(int v : graph.get(u)) {
                if(!dfs(v)) {
                    return false;
                }
            }
        }
        visited[u] = 2;
        courses.add(u); // LIST 而不是STACK因为先加入的是先修课 之前一定没有更"先"的了.
        return true;
    }

    // DFS + STACK
    Stack<Integer> stack;
    public int[] findOrder1(int numCourses, int[][] pres) {
        graph = new HashMap<>();
        stack = new Stack<>();
        visited = new int[numCourses];
        for(int[] p : pres) {
            // Need get res array with stack.
            graph.computeIfAbsent(p[1], x -> new ArrayList<>()).add(p[0]);  //注意是P[1], P[0]
        }

        for(int i = 0; i < numCourses; i++) {
            if(!dfs1(i)) {  // i 如果有先修课,那么在之后会PUSH到STACK中,没有的话正好不管.
                return new int[0];
            }
        }

        int[] res = new int[numCourses];
        int i = 0;
        while(!stack.isEmpty()) {
            res[i++] = stack.pop();
        }
        return res;
    }
    private boolean dfs1(int u) {
        if(visited[u] == 2) {
            return true;
        }
        if(visited[u] == 1) {
            return false;
        }
        if(graph.containsKey(u)) {
            visited[u] = 1;
            for(int v : graph.get(u)) {
                if(!dfs1(v)) {
                    return false;
                }
            }
        }
        visited[u] = 2;
        stack.push(u);
        return true;
    }

    // BFS
    public int[] findOrder2(int numCourses, int[][] pres) {
        List<Integer>[] graph = new ArrayList[numCourses];
        int[] indegree = new int[numCourses];
        int[] res = new int[numCourses];
        int index = 0;
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
            res[index++] = u;

            for(int v : graph[u]) {
                if(--indegree[v] == 0) {
                    queue.add(v);
                }
            }
        }

        if(index < numCourses) {
            return new int[0];
        }
        return res;
    }
}
