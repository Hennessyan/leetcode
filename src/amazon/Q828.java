package amazon;
// Count Unique Characters of All Substrings of a Given String
public class Q828 {

    public static void main(String[] args) {
        Q828 q = new Q828();
        System.out.println(q.uniqueLetterString("abc"));
        System.out.println(q.uniqueLetterString("aba"));
    }

    /**
     *   https://blog.csdn.net/qq_17550379/article/details/103064459
     *   A  B    C      B   D B
     *   1 1+2  1+2+3
     *                  B
     *                 CB
     *                BCB
     *               ABCB
     *            6+4-2*2
     *                       B
     *                      DB
     *                     BDB
     *                    CBDB
     *                   BCBDB
     *                  ABCBDB
     *       (6+4-2*2)+6-2*4+2
     *   f(i) = f(i-1) + i - 2 * m[s[i]] + n[s[i]] (1 based index)
     *   m - last position of s[i]
     *   n - last of last position of s[i]
     */
    public int uniqueLetterString(String s) {
        int len = s.length();
        long ans = 0, cur = 0, mod = 1_000_000_007l;
        long m[] = new long[26], n[] = new long[26];
        for(int i = 1; i <= len; i++) {
            int index = s.charAt(i - 1) - 'A';
            cur = (cur + i - 2 * m[index] + n[index]) % mod;
            ans = (ans + cur) % mod;
            n[index] = m[index];
            m[index] = i;
        }
        return (int) ans;
    }

}
