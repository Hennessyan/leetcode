package challenge.feb21;
// Broken Calculator
public class Q991 {
    // O(lgY) O(1)
    // earlier deduct operation can be amplified by multiply operation,
    // greedily doing multiplication might not reach optimal solution as an additional early subtraction can have a huge effect.
    // Whereas with addition and division, earlier addition will be diminished by later divisions.
    // It is thus always better to do division wherever possible.
    /*
    针对x的减和乘 => 乘先并不是最优
    反过来看y => 除先加后总是最优的
    */
    public int brokenCalc(int X, int Y) {
        int ans = 0;
        while(X < Y) {
            ans++;
            if(Y % 2 == 1) {
                Y++;
            } else {
                Y /= 2;
            }
        }
        return ans + X - Y;
    }
}
