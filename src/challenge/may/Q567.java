package challenge.may;

import java.util.Arrays;

// Permutation in String
public class Q567 {

    public static void main(String[] args) {
        Q567 q = new Q567();
        System.out.println(q.checkInclusion("rvwrk", "lznomzggwrvrkxecjaq"));
    }

    // O(l1 + (l2 - l1) O(1)
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        int[] s1c = new int[26];
        int[] s2c = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            s1c[s1.charAt(i) - 'a']++;
            s2c[s2.charAt(i) - 'a']++;
        }
        int count = 0;
        for (int i = 0; i < 26; i++) {
            if (s1c[i] == s2c[i]) {
                count++;
            }
        }
        for (int i = 0; i < s2.length() - s1.length(); i++) {
            int l = s2.charAt(i) - 'a';
            int r = s2.charAt(i + s1.length()) - 'a';
            if (count == 26) {
                return true;
            }
            if (l == r) {
                continue;
            }
            s2c[r]++;
            if (s2c[r] == s1c[r]) {
                count++;
            } else if (s2c[r] == s1c[r] + 1) {  //注意只在"平衡"被打破时--
                count--;
            }
            s2c[l]--;
            if (s2c[l] == s1c[l]) {
                count++;
            } else if (s2c[l] + 1 == s1c[l]) { //注意只在"平衡"被打破时--
                count--;
            }
        }
        return count == 26;
    }
    // TLE
    // O(l1lgl1 + (l2 - l1)l1lgl1) O(l1)
    public boolean checkInclusion1(String s1, String s2) {
        s1 = sort(s1);
        for (int i = 0; i <= s2.length() - s1.length(); i++) {
            if (s1.equals(sort(s2.substring(i, i + s1.length()))))
                return true;
        }
        return false;
    }
    public String sort(String s) {
        char[] t = s.toCharArray();
        Arrays.sort(t);
        return new String(t);
    }
}
