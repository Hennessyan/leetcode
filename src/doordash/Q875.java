package doordash;
// Koko Eating Bananas
public class Q875 {
    // O(nlgm) O(1)
    public int minEatingSpeed(int[] piles, int h) {
        int l = 1, r = 1;
        for(int p : piles) {
            r = Math.max(r, p);
        }
        while(l < r) {
            int m = l + (r - l) / 2;
            int spend = 0;
            for(int p : piles) {
                spend += Math.ceil((double) p / m);
            }
            if(spend <= h) {
                r = m;
            } else l = m + 1;
        }
        return l;
    }
}
