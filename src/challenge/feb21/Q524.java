package challenge.feb21;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Longest Word in Dictionary through Deleting
public class Q524 {
    // O(n * x) O(x) : x - len of longest
    public String findLongestWord(String s, List<String> d) {
        String longest = "";
        for(String tmp : d) {
            if(isSequence(s, tmp)) {
                if(tmp.length() > longest.length() ||
                        (tmp.length() == longest.length() && tmp.compareTo(longest) < 0)) {
                    longest = tmp;
                }
            }
        }
        return longest;
    }
    private boolean isSequence(String a, String b) {
        int j = 0;
        for(int i = 0; i < a.length() && j < b.length(); i++) {
            if(a.charAt(i) == b.charAt(j)) {
                j++;
            }
        }
        return j == b.length();
    }
    // O(n * x * lgn + n * x) O(lgn) - ave SC of quick sort
    public String findLongestWord1(String s, List<String> d) {
        Collections.sort(d, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return a.length() == b.length() ? a.compareTo(b) : b.length() - a.length();
            }
        });

        for(String tmp : d) {
            if(isSequence(s, tmp)) {
                return tmp;
            }
        }
        return "";
    }
}
