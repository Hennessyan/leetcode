package amazon;

import java.util.*;

// Minimum Height Trees
public class Q310 {
    // method1 : O(N) O(N)
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> list = new ArrayList<>();
        if(n == 1) {
            list.add(0);
            return list;
        }
        List<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            if(graph[i].size() == 1) {
                queue.add(i);
            }
        }

        while(n > 2) {
            int size = queue.size();
            n -= size;

            for(int i = 0; i < size; i++) {
                Integer u = queue.poll();   // we can use int if graph is built by Set.
                int v = graph[u].get(0);
                graph[v].remove(u);
                if(graph[v].size() == 1) {
                    queue.add(v);
                }
            }
        }
        return new ArrayList<>(queue);
    }
    // same as method1.
    public List<Integer> findMinHeightTrees1(int n, int[][] edges) {
        List<Integer> list = new ArrayList<>();
        if(n == 1) {
            list.add(0);
            return list;
        }
        List<Set<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            graph.add(new HashSet<>());
        }
        for(int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            if(graph.get(i).size() == 1) {
                queue.add(i);
            }
        }
        while(n > 2) {
            int size = queue.size();
            n -= size;
            for(int i = 0; i < size; i++) {
                int u = queue.poll();
                int v = graph.get(u).iterator().next(); //只有一个元素
                graph.get(v).remove(u);     //使用set可以不把u转为Integer,list.remove(x) x需要是Integer.
                if(graph.get(v).size() == 1) {
                    queue.add(v);
                }
            }
        }
        while(!queue.isEmpty()) {
            list.add(queue.poll());
        }
        return list;
    }
}
