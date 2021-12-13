package amazon;
// Number of Wonderful Substrings
public class Q1915 {

    /**
                   a b a b c
     mask        0 1 3 2 0 4
     res           0 0 0 1 1
     count[mask] 1 1 1 1 2 1
     **/
    // https://leetcode.com/problems/number-of-wonderful-substrings/discuss/1299525/Count-bitmasks-with-picture
    public long wonderfulSubstrings(String word) {
        if(word == null || word.length() == 0) return 0;
        long[] count = new long[1024]; // first 10 characters [a~j]
        long ans = 0l;
        count[0] = 1;
        int mask = 0;
        for(char c : word.toCharArray()) {
            mask ^= 1 << (c - 'a');
            ans += count[mask];     // substring which letters appear only even times
            // find only one letter appears odd times case
            for(int i = 0; i < 10; i++) {
                ans += count[mask ^ (1 << i)];
            }
            count[mask]++;
        }
        return ans;
    }
}
