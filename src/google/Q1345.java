package google;

import java.util.*;

// Jump Game IV
// 1340 1696
public class Q1345 {
    // BFS : O(n) O(n)
    public int minJumps(int[] arr) {
        int n = arr.length;
        if (n <= 1) {
            return 0;
        }

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.computeIfAbsent(arr[i], v -> new LinkedList<>()).add(i);
        }

        List<Integer> curs = new LinkedList<>(); // store current layer
        curs.add(0);
        Set<Integer> visited = new HashSet<>();
        int step = 0;

        // when current layer exists
        while (!curs.isEmpty()) {
            List<Integer> nex = new LinkedList<>();

            // iterate the layer
            for (int node : curs) {
                // check if reached end
                if (node == n - 1) {
                    return step;
                }

                // check same value
                for (int child : graph.get(arr[node])) {
                    if (!visited.contains(child)) {
                        visited.add(child);
                        nex.add(child);
                    }
                }

                // clear the list to prevent redundant search
                // avoid duplicates with only visited set is costly, as we may go through a very big values multiple times
                // [7, 7, ... 7, ... x, y]
                graph.get(arr[node]).clear();

                // check neighbors
                if (node + 1 < n && !visited.contains(node + 1)) {
                    visited.add(node + 1);
                    nex.add(node + 1);
                }
                if (node - 1 >= 0 && !visited.contains(node - 1)) {
                    visited.add(node - 1);
                    nex.add(node - 1);
                }
            }

            curs = nex;
            step++;
        }

        return -1;
    }
    // bidirectional bfs - O(n) O(n)
    public int minJumps1(int[] arr) {
        if(arr == null || arr.length < 2) {
            return 0;
        }
        int step = 0, n = arr.length;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i = 0; i < n; i++)  {
            graph.computeIfAbsent(arr[i], x -> new ArrayList<>()).add(i);
        }
        Set<Integer> visited = new HashSet<>();
        Set<Integer> s1 = new HashSet<>();
        Set<Integer> s2 = new HashSet<>();
        s1.add(0);
        s2.add(n - 1);
        visited.add(0);
        visited.add(n - 1);

        while(!s1.isEmpty()) {
            if(s1.size() > s2.size()) {
                Set<Integer> st = s1;
                s1 = s2;
                s2 = st;
            }

            Set<Integer> s = new HashSet<>();
            for(int u : s1) {

                for(int v : graph.get(arr[u])) {
                    if(s2.contains(v)) {
                        return step + 1;
                    }
                    if(visited.add(v)) {
                        s.add(v);
                    }
                }
                if(s2.contains(u + 1) || s2.contains(u - 1)) {
                    return step + 1;
                }
                if(u - 1 >= 0) {
                    if(visited.add(u - 1)) {
                        s.add(u - 1);
                    }
                }
                if(u + 1 < n) {
                    if(visited.add(u + 1)) {
                        s.add(u + 1);
                    }
                }
            }
            step++;
            s1 = s;
        }
        return n - 1;
    }
}
