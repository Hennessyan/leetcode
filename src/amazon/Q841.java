package amazon;

import java.util.*;

// Keys and Rooms
public class Q841 {
    // BFS : O(V+E) O(V)
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if(rooms == null || rooms.size() == 0) {
            return true;
        }
        int N = rooms.size(), count = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        boolean[] seen = new boolean[N];
        seen[0] = true;

        while(!queue.isEmpty()) {
            int u = queue.poll();
            for(int v : rooms.get(u)) {
                if(seen[v]) {
                    continue;
                }
                seen[v] = true;
                count++;
                queue.add(v);
            }
        }
        return count == N;
    }
    // DFS
    int N;
    Set<Integer> seen = new HashSet<>();
    List<List<Integer>> rooms;
    public boolean canVisitAllRooms1(List<List<Integer>> rooms) {
        N = rooms.size();
        this.rooms = rooms;
        if(N == 1) {
            return true;
        }
        seen.add(0);
        dfs(0);

        return seen.size() == N;
    }
    private void dfs(int u) {
        for(int v : this.rooms.get(u)) {
            if(seen.add(v)) {
                dfs(v);
            }
        }
    }
}
