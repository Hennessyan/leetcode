package amazon;

import java.util.*;

// Bus Routes
public class Q815 {
    // O(n^2*m) O(n^2)
    // n - number of buses, m - max route (len of routes[i])
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if(S == T) {
            return 0;
        }
        Set<Integer> target = new HashSet<>();
        Set<Integer> seen = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < routes.length; i++) {
            // Arrays.sort(routes[i]);      // - if not increase order, just traverse rather than binary search
            if(Arrays.binarySearch(routes[i], S) >= 0) {
                seen.add(i);
                queue.add(i);
            }
            if(Arrays.binarySearch(routes[i], T) >= 0) {
                target.add(i);
            }
        }
        Map<Integer, List<Integer>> busMap = new HashMap<>();
        for(int i = 0; i < routes.length - 1; i++) {
            for(int j = i + 1; j < routes.length; j++) {
                if(intersected(routes[i], routes[j])) {
                    busMap.computeIfAbsent(i, x -> new ArrayList<>()).add(j);
                    busMap.computeIfAbsent(j, x -> new ArrayList<>()).add(i);
                }
            }
        }
        int num = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            num++;
            for(int i = 0; i < size; i++) {
                int bus = queue.poll();
                if(target.contains(bus)) {
                    return num;
                }
                if(busMap.containsKey(bus)) {
                    for(int next : busMap.get(bus)) {
                        if(seen.add(next)) {
                            queue.add(next);
                        }
                    }
                }
            }
        }
        return -1;
    }
    private boolean intersected(int[] a, int b[]) {
        int i = 0, j = 0;
        while(i < a.length && j < b.length) {
            if(a[i] == b[j]) {
                return true;
            }else if(a[i] < b[j]) {
                i++;
            }else {
                j++;
            }
        }
        return false;
    }
}
