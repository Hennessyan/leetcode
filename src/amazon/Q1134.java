package amazon;
// Armstrong Number
public class Q1134 {
    // O(n) O(n) / O(1)
    public int getSumOfKthPowerOfDigits(int N, int k) {
        // `result` stores the result of sum of k'th power of each digit.
        int result = 0;

        // Run until N is not 0
        while(N != 0) {
            // Modulo 10 gives us the last digit
            // Add digit ^ k to the result
            result += Math.pow(N % 10, k);

            // Remove the last digit.
            N /= 10;
        }
        return result;
    }
    public boolean isArmstrong(int N) {
        // O(n) space
        int length = String.valueOf(N).length();

        // O(1) space
        //int length = (int)Math.log10(N) + 1;

        // O(1) space without build-in method
//        int length = 0;
//        int tempN = N;
//        while (tempN != 0) {
//            length++;
//            tempN /= 10;
//        }

        // Return true if Sum of k'th power of digits equals original number.
        return getSumOfKthPowerOfDigits(N, length) == N;
    }
}
