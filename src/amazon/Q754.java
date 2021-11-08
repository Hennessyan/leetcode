package amazon;
// Reach a Number
public class Q754 {

    // Math - O(sqrt(target)) O(1) - (1+k)*k/2 = target
    // S = 1 + 2 + 3 + ... + k => target
    // some num with positive signal, others with negative.
    // if target < 0, just use flip signal for the combination => Math.abs(target) first

    // delta = S - target
    // if delta is even, we can find the combination with value delta / 2 from [1,k] to reach the result
    // if delta is odd, we need to consider k + 1 =>
    //      if k + 1 is odd (k % 2 == 0) => done
    //      if k + 1 is even (k % 2 == 1), we need add k + 2 => k + 1 + k % 2
    public int reachNumber(int target) {
        target = Math.abs(target);
        int k = 0;
        while(target > 0) {
            target -= ++ k;
        }
        return target % 2 == 0 ? k : (k + 1 + k % 2);
    }
}
