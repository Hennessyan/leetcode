package amazon;

import java.util.*;

// Smallest Common Region
// Q236
// todo: 1644 1659 1676
public class Q1257 {

    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        for (int i = regions.size() - 1; i >= 0; i--) {
            if (regions.get(i).contains(region1) && regions.get(i).contains(region2))
                return regions.get(i).get(0);
            if (regions.get(i).contains(region1))
                region1 = regions.get(i).get(0);
            if (regions.get(i).contains(region2))
                region2 = regions.get(i).get(0);
        }
        return "";
    }

    public String findSmallestRegion1(List<List<String>> regions, String region1, String region2) {
        HashMap<String, String> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        for (List<String> r : regions) {
            for (int i = 1; i < r.size(); i++) {
                map.put(r.get(i), r.get(0));
            }
        }

        while (region1 != null) {
            set.add(region1);
            region1 = map.get(region1);
        }
        while (!set.contains(region2)) {
            region2 = map.get(region2);
        }
        return region2;
    }

    Map<String, List<String>> graph = new HashMap<>();
    String res;
    public String findSmallestRegion3(List<List<String>> regions, String region1, String region2) {
        res = null;
        Map<String, Integer> indegree = new HashMap<>();
        for(List<String> list : regions) {
            String key = list.get(0);
            int size = list.size();
            for(int i = 1; i < size; i++) {
                String v = list.get(i);
                indegree.put(v, indegree.getOrDefault(v, 0) + 1);
                graph.computeIfAbsent(key, x -> new ArrayList<>()).add(v);
            }
        }
        List<String> candidates = new ArrayList<>();
        for(String key : graph.keySet()) {
            if(indegree.getOrDefault(key, 0) == 0) {
                candidates.add(key);
            }
        }

        for(String p : candidates) {
            helper(p, region1, region2);
            if(res != null) {
                return res;
            }
        }
        return res;
    }
    private boolean helper(String p, String r1, String r2) {
        int count = 0;
        if(graph.containsKey(p)) {
            for(String next : graph.get(p)) {
                if(helper(next, r1, r2)) {
                    count++;
                }
            }
        }
        if(p.equals(r1) || p.equals(r2)) {
            count++;
        }
        if(count >= 2) {
            res = p;
        }
        return count > 0;
    }
}
