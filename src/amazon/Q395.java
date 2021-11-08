package amazon;

import java.util.Arrays;

// Longest Substring with At Least K Repeating Characters
public class Q395 {
    // O(unique * N) O(1) => O(26n => n) O(1)
    public int longestSubstring0(String s, int k) {
        char[] str = s.toCharArray();
        int[] countMap = new int[26];
        int maxUnique = getMaxUniqueLetters(s);
        int result = 0;
        for (int currUnique = 1; currUnique <= maxUnique; currUnique++) {
            // reset countMap
            Arrays.fill(countMap, 0);
            int windowStart = 0, windowEnd = 0, idx = 0, unique = 0, countAtLeastK = 0;
            while (windowEnd < str.length) {
                // expand the sliding window
                if (unique <= currUnique) {
                    idx = str[windowEnd] - 'a';
                    if (countMap[idx] == 0) unique++;
                    countMap[idx]++;
                    if (countMap[idx] == k) countAtLeastK++;
                    windowEnd++;
                }
                // shrink the sliding window
                else {
                    idx = str[windowStart] - 'a';
                    if (countMap[idx] == k) countAtLeastK--;
                    countMap[idx]--;
                    if (countMap[idx] == 0) unique--;
                    windowStart++;
                }
                if (unique == currUnique && unique == countAtLeastK)
                    result = Math.max(windowEnd - windowStart, result);
            }
        }

        return result;
    }
    // use this method.
    public int longestSubstring1(String s, int k) {
        if(s == null || s.length() < k) return 0;
        int uniques = getMaxUniqueLetters(s);
        int ans = 0, n = s.length();
        int[] fre = new int[26];
        for(int uni = 1; uni <= uniques; uni++) {
            int count = 0, l = 0, r = 0, atLeastK = 0;
            Arrays.fill(fre, 0);
            while(r < n) {
                int cur = s.charAt(r++) - 'a';
                if(fre[cur] == 0) count++;
                if(++fre[cur] == k) atLeastK++;
                while(count > uni) {
                    int pre = s.charAt(l++) - 'a';
                    if(fre[pre] == k) atLeastK--;
                    if(--fre[pre] == 0) count--;
                }
                if(count == uni && count == atLeastK) {
                    ans = Math.max(ans, r - l);
                }
            }
        }
        return ans;
    }

    // get the maximum number of unique letters in the string s
    int getMaxUniqueLetters(String s) {
        boolean map[] = new boolean[26];
        int maxUnique = 0;
        for (int i = 0; i < s.length(); i++) {
            if (!map[s.charAt(i) - 'a']) {
                maxUnique++;
                map[s.charAt(i) - 'a'] = true;
            }
        }
        return maxUnique;
    }

    //时间复杂度是 O(n),  递归每层是 O(n), 最多删除 26 个字母, 递归 26 层, O(26*n) = O(n)
    //针对因不满足情况的字符而取子字符串的情况,子字符串中如果有新的不满足,那么一定跟当前的不同,最多会有26次.
    //对于子串中原本满足但是分割后不满足的,也就是重复当前长度次数的重复调用longestSubstring.整体时间是子串的重复字符长度.
    //正好用来当作遍历字符串的消耗. 如果满足条件的,那也是遍历当前长度取新的值,然后从新的不满足条件的(与上次不同的)字符处截取。
    // 所以可以看作每层递归O(N),最多26层.
    public int longestSubstring(String s, int k) {
        if(s == null || s.length() == 0 || k <= 0) {
            return 0;
        }
        int len = s.length();
        int[] count = new int[26];
        for(char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        if(valid(count, s, len, k)) {
            return len;
        }
        int l = 0, r = 0, max = 0;
        while(r < len) {
            char c = s.charAt(r);
            if(count[c - 'a'] < k) {
                max = Math.max(max, longestSubstring(s.substring(l, r), k));
                l = r + 1;
            }
            r++;
        }
        max = Math.max(max, longestSubstring(s.substring(l), k));
        return max;
    }
    private boolean valid(int[] count, String s, int len, int k) {
        for(int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if(count[c - 'a'] < k) {
                return false;
            }
        }
        return true;
    }
}
