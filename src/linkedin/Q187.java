package linkedin;

import java.util.*;

// Repeated DNA Sequences
// Q1044
public class Q187 {
    // O(N) O(N)
    public List<String> findRepeatedDnaSequences(String s) {

        if(s == null || s.length() < 11) return new ArrayList<>();
        Set<String> res = new HashSet<>();
        Set<Integer> seen = new HashSet<>();
        int n = s.length(), a = 4, l = 10, base = 1 << 20; // base = (int) Math.pow(a, l)
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 0);
        map.put('C', 1);
        map.put('G', 2);
        map.put('T', 3);
        int hash = 0;
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = map.get(s.charAt(i));
        }
        for(int i = 0; i < l; i++) {
            hash = hash * a +  nums[i];
        }
        seen.add(hash);
        for(int j = 10; j < n; j++) {
            hash = hash * a + nums[j] - base * nums[j - l];
            if(!seen.add(hash)) {
                res.add(s.substring(j - l + 1, j + 1));
            }
        }
        return new ArrayList<>(res);
    }
    // O(L*(N-L)) O(L*(N-L))
    public List<String> findRepeatedDnaSequences1(String s) {
        int L = 10, n = s.length();
        HashSet<String> seen = new HashSet(), output = new HashSet();

        // iterate over all sequences of length L
        for (int start = 0; start < n - L + 1; ++start) {
            String tmp = s.substring(start, start + L);
            if (seen.contains(tmp)) output.add(tmp);
            seen.add(tmp);
        }
        return new ArrayList<String>(output);
    }
}
