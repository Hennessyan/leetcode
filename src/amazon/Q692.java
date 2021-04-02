package amazon;

import java.util.*;

// Top K Frequent Words
public class Q692 {
    // O(nlgk) O(n)
    public List<String> topKFrequent(String[] words, int k) {
        List<String> list = new ArrayList<>();
        if(words == null || words.length == 0 || k <= 0) {
            return list;
        }
        Map<String, Integer> count = new HashMap<>();
        for(String w : words) {
            count.put(w, count.getOrDefault(w, 0) + 1);
        }
        PriorityQueue<String> pq = new PriorityQueue<>(k + 1, (a, b) -> (
                count.get(a) == count.get(b) ? b.compareTo(a) : count.get(a) - count.get(b)));
        for(String w : count.keySet()) {
            pq.offer(w);
            if(pq.size() > k) {
                pq.poll();
            }
        }
        while(!pq.isEmpty()) {
            list.add(0, pq.poll()); // expensive
        }

//        List<String> ans = new ArrayList();
//        while (!heap.isEmpty()) ans.add(heap.poll());
//        Collections.reverse(ans);
        return list;
    }

    // Sort : O(nlgn) O(n)
    public List<String> topKFrequent1(String[] words, int k) {
        Map<String, Integer> count = new HashMap();
        for (String word : words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        List<String> candidates = new ArrayList(count.keySet());
        Collections.sort(candidates, (w1, w2) -> count.get(w1).equals(count.get(w2)) ?
                w1.compareTo(w2) : count.get(w2) - count.get(w1));

        return candidates.subList(0, k);
    }
}
