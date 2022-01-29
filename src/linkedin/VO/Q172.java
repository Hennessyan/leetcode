package linkedin.VO;
// Factorial Trailing Zeroes
public class Q172 {
    // => # of factor pair for 2 & 5 => if we have 5, we always has the even number
    // => # of factor of 5
    // O(lg5(n)) O(1)
    public int trailingZeroes(int n) {
        int count = 0;
        while(n != 0) {
            n /= 5;
            count += n;
        }
        return count;
    }
}
