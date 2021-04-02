package amazon;

import java.util.*;

// Word Break II
public class Q140 {
    // check solution method1 : https://leetcode.com/problems/word-break-ii/solution/
    /*
     * N : length of the input string.
     * W : the number of words in the dictionary.
     *
     * string with length N has N different prefixes, which causes (1 + 2 + ... + N) => N^2 edges
     * "aaa" -> "a", "aa", "aaa"
     *
     * each postfix may has 2^(i-1) solutions in worst case => 2^N
     *
     * Set takes O(W)
     *
     * so TC : O(N^2 + 2^N + W)
     *    SC : O(N * 2^N + W)
     */
    // up-down
    public List<String> wordBreak2(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        if(s == null || s.length() == 0 || wordDict.size() == 0) {
            return res;
        }
        return wordBreak(s, new HashSet<>(wordDict), new HashMap<>());
    }

    private List<String> wordBreak(String s, Set<String> set, HashMap<String,List<String>> map) {
        if(map.containsKey(s)) {
            return map.get(s);
        }
        List<String> list = new ArrayList<>();
        if(s.length() == 0) {
            list.add("");
            return list;
        }
        for(int i = 1; i <= s.length(); i++) {
            String word = s.substring(0, i);
            if(set.contains(word)) {
                List<String> tmp = wordBreak(s.substring(i), set, map);
                for(String str : tmp) {
                    list.add(word + (str.length() == 0 ? "" : " ") + str);
                }
            }
        }
        map.put(s, list);
        return list;
    }
    // similar method
    public List<String> wordBreak1(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        if(s == null || s.length() == 0 || wordDict.size() == 0) {
            return res;
        }
        return wordBreak(s, wordDict, new HashMap<>());
    }

    private List<String> wordBreak(String s, List<String> wordDict, HashMap<String,List<String>> map) {
        if(map.containsKey(s)) {
            return map.get(s);
        }
        List<String> list = new ArrayList<>();
        if(s.length() == 0) {
            list.add("");
            return list;
        }
        for(String word : wordDict) {
            if(s.startsWith(word)) {
                List<String> tmp = wordBreak(s.substring(word.length()), wordDict, map);
                for(String str : tmp) {
                    list.add(word + (str.length() == 0 ? "" : " ") + str);
                }
            }
        }
        map.put(s, list);
        return list;
    }

    /*method0.0 backtracking LTE*/
//	public List<String> wordBreak(String s, List<String> wordDict) {
//		List<String> res = new ArrayList<>();
//		if(s == null || s.length() == 0 || wordDict == null){
//			return res;
//		}
//		backtrack(s, wordDict, 0, "", res);
//		return res;
//    }
//	private void backtrack(String s, List<String> wordDict, int start, String prefix, List<String> res){
//		if(start == s.length()){
//			res.add(prefix.substring(0, prefix.length() - 1));	//remove last empty space
//		}
//		for(int i = start + 1; i <= s.length(); i++){
//			String tmp = s.substring(start, i);
//			if(wordDict.contains(tmp)){
//				String old = prefix;
//				prefix += tmp + " ";
//				backtrack(s, wordDict, i, prefix, res);
//				prefix = old;
//			}
//		}
//	}

    /*method0.1 clear than method1*/
//	public List<String> wordBreak(String s, Set<String> wordDict) {
//        return word_Break(s, wordDict, 0);
//    }
//    public List<String> word_Break(String s, Set<String> wordDict, int start) {
//        LinkedList<String> res = new LinkedList<>();
//        if (start == s.length()) {
//            res.add("");
//        }
//        for (int end = start + 1; end <= s.length(); end++) {
//            if (wordDict.contains(s.substring(start, end))) {
//                List<String> list = word_Break(s, wordDict, end);
//                for (String l : list) {
//                    res.add(s.substring(start, end) + (l.equals("") ? "" : " ") + l);
//                }
//            }
//        }
//        return res;
//    }
    // bottom-up
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        List<String> res = new ArrayList<>();

        List<List<String>> lists = new ArrayList<>();
        lists.add(new ArrayList<>());
        lists.get(0).add("");

        for(int i = 1; i <= s.length(); i++) {
            List<String> list = new ArrayList<>();
            for(int j = 0; j < i; j++) {
                String w = s.substring(j, i);
                if(set.contains(w)) {
                    for(String prefix : lists.get(j)) {
                        list.add((prefix + " " + w).trim());
                    }
                }
            }
            lists.add(list);
        }
        for(String tmp : lists.get(s.length())) {
            res.add(tmp);
        }
        return res;
    }
    // better than first two methods
    /*Set<String> wordSet;
    Map<Integer, List<String>> memo;
    public List<String> wordBreak(String s, List<String> wordDict) {
        if(s == null || s.length() == 0) {
            return new ArrayList<>();
        }
        wordSet = new HashSet<>(wordDict);
        memo = new HashMap<>();
        return backtrack(s, 0);
    }
    private List<String> backtrack(String s, int start) {
        if(memo.containsKey(start)) {
            return memo.get(start);
        }
        List<String> list = new ArrayList<>();
        if(start == s.length()) {
            list.add("");
            return list;
        }
        for(int i = start + 1; i <= s.length(); i++) {
            String word = s.substring(start, i);
            if(wordSet.contains(word)) {
                for(String next : backtrack(s, i)) {
                    list.add(word + (next.length() == 0 ? "" : " " + next));
                }
            }
        }
        memo.put(start, list);
        return list;
    }*/
}
