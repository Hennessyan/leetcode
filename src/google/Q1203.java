package google;

import java.util.*;

// Sort Items by Groups Respecting Dependencies
public class Q1203 {

    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        Map<Integer, Set<Integer>> adjI = new HashMap<>();
        Map<Integer, Set<Integer>> adjG = new HashMap<>();

        for(int i = 0; i < n; i++) {
            if(group[i] == -1) {
                group[i] = m++;
            }
        }
        int[] i_indegree = new int[n];
        int[] g_indegree = new int[m];
        // the worst case: total size of beforeItems is n^2 => O(n^2)
        // the correct case : O(n) => O(n+m) new m can be n in the worst case
        for(int i = 0; i < n; i++) {
            List<Integer> items = beforeItems.get(i);
            for(int item : items) {
                if (adjI.computeIfAbsent(item, x -> new HashSet<>()).add(i)) {
                    i_indegree[i]++;
                }
                if (group[item] != group[i]) {
                    if (adjG.computeIfAbsent(group[item], x -> new HashSet<>()).add(group[i])) {
                        g_indegree[group[i]]++;
                    }
                }
            }
        }

        int[] item_list = topoSort(adjI, i_indegree, n);
        int[] group_list = topoSort(adjG, g_indegree, m);
        if(item_list.length != n || group_list.length != m) {
            return new int[0];
        }
        List<Integer>[] tmp = new ArrayList[m];    // tmp save items based on group order
        for(int i = 0; i < m; i++) {
            tmp[i] = new ArrayList<>();
        }
        for(int i = 0; i < n; i++) {
            tmp[group[item_list[i]]].add(item_list[i]);
        }
        int[] res = new int[n];
        int index = 0;
        for(int i = 0; i < m; i++) {
            int g = group_list[i];
            for(int item : tmp[g]) {
                res[index++] = item;
            }
        }
        return res;
    }
    private int[] topoSort(Map<Integer, Set<Integer>> graph, int[] indegree, int n) {
        int[] res = new int[n];
        int index = 0;

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < n; i++) {
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
        return index == n ? res : new int[0];
    }
    // method1 is better because it's linear, prioritqueue is expensive.
    // method2: https://github.com/JackYumCha/algorithms/blob/master/topological-sorting/lc1203%20Sort%20Items%20by%20Groups%20Respecting%20Dependencies.java

    // method3 : 复习 - https://leetcode.com/problems/sort-items-by-groups-respecting-dependencies/discuss/402945/C%2B%2B-with-picture-generic-topological-sort
    public int[] sortItems1(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        int total = n + 2 * m;
        List<Integer>[] adj = new ArrayList[total];
        int[] indegree = new int[total];
        for(int i = 0; i < total; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int i = 0; i < n; i++) {
            if(group[i] >= 0) {
                adj[i].add(n + group[i] + m);
                indegree[n + group[i] + m]++;
                adj[n + group[i]].add(i);
                indegree[i]++;
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j : beforeItems.get(i)) {
                if(group[i] == group[j]) {
                    adj[j].add(i);
                    indegree[i]++;
                } else {
                    int u = group[j] >= 0 ? n + m + group[j] : j;
                    int v = group[i] >= 0 ? n + group[i] : i;
                    if(!adj[u].contains(v)) {
                        adj[u].add(v);
                        indegree[v]++;
                    }
                }
            }
        }
        // 注意是用PriorityQueue
        Queue<Integer> queue = new PriorityQueue<>();
        for(int i = 0; i < total; i++) {
            if(indegree[i] == 0) {
                queue.add(i);
            }
        }
        int[] res = new int[n];
        int index = 0;
        while(!queue.isEmpty()) {
            int u = queue.poll();
            if(u < n) {
                res[index++] = u;
            }
            for(int v : adj[u]) {
                if(--indegree[v] == 0) {
                    queue.add(v);
                }
            }
        }
        return index == n ? res : new int[0];
    }
}
