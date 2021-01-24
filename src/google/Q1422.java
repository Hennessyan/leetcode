package google;
// Maximum Score After Splitting a String
public class Q1422 {
    // O(n) O(n)
    public int maxScore(String s) {
        int[] numLeftZeros = new int[s.length()];

        int numZero = 0;
        for(int i = 0 ; i < s.length() ; i++) {
            if (s.charAt(i) == '0') {
                numZero++;
            }
            numLeftZeros[i] = numZero;
        }

        int maxScore = 0;
        int numOne = 0;
        for(int i = s.length() - 1 ; i >= 0 ; i--) {
            if (i < s.length() - 1) {
                maxScore = Math.max(maxScore, numOne + numLeftZeros[i]);
            }
            if (s.charAt(i) == '1') {
                numOne++;
            }
        }
        return maxScore;
    }
    // O(n) O(n)
    public int maxScore1(String s) {
        //strategy: Precompute + Greedy
        int one = 0;
        //precompute number of ones
        for (char c : s.toCharArray()) {    // O(n)
            one += c - '0';
        }
        int zero = 0;
        int max = 0;
        //include ith char into left String
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '0') {
                //need to first update zero because it is in the left String
                //update max as zero + one
                max = Math.max(max, ++zero + one);
            } else {
                //need to first update one because it is not in the right String
                //update max as zero + one
                max = Math.max(max, --one + zero);
            }
        }
        return max;
    }
}
