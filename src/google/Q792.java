package google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Number of Matching Subsequences
public class Q792 {

    public static void main(String[] args) {
        Q792 q = new Q792();
        String S = "abcde";
        String[] words = {"a", "bb", "acd", "ace"};
        System.out.println(q.numMatchingSubseq(S, words));
    }
    // brute force: TLE - O(w * (S + L)) O(S + L)
    char[] ca, cb;
    public int numMatchingSubseq3(String S, String[] words) {
        int ans = 0;
        for(String w : words) {
            ca = S.toCharArray();
            cb = w.toCharArray();
            if(match()) ans++;
        }
        return ans;
    }
    private boolean match() {
        int i = 0;
        for(char c : ca) {
            if(i < cb.length && c == cb[i]) {
                i++;
            }
        }
        return i == cb.length;
    }
    // O(s + w * l) O(w * l)
    // S is very long, so we hope to traverse it only once.
    public int numMatchingSubseq2(String S, String[] words) {
        List<Node>[] lists = new List[26];
        for(int i = 0; i < 26; i++) {
            lists[i] = new ArrayList<>();
        }
        for(String w : words) {
            lists[w.charAt(0) - 'a'].add(new Node(w, 0));
        }
        int count = 0;
        for(char c : S.toCharArray()) {
            List<Node> old = lists[c - 'a'];
            lists[c - 'a'] = new ArrayList<>();
            for(Node n : old) {
                n.index++;
                if(n.index == n.word.length()) {
                    count++;
                }else {
                    lists[n.word.charAt(n.index) - 'a'].add(n);
                }
            }
            old.clear();
        }
        return count;
    }
    class Node {
        String word;
        int index;
        public Node(String w, int i) {
            word = w;
            index = i;
        }
    }

    public int numMatchingSubseq1(String S, String[] words) {
        int ans = 0;
        List<StringBuilder>[] waiting = new List[26];
        for (int c = 0; c <= 25; c++)
            waiting[c] = new ArrayList();
        for (String w : words)
            waiting[w.charAt(0) - 'a'].add(new StringBuilder(w));

        for (char c : S.toCharArray()) {
            List<StringBuilder> advance = waiting[c - 'a'];

            waiting[c] = new ArrayList();

            for (StringBuilder it : advance){
                it.deleteCharAt(0);
                if(it.length() != 0)
                    waiting[it.charAt(0) - 'a'].add(it);
                else
                    ans++;
            }
        }
        return ans;
    }

    // O(S + w * l * lgS) O(S)
    public int numMatchingSubseq(String S, String[] words) {
        List<Integer>[] lists = new List[26];
        for(int i = 0; i < 26; i++) {
            lists[i] = new ArrayList<>();
        }
        for(int i = 0; i < S.length(); i++) {
            lists[S.charAt(i) - 'a'].add(i);
        }
        int ans = 0;
        for(String w : words) {
            if(match1(lists, w)) {
                ans++;
            }
        }
        return ans;
    }

    private boolean match1(List<Integer>[] lists, String w) {
        int p = -1;
        for(char c : w.toCharArray()) {
            List<Integer> list = lists[c - 'a'];
            int index = Collections.binarySearch(list, p + 1);
            if(index < 0) index = -index - 1;
            if(index >= list.size()) return false;
            p = list.get(index);
        }
        return true;
    }
}
