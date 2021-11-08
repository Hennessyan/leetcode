package microsoft;
// Reverse Words in a String II
// Q151 Q557
public class Q186 {

    public void reverseWords(char[] s) {
        reverse(s, 0, s.length - 1);
        int i = 0, j = 0, len = s.length;
        while(j < len) {
            if(s[j] == ' ') {
                reverse(s, i, j - 1);
                i = j + 1;
            }
            j++;
        }
        reverse(s, i, j - 1);
    }
    public void reverseWords1(char[] s) {
        reverse(s, 0, s.length - 1);
        int l = 0, r = 0, n = s.length;
        while(r < n) {
            while(r < n && s[r] != ' ') {
                r++;
            }
            reverse(s, l, r - 1);
            r++;
            l = r;
        }
    }
    private void reverse(char[] s,int l, int r) {
        while(l < r) {
            char c  = s[l];
            s[l++] = s[r];
            s[r--] = c;
        }
    }
}
