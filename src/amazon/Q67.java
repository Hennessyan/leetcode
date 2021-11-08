package amazon;
// Add Binary
public class Q67 {
    // O(max(m,n)) O(max(m,n))
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int m = a.length() - 1, n = b.length() - 1, carry = 0;
        while(m >= 0 || n >= 0 || carry > 0) {
            carry += m >= 0 ? a.charAt(m--) - '0' : 0;
            carry += n >= 0 ? b.charAt(n--) - '0' : 0;
            sb.append(carry % 2);
            carry /= 2;
        }
        return sb.reverse().toString();
    }
}
