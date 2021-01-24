package amaon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Course Schedule
public class Q207 {
    // BFS : O(E+V) O(E+V)
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites == null || prerequisites.length == 0) {    //必须有
            return true;
        }
        int[] indegree = new int[numCourses];
        List<Integer>[] graph = new ArrayList[numCourses];  //注意格式
        for(int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int[] pre : prerequisites) {
            indegree[pre[0]]++;
            graph[pre[1]].add(pre[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        int count = 0;
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
        return numCourses == count;
    }
    //DFS : find cycle
    //TC & SC : O(E + V)
    //思路是找cycle,一门课可以有多门先修课,但是只有不会构成cycle,就总能被上完.所以简单的查看是否有环就可以了.
    private List<Integer>[] graph;
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        if(prerequisites == null || prerequisites.length == 0) {
            return true;
        }
        int[] status = new int[numCourses];
        graph = new ArrayList[numCourses];
        for(int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int[] pre : prerequisites) {
            graph[pre[1]].add(pre[0]);
        }
        for(int i = 0; i < numCourses; i++) {
            if(!dfs(i, status)) {
                return false;
            }
        }
        return true;
    }
    private boolean dfs(int i, int[] status) {
        if(status[i] == 2) {
            return true;
        } else if(status[i] == 1) {
            return false;
        }
        status[i] = 1;
        for(int v : graph[i]) {
            if(!dfs(v, status)) {
                return false;
            }
        }
        status[i] = 2;
        return true;
    }
}
