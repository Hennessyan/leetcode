package challenge;
// Minimum Cost to Move Chips to The Same Position
// Math - Greedy
public class Q1217 {

    public int minCostToMoveChips(int[] position) {
        int odd = 0, even = 0;
        for(int p : position) {
            if(p % 2 == 0) {
                even += 1;
            } else {
                odd += 1;
            }
        }
        return Math.min(even, odd);
    }
}
