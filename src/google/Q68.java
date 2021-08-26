package google;

import java.util.ArrayList;
import java.util.List;

// Text Justification
public class Q68 {
    // O(m*n) O(m*n) m - words.length, n - max(words[i].length)
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        if(words == null || words.length == 0) {
            return res;
        }
        int i = 0, n = words.length;
        while(i < n) {
            int start = i, end = i, sum = 0;
            while(end < n && sum + words[end].length() <= maxWidth) {
                sum += words[end++].length() + 1;
            }
            boolean last = end == n;
            i = end;
            res.add(helper(start, end - 1, maxWidth -  sum + end - start, last, words));
        }
        return res;
    }
    // O(m*n / maxWidth * maxWidth) => O(mn)
    private String helper(int start, int end, int numberOfSpace, boolean last, String[] words) {
        StringBuilder sb = new StringBuilder();
        int spacePerWord = start < end ? numberOfSpace / (end - start) : numberOfSpace;
        int extraSpace = start < end ? numberOfSpace % (end - start) : 0;
        for(int i = start; i <= end; i++) {
            sb.append(words[i]);

            if(last) {
                if(numberOfSpace-- > 0) {   //注意要使用numberOfSpace > 0来检查有多少个space剩余,从而决定是否需要给sb加空格！！！
                    sb.append(" ");
                }
            } else if(numberOfSpace > 0) {  // necessary: "this  is  an" rather than "this  is  an  "
                int space = spacePerWord + (extraSpace-- > 0 ? 1 : 0);
                while(space-- > 0) {
                    numberOfSpace--;
                    sb.append(" ");
                }
            }
        }
        if(last) {
            while(numberOfSpace-- > 0) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
