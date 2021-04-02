package google;

import java.util.*;

// Sequence Reconstruction
public class Q444 {
    //可以构建unique sequence的两个条件:
    //1.Every sequence in seqs should be a subsequence in org
    //2.Every 2 consecutive elements in org should be consecutive elements in some sequence from seqs
    // O(N) O(N)
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();

        for(List<Integer> seq : seqs) {
            if(seq.size() == 1) {
                int val = seq.get(0);
                graph.putIfAbsent(val, new HashSet<>());
                indegree.putIfAbsent(val, 0);
            } else {
                for(int i = 0; i < seq.size() - 1; i++) {
                    int fir = seq.get(i);
                    int sec = seq.get(i + 1);
                    if(graph.computeIfAbsent(fir, x -> new HashSet<>()).add(sec)) { // 用SET就需要这样,否则indegree会多加.
                        indegree.put(sec, indegree.getOrDefault(sec, 0) + 1);
                    }
                    indegree.putIfAbsent(fir, 0);
                }
            }
        }

        if(indegree.size() != org.length) {
            return false;
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int key : indegree.keySet()) {
            if(indegree.get(key) == 0) {
                queue.add(key);
            }
        }
        int index = 0;
        while(!queue.isEmpty()) {
            if(queue.size() != 1) { //只有一个
                return false;
            }
            int u = queue.poll();
            if(org[index++] != u) { //计算过程中检查ORDER是否正确
                return false;
            }
            if(graph.containsKey(u)) {
                for(int v : graph.get(u)) {
                    int val = indegree.get(v);
                    if(--val == 0) {
                        queue.add(v);
                    }
                    indegree.put(v, val);
                }
            }
        }
        return index == org.length;
    }

    // https://leetcode.com/problems/sequence-reconstruction/discuss/92574/Very-short-solution-with-explanation
    public boolean sequenceReconstruction1(int[] org, List<List<Integer>> seqs) {
        if(seqs == null || seqs.size() == 0) {  // necessary
            return false;
        }
        int n = org.length; // org in [1,n]
        int[] index = new int[n + 1];
        boolean[] pair = new boolean[n + 1];
        for(int i = 0; i < n; i++) {
            index[org[i]] = i;
        }

        for(List<Integer> seq : seqs) {
            for(int i = 0; i < seq.size(); i++) {
                int a = seq.get(i);
                if(a > n || a <= 0) {   // use map if we don't know the scope
                    return false;
                }
                if(i > 0 && index[seq.get(i-1)] >= index[a]) {  // "=" for [1], [1,1] => false
                    return false;
                }
                if(i > 0 && index[seq.get(i-1)] + 1 == index[a]) {
                    pair[index[seq.get(i-1)]] = true;
                }
            }
        }
        for(int i = 0; i < n - 1; i++) {    // verify L84-86
            if(!pair[i]) {
                return false;
            }
        }
        return true;
    }
}
