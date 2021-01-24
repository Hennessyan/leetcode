package microsoft;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Palindrome Permutation
public class Q266 {
    // Single Pass : O(n) O(1)
    public boolean canPermutePalindrome0(String s) {
        int[] chs = new int[128];
        int count = 0;
        for(char c : s.toCharArray()) {
            if(++chs[c] % 2 == 0) {
                count--;
            } else {
                count++;
            }
        }
        return count <= 1;
    }
    // HashMap
    public boolean canPermutePalindrome1(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int count = 0;
        for(char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for(Integer number : map.values()) {
            count += number % 2;
        }
        return count <= 1;
    }
    // Set
    public boolean canPermutePalindrome2(String s) {
        Set<Character> set = new HashSet<>();
        for(char c : s.toCharArray()) {
            if(!set.add(c)) {
                set.remove(c);
            }
        }
        return set.size() <= 1;
    }
    // Array
    public boolean canPermutePalindrome(String s) {
        int[] map = new int[128];
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)]++;
        }
        int count = 0;
        for (int key = 0; key < map.length && count <= 1; key++) {
            count += map[key] % 2;
        }
        return count <= 1;
    }
}
