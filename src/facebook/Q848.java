package facebook;
// Shifting Letters
public class Q848 {

    public String shiftingLetters1(String s, int[] shifts) {
        char[] chs = s.toCharArray();
        int n = shifts.length;
        for(int i = n - 2; i >= 0; i--) {
            shifts[i] = (shifts[i] + shifts[i + 1]) % 26;
        }
        for(int i = 0; i < n; i++) {
            chs[i] = (char) ('a' + (chs[i] - 'a' + shifts[i]) % 26);
        }
        return String.valueOf(chs);
    }

    public String shiftingLetters(String s, int[] shifts) {
        char[] chs = s.toCharArray();
        int n = shifts.length, num = 0;
        for(int i = n - 1; i >= 0; i--) {
            num = (num + shifts[i]) % 26;
            chs[i] += num;
            if(chs[i] > 'z') {
                chs[i] -= 26;
            }
        }
        return String.valueOf(chs);
    }
}
