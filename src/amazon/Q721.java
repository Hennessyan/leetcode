package amazon;

import java.util.*;

// Accounts Merge
public class Q721 {
    // O(nklgnk) O(nk) - N is the number of accounts and K is the maximum length of an account
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailToName = new HashMap<>();
        Map<String, List<String>> graph = new HashMap<>();
        for(List<String> account : accounts) {
            String name = "";
            for(String email : account) {
                if(name.equals("")) {
                    name = email;
                    continue;
                }
                emailToName.put(email, name);
                graph.computeIfAbsent(account.get(1), x -> new ArrayList<>()).add(email);
                graph.computeIfAbsent(email, x -> new ArrayList<>()).add(account.get(1));
            }
        }
        List<List<String>> ans = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        for(String key : emailToName.keySet()) {
            if(seen.contains(key)) {
                continue;
            }
            seen.add(key);
            Stack<String> stack = new Stack<>();
            List<String> tmp = new ArrayList<>();
            stack.push(key);
            while(!stack.isEmpty()) {
                String u = stack.pop();
                tmp.add(u);
                for(String v : graph.get(u)) {
                    if(!seen.contains(v)) {
                        seen.add(v);
                        stack.push(v);
                    }
                }
            }
            Collections.sort(tmp);
            tmp.add(0, emailToName.get(tmp.get(0)));
            ans.add(tmp);
        }
        return ans;
    }

    public List<List<String>> accountsMerge1(List<List<String>> accounts) {
        Map<String, String> e2n = new HashMap<>();
        Map<String, Integer> e2i = new HashMap<>();
        int n = accounts.size(), index = 0;
        DSU uf = new DSU(n);
        for(List<String> list : accounts) {
            String name = "";
            for(String email : list) {
                if(name.equals("")) {
                    name = email;
                    continue;
                }
                e2n.put(email, name);
                e2i.putIfAbsent(email, index);
                uf.union(e2i.get(list.get(1)), e2i.get(email));
            }
            index++;
        }

        List<List<String>> res = new ArrayList<>();
        Map<Integer, List<String>> map = new HashMap<>();
        for(String email : e2n.keySet()) {
            map.computeIfAbsent(uf.find(e2i.get(email)), x -> new ArrayList<>()).add(email);
        }
        for(List<String> list : map.values()) {
            Collections.sort(list);
            list.add(0, e2n.get(list.get(0)));
            res.add(list);
        }
        return res;
    }
    class DSU{
        private int[] parent;
        private int[] rank;
        public DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for(int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        public int find(int node) {
            // if(parent[node] != node) {
            //     parent[node] = find(parent[node]);
            // }
            while(parent[node] != node) {
                parent[node] = parent[parent[node]];
                node = parent[node];
            }
            return parent[node];
        }
        public void union(int n1, int n2) {
            int r1 = find(n1);
            int r2 = find(n2);
            if(parent[r1] != parent[r2]) {
                if(rank[r1] > rank[r2]) {
                    parent[r2] = r1;
                }else if(rank[r1] < rank[r2]) {
                    parent[r1] = r2;
                }else {
                    parent[r2] = r1;
                    rank[r1]++;
                }
            }
        }
    }
}
