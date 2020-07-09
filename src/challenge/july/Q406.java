package challenge.july;

// Hamming Distance
// Q762(E)
public class Q406 {

    public static void main(String[] args) {
        Q406 q = new Q406();
        System.out.println(q.hammingDistance(1, 4));   // 2
    }

    // 0 0 0 1
    // 0 1 0 0
    // O(1) O(1)
    public int hammingDistance0(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

    public int hammingDistance1(int x, int y) {
        int xor = x ^ y;
        int distance = 0;
        while (xor != 0) {
            if (xor % 2 == 1) {
                distance++;
            }
            xor >>= 1;
        }
        return distance;
    }

    public int hammingDistance(int x, int y) {
        int xor = x ^ y;
        int distance = 0;
        while (xor != 0) {
            distance++;
            xor = xor & (xor - 1);
        }
        return distance;
    }
}
