package amazon;
// Gas Station
public class Q134 {
    // O(n) O(1)
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int cur = 0, total = 0, start = 0;
        for(int i = 0; i < gas.length; i++) {
            cur += gas[i] - cost[i];
            if(cur < 0) {
                total += cur;
                cur = 0;
                start = i + 1;
            }
        }
        total += cur;
        return total < 0 ? -1 : start;
    }
}
