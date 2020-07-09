package challenge.june;
// Power of Two
public class Q231 {

    public static void main(String[] args) {
        Q231 q = new Q231();
        System.out.println(q.isPowerOfTwo(15));

    }

    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
