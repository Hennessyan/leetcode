package microsoft;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Palindrome Permutation II
// Q131
public class Q267 {
    // O(n*(n/2)!) O(n) - stack for recursion.
    Set<String> set = new HashSet<>();
    public List<String> generatePalindromes(String s) {
        List<String> list = new ArrayList<>();
        int[] map = new int[128];
        if(!canPalindromes(s, map)) {
            return list;
        }
        char[] half = new char[s.length() / 2];
        char c = 0;
        int k = 0;
        for(int i = 0; i < 128; i++) {
            if(map[i] % 2 == 1) {
                c = (char) i;
            }
            // not "else condition" for below part because of "aaa" case.
            for(int j = 0; j < map[i] / 2; j++) {
                half[k++] = (char) i;
            }
        }
        help(half, 0, c);
        return new ArrayList<>(set);
    }
    private boolean canPalindromes(String s, int[] map) {
        int count = 0;
        for(char c : s.toCharArray()) {
            map[c]++;
            if(map[c] % 2 == 1) {
                count++;
            }else {
                count--;
            }
        }
        return count <= 1;
    }
    private void help(char[] half, int i, char c) {
        if(i == half.length) {
            set.add(new String(half) + (c == 0 ?  "" : c) + new StringBuilder(new String(half)).reverse());
        }
        for(int j = i; j < half.length; j++) {
            if(i == j || half[j] != half[i]) {
                swap(half, i, j);
                help(half, i + 1, c);
                swap(half, i, j);
            }
        }
    }
    private void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }
}
