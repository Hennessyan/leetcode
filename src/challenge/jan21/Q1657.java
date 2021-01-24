package challenge.jan21;

import java.util.*;

// Determine if Two Strings Are Close
public class Q1657 {
    // O(n) O(1)
    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        Map<Character, Integer> word1Map = new HashMap<>();
        Map<Character, Integer> word2Map = new HashMap<>();
        for (char c : word1.toCharArray()) {
            word1Map.put(c, word1Map.getOrDefault(c, 0) + 1);
        }
        for (char c : word2.toCharArray()) {
            word2Map.put(c, word2Map.getOrDefault(c, 0) + 1);
        }
        if (!word1Map.keySet().equals(word2Map.keySet())) {
            return false;
        }
        List<Integer> word1FrequencyList = new ArrayList<>(word1Map.values());
        List<Integer> word2FrequencyList = new ArrayList<>(word2Map.values());
        Collections.sort(word1FrequencyList);
        Collections.sort(word2FrequencyList);
        return word1FrequencyList.equals(word2FrequencyList);
    }

    public boolean closeStrings1(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        int word1Map[] = new int[26];
        int word2Map[] = new int[26];
        for (char c : word1.toCharArray()) {
            word1Map[c - 'a']++;
        }
        for (char c : word2.toCharArray()) {
            word2Map[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if ((word1Map[i] == 0 && word2Map[i] > 0) ||
                    (word2Map[i] == 0 && word1Map[i] > 0)) {
                return false;
            }
        }
        Arrays.sort(word1Map);
        Arrays.sort(word2Map);
        return Arrays.equals(word1Map, word2Map);
    }

    public boolean closeStrings2(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        int word1Map[] = new int[26];
        int word2Map[] = new int[26];
        int word1Bit = 0;
        int word2Bit = 0;
        for (char c : word1.toCharArray()) {
            word1Map[c - 'a']++;
            word1Bit = word1Bit | (1 << (c - 'a'));
        }
        for (char c : word2.toCharArray()) {
            word2Map[c - 'a']++;
            word2Bit = word2Bit | (1 << (c - 'a'));
        }
        if (word1Bit != word2Bit) {
            return false;
        }
        Arrays.sort(word1Map);
        Arrays.sort(word2Map);
        for (int i = 0; i < 26; i++) {
            if (word1Map[i] != word2Map[i]) {
                return false;
            }
        }
        return true;
    }
}
