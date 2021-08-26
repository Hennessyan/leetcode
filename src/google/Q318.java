package google;

import java.util.HashMap;
import java.util.Map;

// Maximum Product of Word Lengths
public class Q318 {
    // O(n^2 + L) O(n) / O(1)
    // n - number of words, L - sum length of all words
    public int maxProduct(String[] words) {
        // save space in case there are same keys.
        Map<Integer, Integer> map = new HashMap<>();
        for(String word : words) {
            int bitKey = 0;
            for(char c : word.toCharArray()) {
                bitKey |= 1 << bitNumber(c);    // can't use += for ["a","aa","aaa","aaaa"] => 12
            }
            map.put(bitKey, Math.max(map.getOrDefault(bitKey, 0), word.length()));
        }

        int maxProd = 0;
        for(int x : map.keySet()) {
            for(int y : map.keySet()) {
                if((x & y) == 0) {
                    maxProd = Math.max(maxProd, map.get(x) * map.get(y));
                }
            }
        }
        return maxProd;
    }
    private int bitNumber(char c) {
        return c - 'a';
    }

    // ------------------------------------
    public int bitNumber1(char ch) {
        return (int)ch - (int)'a';
    }

    public int maxProduct1(String[] words) {
        int n = words.length;
        int[] masks = new int[n];
        int[] lens = new int[n];

        int bitmask = 0;
        for (int i = 0; i < n; ++i) {
            bitmask = 0;
            for (char ch : words[i].toCharArray()) {
                // add bit number bit_number in bitmask
                bitmask |= 1 << bitNumber1(ch);
            }
            masks[i] = bitmask;
            lens[i] = words[i].length();
        }

        int maxVal = 0;
        for (int i = 0; i < n; ++i)
            for (int j = i + 1; j < n; ++j)
                if ((masks[i] & masks[j]) == 0)
                    maxVal = Math.max(maxVal, lens[i] * lens[j]);

        return maxVal;
    }
}
