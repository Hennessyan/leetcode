package amazon;
// Maximum Repeating Substring
public class Q1668 {

    public int maxRepeating0(String sequence, String word) {
        int count=0;

        StringBuilder str = new StringBuilder(word);
        while(sequence.contains(str.toString()))    // bad performance
        {
            count++;
            str.append(word);
        }

        return count;
    }

    public int maxRepeating(String sequence, String word) {
        if(word == null || sequence == null || word.length() > sequence.length()) {
            return 0;
        }
        int max = 0, m = sequence.length(), n = word.length();
        int[] dp = new int[m];
        for(int i = 0; i <= m - n; i++) {
            if(sequence.substring(i, i + n).equals(word)) {
                dp[i] = 1;
            }
        }
        for(int i = 0; i < m; i++) {
            if(i - n >= 0 && dp[i] != 0 && dp[i - n] != 0) {    // 只有不等于0的情况才可以相加!!!
                dp[i] += dp[i - n];
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    public int maxRepeating2(String sequence, String word) {
        if(word == null || sequence == null || word.length() > sequence.length()) {
            return 0;
        }
        int max = 0, m = sequence.length(), n = word.length();
        int r = m / n, l = 0;
        while(l <= r) {
            int mid = l + (r - l) / 2;
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < mid; j++) {
                sb.append(word);
            }
            if(sequence.contains(sb)) {
                max = Math.max(max, mid);
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return max;
    }
}
