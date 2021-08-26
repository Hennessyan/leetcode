package google;
// Add Strings
public class Q415 {
    // O(n1 + n2) O(n1 + n2)
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
        StringBuilder sb = new StringBuilder();
        while(i >= 0 || j >= 0) {
            if(i >= 0) {
                carry += (num1.charAt(i--) - '0');
            }
            if(j >= 0) {
                carry += (num2.charAt(j--) - '0');
            }
            sb.append(carry % 10);
            carry /= 10;
        }
        if(carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }
}
