package amazon;

import java.util.*;

// Iterator for Combination
public class Q1286 {
    // pre-computation : O(n*2^n)
    // may ask next combination as follow-up, so backtracking may not be a good solution here.
    class CombinationIterator {

        List<String> list;
        Iterator<String> it;
        public CombinationIterator(String characters, int k) {
            list = new ArrayList<>();
            int n = characters.length();
            for(int i = (1 << n) - 1; i >= 0; i--) {
                if(Integer.bitCount(i) == k) {
                    StringBuilder sb = new StringBuilder();
                    for(int j = 0; j < n; j++) {
                        int t = 1 << (n - 1 - j);
                        if((i & t)  > 0) {
                            sb.append(characters.charAt(j));
                        }
                    }
                    list.add(sb.toString());
                }
            }
            it = list.iterator();
        }

        public String next() {
            return it.next();
        }

        public boolean hasNext() {
            return it.hasNext();
        }
    }
    // pre-computation : O(n*2^n)  O(k * C(n,k))
    class CombinationIterator1 {
        public Deque< String > combinations = new ArrayDeque< String >();

        public CombinationIterator1(String characters, int combinationLength) {
            int n = characters.length();
            int k = combinationLength;

            // generate bitmasks from 0..00 to 1..11
            for (int bitmask = 0; bitmask < 1 << n; bitmask++) {
                // use bitmasks with k 1-bits
                if (Integer.bitCount(bitmask) == k) {
                    // convert bitmask into combination
                    // 111 --> "abc", 000 --> ""
                    // 110 --> "ab", 101 --> "ac", 011 --> "bc"
                    StringBuilder curr = new StringBuilder();
                    for (int j = 0; j < n; j++) {
                        if ((bitmask & (1 << n - j - 1)) != 0) {
                            curr.append(characters.charAt(j));
                        }
                    }
                    combinations.push(curr.toString());
                }
            }
        }

        public String next() {
            return combinations.pop();
        }

        public boolean hasNext() {
            return (!combinations.isEmpty());
        }
    }
    // next-combination : O(n*2^n / C(n,k)) O(k)
    class CombinationIterator2 {
        int bitmask, n, k;
        String chars;

        public CombinationIterator2(String characters, int combinationLength) {
            n = characters.length();
            k = combinationLength;
            chars = characters;

            // generate first bitmask 1(k)0(n - k)
            bitmask = (1 << n) - (1 << n - k);
        }

        public String next() {
            // convert bitmask into combination
            // 111 --> "abc", 000 --> ""
            // 110 --> "ab", 101 --> "ac", 011 --> "bc"
            StringBuilder curr = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if ((bitmask & (1 << n - j - 1)) != 0) {
                    curr.append(chars.charAt(j));
                }
            }

            // generate next bitmask
            bitmask--;
            while (bitmask > 0 && Integer.bitCount(bitmask) != k) {
                bitmask--;
            }

            return curr.toString();
        }

        public boolean hasNext() {
            return bitmask > 0;
        }
    }
}
