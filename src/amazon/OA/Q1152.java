package amazon.OA;

import java.util.*;
// https://leetcode.com/problems/analyze-user-website-visit-pattern/
// 1. 不需要 back to back 地访问 patten 中的三个网页。换句话说，pattern中的三个网页是 subsequence, 不是 subarray
// 2. 即使同一个 user 访问了同一个 pattern 多次，也只能算 1 分
public class Q1152 {

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        Map<String, List<Integer>> pattern = new HashMap<>();
        int n = username.length;
        for(int i = 0; i < n; i++) {
            if(!pattern.containsKey(username[i])) {
                pattern.put(username[i], new ArrayList<>());
            }
            pattern.get(username[i]).add(i);
        }

        Map<String, Integer> scores = new HashMap<>();

        for(String name : pattern.keySet()) {
            List<Integer> indexes = pattern.get(name);
            if(indexes.size() < 3) continue;
            Collections.sort(indexes, (i1, i2) -> timestamp[i1] - timestamp[i2]);
            Set<String> sequence = new HashSet<>();
            for(String seq : getSequence(indexes, website)) {

                if(sequence.add(seq)) {
                    scores.put(seq, scores.getOrDefault(seq, 0) + 1);
                }
            }
        }

        String res = "";
        int max = 0;

        for(Map.Entry<String, Integer> entry : scores.entrySet()) {
            String seq = entry.getKey();
            int score = entry.getValue();
            if(score > max || (score == max && seq.compareTo(res) < 0)) {
                res = seq;
                max = score;
            }
        }
        return Arrays.asList(res.split("\\."));
    }
    private List<String> getSequence(List<Integer> indexes, String[] web) {
        List<String> res = new ArrayList<>();
        int n = indexes.size();
        for(int i = 0; i < n - 2; i++) {
            for(int j = i + 1; j < n - 1; j++) {
                for(int k= j + 1; k < n; k++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(web[indexes.get(i)]).append('.').append(web[indexes.get(j)]).append('.').append(web[indexes.get(k)]);
                    res.add(sb.toString());
                }
            }
        }
        return res;
    }
}
