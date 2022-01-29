package challenge.may;
// Find the Town Judge
public class Q997 {

    public static void main(String[] args) {
        Q997 q = new Q997();

    }

    /**
     *  Because of note below, we can use method2 directly.
     *  1 <= N <= 1000
     * trust.length <= 10000
     * trust[i] are all different
     * trust[i][0] != trust[i][1]
     * 1 <= trust[i][0], trust[i][1] <= N
     */
    // method1
    public int findJudge1(int N, int[][] trust) {
        int[] input = new int[N + 1];   // be trusted
        int[] output = new int[N + 1];  // trust
        for(int[] t : trust) {
            output[t[0]]++;
            input[t[1]]++;
        }
        for(int i = 1; i < N + 1; i++) {
            if(output[i] == 0 && input[i] == N - 1) {
                return i;
            }
        }
        return -1;
    }
    // method2
    public int findJudge(int N, int[][] trust) {
        if(trust.length < N - 1) return -1;

        int[] count = new int[N + 1];
        for(int[] t : trust) {
            count[t[0]]--;
            count[t[1]]++;
        }
        for(int i = 1; i <= N; i++) {
            if(count[i] == N - 1) {
                return i;
            }
        }
        return -1;
    }
}
