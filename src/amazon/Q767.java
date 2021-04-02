package amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

// Reorganize String
public class Q767 {
    // O(n) O(1)
    public String reorganizeString(String S) {
        int n = S.length();
        int[] count = new int[26];
        for(char c : S.toCharArray()) {
            count[c - 'a'] += 100;
        }
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 26; i++) {
            list.add(count[i] + i);
        }
        Collections.sort(list, (a, b) -> b - a);
        char[] res = new char[n];
        int index = 0;
        for(int val : list) {
            int fre = val / 100;
            if(fre > (n + 1) / 2) {
                return "";
            }
            char c = (char) ('a' + val % 100);
            while(fre-- > 0) {
                if(index >= n) {
                    index = 1;
                }
                res[index] = c;
                index += 2;
            }
        }
        return String.valueOf(res);
    }

    // O(N) O(1)
    public String reorganizeString1(String S) {
        int N = S.length();
        int[] count = new int[26];
        for(char c : S.toCharArray()) {
            count[c - 'a']++;
        }
        PriorityQueue<Item> pq = new PriorityQueue<>((i1, i2) ->
                i1.count == i2.count ? i1.letter - i2.letter : i2.count - i1.count);
        for(int i = 0; i < 26; i++) {
            if(count[i] != 0) {
                if(count[i] > (N + 1) / 2) return "";
                pq.offer(new Item(count[i], (char)('a' + i)));
            }
        }
        StringBuilder sb = new StringBuilder();
        while(pq.size() >= 2) {
            Item i1 = pq.poll();
            Item i2 = pq.poll();
            sb.append(i1.letter);
            sb.append(i2.letter);
            if(--i1.count > 0) pq.offer(i1);
            if(--i2.count > 0) pq.offer(i2);
        }
        if(pq.size() > 0) sb.append(pq.poll().letter);
        return sb.toString();

    }
    class Item {
        int count;
        char letter;
        public Item(int count, char letter) {
            this.count = count;
            this.letter = letter;
        }
    }
}
