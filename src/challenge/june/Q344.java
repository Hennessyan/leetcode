package challenge.june;

import java.util.Arrays;

// Reverse String
public class Q344 {

    public static void main(String[] args) {
        Q344 q = new Q344();
        char[] s = {'a', 'b', 'c'};
        q.reverseString(s);
        System.out.println(Arrays.toString(s));
    }

    public void reverseString(char[] s) {
        if (s == null || s.length < 2) {
            return;
        }
        int l = 0, r = s.length - 1;
        while (l < r) {
            char c = s[l];
            s[l++] = s[r];
            s[r--] = c;
        }
    }
}
