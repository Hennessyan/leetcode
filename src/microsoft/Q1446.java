package microsoft;
// Consecutive Characters
public class Q1446 {

    public int maxPower(String s) {
        if(s == null || s.length() == 0) return 0;
        int max = 0, n = s.length(), count = 1;
        for(int i = 0; i < n - 1; i++) {
            if(s.charAt(i) == s.charAt(i+1)) {
                count++;
            } else {
                max = Math.max(max, count);
                count = 1;
            }
        }
        return Math.max(max, count);
    }
}
