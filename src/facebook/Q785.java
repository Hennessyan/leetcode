package facebook;

import java.util.Stack;

// Is Graph Bipartite?
public class Q785 {

    // O(N + E) O(N)
    public boolean isBipartite(int[][] graph) {
        if(graph == null || graph.length == 0) {
            return true;
        }
        int N = graph.length;
        int[] group = new int[N];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < N; i++) {
            if(group[i] == 0) {
                group[i] = 1;
                stack.push(i);
                while(!stack.isEmpty()) {
                    int u = stack.pop();
                    for(int v : graph[u]) {
                        if(group[v] == group[u]) {
                            return false;
                        }
                        if(group[v] == 0) {
                            group[v] = -group[u];
                            stack.push(v);
                        }
                    }
                }
            }
        }
        return true;
    }
}
