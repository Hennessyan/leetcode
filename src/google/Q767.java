package google;

import java.util.*;

// Reorganize String
// Similar : Q358(H) Rearrange String k Distance Apart
public class Q767 {

    public static void main(String[] args) {
        Q767 q = new Q767();
        System.out.println(q.reorganizeString("aab"));  // "aba"
        System.out.println(q.reorganizeString("aaab"));  // ""'
    }

    public String reorganizeString(String S) {
        int N = S.length();
        int[] counts = new int[26];
//        Integer[] counts = new Integer[26];
//        for (int i = 0; i < 26; ++i) counts[i] = 0;   Integer Array需要初始化，否则NULL POINTER
        for (char c: S.toCharArray()) counts[c-'a'] += 100;
        for (int i = 0; i < 26; ++i) counts[i] += i;
        //Encoded counts[i] = 100*(actual count) + (i)
        List<Integer> list = new ArrayList<>();
        for(int c : counts) {
            list.add(c);
        }
        Collections.sort(list, Collections.reverseOrder());

        char[] ans = new char[N];
        int t = 0;
        for (int code: list) {
            int ct = code / 100;
            char ch = (char) ('a' + (code % 100));
            if (ct > (N+1) / 2) return "";
            for (int i = 0; i < ct; ++i) {
                if (t >= N) t = 1;
                ans[t] = ch;
                t += 2;
            }
        }

        return String.valueOf(ans);
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
