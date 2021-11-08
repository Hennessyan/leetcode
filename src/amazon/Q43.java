package amazon;
// Multiply Strings
public class Q43 {

    public String multiply(String num1, String num2) {
        int l1 = num1.length() - 1, l2 = num2.length() - 1;
        int[] memo = new int[l1 + l2 + 2];
        for(int i = l1; i >= 0; i--) {
            for(int j = l2; j >= 0; j--) {
                int val = (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + memo[i + j + 1];
                memo[i + j + 1] = val % 10;
                memo[i + j] += val / 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < memo.length; i++) {
            if(sb.length() > 0 || memo[i] != 0) {
                sb.append(memo[i]);
            }
        }
        return sb.length() == 0 ? "0" : sb.toString();  // "0" "0" => "0"
    }
}
