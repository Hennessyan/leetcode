package amazon;
// Slowest Key
public class Q1629 {
    // O(n) O(1)
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int n = releaseTimes.length, longest = releaseTimes[0];
        char res = keysPressed.charAt(0);
        for(int i = 1; i < n; i++) {
            int duration = releaseTimes[i] - releaseTimes[i - 1];
            if(duration > longest || (duration == longest && res < keysPressed.charAt(i))) {
                longest = duration;
                res = keysPressed.charAt(i);
            }
        }
        return res;
    }
}
