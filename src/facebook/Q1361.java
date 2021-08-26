package facebook;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Validate Binary Tree Nodes
public class Q1361 {

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int[] indegree = new int[n];
        List<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < n; i++) {
            if(leftChild[i] != -1) {
                graph[i].add(leftChild[i]);
                if(++indegree[leftChild[i]] > 1) {  // child node should not have multiple parent nodes
                    return false;
                }
            }
            if(rightChild[i] != -1) {
                graph[i].add(rightChild[i]);
                if(++indegree[rightChild[i]] > 1) { // child node should not have multiple parent nodes
                    return false;
                }
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            if(indegree[i] == 0) {
                queue.add(i);
            }
        }
        if(queue.size() != 1) {
            return false;
        }
        while(!queue.isEmpty()) {
            int u = queue.poll();
            n--;
            for(int v : graph[u]) {
                if(--indegree[v] == 0) {
                    queue.add(v);
                }
            }
        }
        return n == 0;
    }
    // disjoint set union (Union Find)
    public boolean validateBinaryTreeNodes1(int n, int[] leftChild, int[] rightChild) {

        //all nodes should be connected with n - 1 edges for a valid tree
        //each union add 1 edge, after union all nodes, we should have n -1 edges

        int[] parent = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = i;
        }

        int edge = 0;
        for(int i = 0; i < n; i++){
            if(leftChild[i] != -1){
                if(union(leftChild[i], i, parent)){
                    edge++;
                }else{
                    return false;
                }
            }

            if(rightChild[i] != -1){
                if(union(rightChild[i], i, parent)){
                    edge++;
                }else{
                    return false;
                }
            }
        }

        return edge == n-1;
    }

    private int find(int x, int[] parent){
        while(x != parent[x]){
            x = parent[x];
        }
        return x;
    }

    private boolean union(int x, int y, int[] parent){
        int px = find(x, parent);
        int py = find(y, parent);
        if(px == py){
            return false;
        }
        if(px != x){
            // cannot link to another parent since it already has a parent
            return false;
        }
        parent[px] = py;
        return true;
    }
}
