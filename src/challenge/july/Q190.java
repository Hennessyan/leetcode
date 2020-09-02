package challenge.july;
// Reverse Bits
public class Q190 {
    // >> is arithmetic shift right, >>> is logical shift right.
    public int reverseBits(int n) {
        int res = 0;
        for(int i = 0; i < 32; i++) {
            res = (res << 1) | (n & 1);
            n >>>= 1;
        }
        return res;
    }
    // use this method in interview
    public int reverseBits1(int n) {
        int i = 31, res = 0;
        while(n != 0) {
            res += (n & 1) << i;
            n >>>= 1;   // Java can't use >>= 1
            i--;
        }
        return res;
    }
}
