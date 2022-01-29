package linkedin;
// Bulb Switcher
public class Q319 {
    /*
        The idea of this issue is find the odd operation #.
        for prime
         -> 1 and itself -> even operations.
        for non-prime
         -> 12 => [1,12],[2,6],[3,4] => even operations
         -> 9 =>  [1,9],[3,3] => odd operations
        => find # of squares less than n => Math.sqrt(n)
            -> 1,4,9,16,25 => 1,2,3,4,5
     */
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }
}
