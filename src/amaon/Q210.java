package amaon;

import java.util.*;

// Course Schedule II
public class Q210 {
    // BFS : O(E + V)
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //不能有判断prerequisites == null 返回int[0]的条件.
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] indegree = new int[numCourses];
        int[] res = new int[numCourses];

        for(int[] p : prerequisites) {
            indegree[p[0]]++;
            graph.computeIfAbsent(p[1], x -> new ArrayList<>()).add(p[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        int index = 0;
        for(int i = 0; i < numCourses; i++) {
            if(indegree[i] == 0) {
                queue.add(i);
            }
        }

        while(!queue.isEmpty()) {
            int u = queue.poll();
            res[index++] = u;
            if(graph.containsKey(u)) {
                for(int v : graph.get(u)) {
                    if(--indegree[v] == 0) {
                        queue.add(v);
                    }
                }
            }
        }
        return index == numCourses ? res : new int[0];
    }
    // DFS : O(E + V)
    Deque<Integer> stack;
    List<Integer>[] graph;
    int[] status;
    public int[] findOrder1(int numCourses, int[][] prerequisites) {
        graph = new ArrayList[numCourses];
        status = new int[numCourses];
        stack = new ArrayDeque<>();

        for(int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int[] p : prerequisites) {
            graph[p[1]].add(p[0]);
        }
        for(int i = 0; i < numCourses; i++) {
            if(!dfs(i)) {
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
    private boolean dfs(int i) {
        if(status[i] == 2) {
            return true;
        } else if(status[i] == 1) {
            return false;
        }
        status[i] = 1;
        for(int v : graph[i]) {
            if(!dfs(v)) {
                return false;
            }
        }
        status[i] = 2;
        stack.push(i);  //只要到这里就保证了I是"最后的"点了,不会再是其他点的prerequisites
        return true;
    }
}
