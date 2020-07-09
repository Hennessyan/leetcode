package challenge.june;

import java.util.*;

// Reconstruct Itinerary
public class Q332 {

    Map<String, List<String>> graph;
    Map<String, boolean[]> visited;
    List<String> res;
    int flights;

    // method1
    // O(d ^ E) O(E + V)
    // E is the number of total flights and dd is the maximum number of flights from an airport.
    public List<String> findItinerary1(List<List<String>> tickets) {
        graph = new HashMap<>();
        visited = new HashMap<>();
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            graph.computeIfAbsent(from, x -> new LinkedList<>()).add(to);
        }
        for (Map.Entry<String, List<String>> entry : graph.entrySet()) {
            String key = entry.getKey();
            Collections.sort(entry.getValue());
            visited.put(key, new boolean[entry.getValue().size()]);
        }
        flights = tickets.size();
        List<String> route = new ArrayList<>();
        route.add("JFK");
        dfs("JFK", route);
        return res;
    }

    private boolean dfs(String from, List<String> route) {
        if (route.size() == flights + 1) {
            res = new ArrayList<>(route);
            return true;
        }
        if (graph.containsKey(from)) {
            List<String> tos = graph.get(from);
            boolean[] tov = visited.get(from);
            for (int i = 0; i < tos.size(); i++) {
                if (!tov[i]) {
                    tov[i] = true;
                    route.add(tos.get(i));
                    if (dfs(tos.get(i), route)) {
                        return true;                    //找到结果时直接结束
                    }
                    tov[i] = false;
                    route.remove(route.size() - 1);
                }
            }
        }
        return false;
    }

    // method2 : O(ElgE) O(E + V)
    Map<String, LinkedList<String>> flightMap = new HashMap<>();
    LinkedList<String> result = null;

    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            flightMap.computeIfAbsent(from, x -> new LinkedList<>()).add(to);
        }
        flightMap.forEach((key, value) -> Collections.sort(value));
        this.result = new LinkedList<>();
        dfs("JFK");
        return result;
    }

    private void dfs(String from) {
        if (flightMap.containsKey(from)) {
            LinkedList<String> destList = this.flightMap.get(from);
            while (!destList.isEmpty()) {
                String next = destList.pollFirst();
                dfs(next);
            }
        }
        result.add(from);   //outside of if condition
    }
}
