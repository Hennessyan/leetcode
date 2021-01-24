package google;

import java.util.*;

// Word Ladder
public class Q127 {

    public int ladderLength1(String beginWord, String endWord, List<String> wordList)  {
        Set<String> wordSet = new HashSet<>(wordList);
        if(!wordSet.contains(endWord)) {
            return 0;
        }
        int depth = 1;
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        while(!queue.isEmpty()) {
            int size = queue.size();
            depth++;

            for(int i = 0; i < size; i++) {
                char[] chs = queue.poll().toCharArray();
                for(int j = 0; j < chs.length; j++) {
                    char old = chs[j];
                    for(char c = 'a'; c <= 'z'; c++) {
                        if(c == chs[j]) {
                            continue;
                        }
                        chs[j] = c;
                        String str = String.valueOf(chs);
                        if(wordSet.contains(str)) {
                            if(str.equals(endWord)) {
                                return depth;
                            }
                            queue.add(str);
                            wordSet.remove(str);
                        }
                        chs[j] = old;
                    }
                }
            }

        }
        return -1;
    }
    // unidirectional : b^0 + b^1 + ... + b^l <= 2*b^l ====> b^l
    // bi-directional : 2 * (b^0 + b^1 + ... + b^(l/2)) ====> b^(l/2)

    // O(n*26^(l/2)) O(n)
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if(!set.contains(endWord)) {
            return 0;
        }
        Set<String> set1 = new HashSet<>();
        set1.add(beginWord);
        Set<String> set2 = new HashSet<>();
        set2.add(endWord);
        int depth = 1;
        while(!set1.isEmpty() && !set2.isEmpty()) {
            if(set1.size() > set2.size()) {
                Set<String> t = set1;
                set1 = set2;
                set2 = t;
            }
            Set<String> tmp = new HashSet<>();
            depth++;
            for(String word : set1) {
                char[] chs = word.toCharArray();
                for(int j = 0; j < chs.length; j++) {
                    char old = chs[j];
                    for(char c = 'a'; c <= 'z'; c++) {
                        if(c == old) {
                            continue;
                        }
                        chs[j] = c;
                        String str = String.valueOf(chs);
                        if(set2.contains(str)) {
                            return depth;
                        }
                        if(set.contains(str)) {
                            set.remove(str);
                            tmp.add(str);
                        }
                    }
                    chs[j] = old;
                }
            }
            set1 = tmp;
        }
        return 0;
    }
}
