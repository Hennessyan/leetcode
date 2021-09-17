package microsoft;
// Reverse Only Letters
public class Q917 {
    // O(n) O(n)
    public String reverseOnlyLetters(String S) {
        StringBuilder sb = new StringBuilder();
        int n = sb.length(), j = n - 1;
        for(int i = 0; i < n; i++) {
            if(Character.isLetter(S.charAt(i))) {
                while(!Character.isLetter(S.charAt(j))) {
                    j--;
                }
                sb.append(S.charAt(j--));
            } else {
                sb.append(S.charAt(i));
            }
        }
        return sb.toString();
    }

    public String reverseOnlyLetters1(String s) {
        char[] chs = s.toCharArray();
        int l = 0, r = chs.length - 1;
        while(l < r) {
            while(l < r && (chs[l] < 65 || (chs[l] > 90 && chs[l] < 97))) {
                l++;
            }
            while(l < r && (chs[r] < 65 || (chs[r] > 90 && chs[r] < 97))) {
                r--;
            }
            if(l < r) {
                swap(chs, l, r);
                l++;
                r--;
            }
        }
        return String.valueOf(chs);
    }
    private void swap(char[] chs, int l, int r) {
        char tmp = chs[l];
        chs[l] = chs[r];
        chs[r] = tmp;

    }
}
