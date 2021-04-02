package amazon;

import java.util.*;

// Number of Connected Components in an Undirected Graph
public class Q323 {

    /*method1 BFS*/
    public int countComponents0(int n, int[][] edges) {	//1 []  返回 1，因此不要判断太多edge case,容易出错
        int count = 0;
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }
        for(int[] pair : edges){
            graph.get(pair[0]).add(pair[1]);
            graph.get(pair[1]).add(pair[0]);
        }
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        for(int i = 0; i < n; i++){
            if(!visited[i]){
                count++;
                queue.add(i);
                while(!queue.isEmpty()){
                    Integer node = queue.poll();
                    if(visited[node]){
                        continue;
                    }
                    visited[node] = true;
                    for(int neighbor : graph.get(node)){
                        queue.add(neighbor);
                        // graph.get(neighbor).remove(node); // not needed, if we use boolean visited array.
                    }
                }
            }
        }
        return count;
    }

    public int countComponents1(int n, int[][] edges) {
        int count = 0;
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }
        for(int[] pair : edges){
            graph.get(pair[0]).add(pair[1]);
            graph.get(pair[1]).add(pair[0]);
        }
        int[] visited = new int[n];
        Queue<Integer> queue = new LinkedList<>();

        for(int i = 0; i < n; i++){
            if(visited[i] == 0){
                count++;
                queue.add(i);
                while(!queue.isEmpty()){
                    Integer node = queue.poll();//这里需要用Integer,下边的remove才能知道是remove(object)而不是remove(index)
                    if(visited[node] == 1){
                        continue;
                    }
                    visited[node] = 1;
                    for(int neighbor : graph.get(node)){
                        queue.add(neighbor);
                        graph.get(neighbor).remove(node);
                    }
                    visited[node] = 2;
                }
            }
        }
        return count;
    }
    /*method2 DFS*/
    public int countComponents2(int n, int[][] edges) {
        int count = 0;
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }
        for(int[] pair : edges){
            graph.get(pair[0]).add(pair[1]);
            graph.get(pair[1]).add(pair[0]);
        }
        boolean[] visited = new boolean[n];
        Stack<Integer> queue = new Stack<>();

        for(int i = 0; i < n; i++){
            if(!visited[i]){
                count++;
                queue.push(i);
                while(!queue.isEmpty()){
                    Integer node = queue.pop();
                    if(visited[node]){
                        continue;
                    }
                    visited[node] = true;
                    for(int neighbor : graph.get(node)){
                        queue.add(neighbor);
                    }
                }
            }
        }
        return count;
    }
    //dfs
    public int countComponents3(int n, int[][] edges) {
        int count = 0;
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }
        for(int[] pair : edges){
            graph.get(pair[0]).add(pair[1]);
            graph.get(pair[1]).add(pair[0]);
        }
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                count++;
                helper(graph, visited, i, -1);
            }
        }
        return count;
    }
    private void helper(List<List<Integer>> graph, boolean[] visited, int node, int parent){
//		if(visited[node]){	//不需要这个判断
//			return;
//		}
        visited[node] = true;
        for(int neighbor : graph.get(node)){
            if(neighbor == parent || visited[neighbor]){    // neighbor == parent is not necessary
                continue;
            }
            helper(graph, visited, neighbor, node);
        }
    }
    //union find : O(V+E) : V - n E - length of edges.
    public int countComponents(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for(int[] pair : edges){
            uf.union(pair[0], pair[1]);
        }
        return uf.count;
    }
    class UnionFind{
        int[] parent;
        int[] rank;
        int count;
        public UnionFind(int n){
            count = n;
            parent = new int[n];
            rank = new int[n];
            for(int i = 0; i < n; i++){
                parent[i] = i;
            }
        }
        public int find(int val){
            if(parent[val] != val){
                parent[val] = find(parent[val]);
            }
            return parent[val];
        }
        public void union(int n1, int n2){
            int r1 = find(n1);
            int r2 = find(n2);
            if(r1 != r2){
                if(rank[r1] > rank[r2]){
                    parent[r2] = r1;
                }else if(rank[r1] < rank[r2]){
                    parent[r1] = r2;
                }else{
                    parent[r1] = r2;
                    rank[r2]++;
                }
                count--;
            }
        }
    }
}
