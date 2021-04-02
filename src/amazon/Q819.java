package amazon;

import java.util.*;

// Most Common Word
public class Q819 {
    // O(m+n) O(m+n)
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> banSet = new HashSet<>(Arrays.asList(banned));
        Map<String, Integer> counts = new HashMap<>();
        int fre = 0;
        String ans = "";
        paragraph += ".";
        char[] chs = paragraph.toLowerCase().toCharArray();
        StringBuffer sb = new StringBuffer();
        for(char c : chs) {
            if(Character.isLetter(c)) {
                sb.append(c);
            } else if(sb.length() > 0) {
                String word = sb.toString();
                if(!banSet.contains(word)) {
                    int count = counts.getOrDefault(word, 0) + 1;
                    counts.put(word,  count);
                    if(fre < count) {
                        fre = count;
                        ans = word;
                    }
                }
                sb = new StringBuffer();
            }
        }
        return ans;
    }
}
