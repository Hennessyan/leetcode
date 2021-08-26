package amazon;
// Flip String to Monotone Increasing
public class Q926 {
    // O(n) O(n)
    public int minFlipsMonoIncr(String s) {
        int n = s.length();
        int[] p = new int[n + 1];   // sum of 1 until current index.
        for(int i = 1; i <= n; i++) {
            p[i] = p[i - 1] + (s.charAt(i - 1) == '1' ? 1 : 0);
        }
        int min = n;
        for(int i = 0; i <= n; i++) {   // i should start from 0 => s = "11011"
            // as we want x zeros followed by N-x ones, we need to flip
            // p[x] ones and (N - x - (p[N] - p[x]) zeros
            min = Math.min(min, p[i] + n - i - (p[n] - p[i]));
        }
        return min;
    }
    // 000 11 000 11
    public int minFlipsMonoIncr1(String s) {
        char[] ch = s.toCharArray();
        int onescount =0;
        int flipcount =0;

        for(int i = 0 ; i < s.length(); i++){
            if (ch[i] == '0') {
                if(onescount ==0)continue;
                flipcount++;
            } else {
                onescount++;
            }
            if (flipcount > onescount) {
                flipcount = onescount;
            }
        }

        return flipcount;
    }
}
