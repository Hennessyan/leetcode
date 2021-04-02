package amazon;
// Pairs of Songs With Total Durations Divisible by 60
public class Q1010 {
    // O(n) O(1)
    public int numPairsDivisibleBy60(int[] time) {
        if(time == null || time.length < 2) {
            return 0;
        }
        int total = 0;
        int[] memo = new int[60];
        for(int t : time) {
            int val = t % 60;
            if(val == 0) {
                total += memo[0];
            } else {
                total += memo[60 - val];
            }
            memo[val]++;
        }
        return total;
    }
}
