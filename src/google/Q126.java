package google;

import java.util.*;

// Word Ladder II
public class Q126 {
    // check all methods in 2018-leetcode/AmazonShua


    // bidirectional:
    // O(n*26^(l/2))    vs O(nk^2 + x) O(nk) (second one should be correct answer)
    // O(n + k * length of result)  k = number of result
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> s0 = new HashSet<>(wordList);
        if(!s0.contains(endWord)) {
            return res;
        }
        Set<String> s1 = new HashSet<>();
        Set<String> s2 = new HashSet<>();
        s1.add(beginWord);
        s2.add(endWord);
        Map<String, List<String>> children = new HashMap<>();
        boolean found = false, backward = false;

        while(!s1.isEmpty() && !s2.isEmpty() && !found) {
            if(s1.size() > s2.size()) {
                Set<String> tmp = s1;
                s1 = s2;
                s2 = tmp;
                backward = !backward;
            }
            for(String str : s1) {
                s0.remove(str);
            }
            // should remove L37-39
//            for(String str : s2) {
//                s0.remove(str);
//            }
            Set<String> s3 = new HashSet<>();
            for(String word : s1) {
                char[] chs = word.toCharArray();
                for(int i = 0; i < chs.length; i++) {
                    char old = chs[i];
                    for(char c = 'a'; c <= 'z'; c++) {
                        if(c == old) {
                            continue;
                        }
                        chs[i] = c;
                        String nWord = String.valueOf(chs);
                        String parent = word, child = nWord;
                        if(backward) {
                            String tmpWord = parent;
                            parent = child;
                            child = tmpWord;
                        }
                        if(s2.contains(nWord)) {
                            found = true;
                            children.computeIfAbsent(parent, x -> new ArrayList<>()).add(child);
                        } else if(!found && s0.contains(nWord)) {
                            s3.add(nWord);
                            children.computeIfAbsent(parent, x -> new ArrayList<>()).add(child);
                        }
                    }
                    chs[i] = old;
                }
            }
            s1 = s3;
        }

        if(found) {
            LinkedList<String> list = new LinkedList<>();
            list.add(beginWord);
            buildLadder(children, list, res, beginWord, endWord);
        }
        return res;
    }

    private void buildLadder(Map<String, List<String>> children, LinkedList<String> list, List<List<String>> res, String beginWord, String endWord) {
        if(beginWord.equals(endWord)) {
            res.add(new ArrayList<>(list));
            return;
        }
        if(children.containsKey(beginWord)) {
            for(String child : children.get(beginWord)) {
                list.add(child);
                buildLadder(children, list, res, child, endWord);
                list.removeLast();
            }
        }
    }


}
