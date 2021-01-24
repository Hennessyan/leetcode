package challenge.dec;
// Pairs of Songs With Total Durations Divisible by 60
public class Q1010 {
    // O(n) O(1)
    public int numPairsDivisibleBy60(int[] time) {
        if(time == null || time.length == 0) {
            return 0;
        }
        int[] arr = new int[60];
        for(int t : time) {
            arr[t % 60]++;
        }
        int total = 0;
        total += arr[0] * (arr[0] - 1) / 2;
        total += arr[30] * (arr[30] - 1) / 2;
        for(int i = 1; i < 30; i++) {
            total += arr[i] * arr[60 - i];
        }
        return total;
    }

    public int numPairsDivisibleBy60_2(int[] time) {
        if(time == null || time.length == 0) {
            return 0;
        }
        int[] arr = new int[60];
        int total = 0;
        for(int t : time) {
            if(t % 60 == 0) {
                total += arr[0];
            } else {
                total += arr[60 - t % 60];
            }
            arr[t % 60]++;
        }
        return total;
    }
}
