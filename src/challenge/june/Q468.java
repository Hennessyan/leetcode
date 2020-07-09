package challenge.june;

// Validate IP Address
public class Q468 {

    public static void main(String[] args) {
        Q468 q = new Q468();
        System.out.println(q.validIPAddress("172.16.254.1"));   // IPv4
        System.out.println(q.validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334"));  // IPv6
        System.out.println("256.256.256.256");  // Neither
    }

    public String validIPAddress(String IP) {
        if (IP == null || IP.length() < 7) {
            return "Neither";
        }
        String[] v4 = IP.split("\\.", -1);
        String[] v6 = IP.split("\\:", -1);  // if limit = 0, trailing empty strings will be discarded
        if (v4.length == 4) {
            return validateIPv4(v4);
        } else if (v6.length == 8) {
            return validateIPv6(v6);
        }
        return "Neither";
    }

    private String validateIPv4(String[] arr) {
        for (String s : arr) {
            int len = s.length();
            if (len == 0 || len > 3) {
                return "Neither";
            }
            if (s.charAt(0) == '0' && len != 1) {
                return "Neither";
            }
            for (char ch : s.toCharArray()) {
                if (!Character.isDigit(ch)) return "Neither";
            }
            if (Integer.parseInt(s) > 255) {
                return "Neither";
            }
        }
        return "IPv4";
    }

    private String validateIPv6(String[] arr) {
        String hexdigits = "0123456789abcdefABCDEF";
        for (String x : arr) {
            if (x.length() == 0 || x.length() > 4) return "Neither";
            for (Character ch : x.toCharArray()) {
                if (hexdigits.indexOf(ch) == -1) return "Neither";
            }
        }
        return "IPv6";
    }

    // https://leetcode.com/problems/validate-ip-address/solution/
    public String validateIPv4(String IP) {
        String[] nums = IP.split("\\.", -1);
        for (String x : nums) {
            // Validate integer in range (0, 255):
            // 1. length of chunk is between 1 and 3
            if (x.length() == 0 || x.length() > 3) return "Neither";
            // 2. no extra leading zeros
            if (x.charAt(0) == '0' && x.length() != 1) return "Neither";
            // 3. only digits are allowed
            for (char ch : x.toCharArray()) {
                if (! Character.isDigit(ch)) return "Neither";
            }
            // 4. less than 255
            if (Integer.parseInt(x) > 255) return "Neither";
        }
        return "IPv4";
    }

    public String validateIPv6(String IP) {
        String[] nums = IP.split(":", -1);
        String hexdigits = "0123456789abcdefABCDEF";
        for (String x : nums) {
            // Validate hexadecimal in range (0, 2**16):
            // 1. at least one and not more than 4 hexdigits in one chunk
            if (x.length() == 0 || x.length() > 4) return "Neither";
            // 2. only hexdigits are allowed: 0-9, a-f, A-F
            for (Character ch : x.toCharArray()) {
                if (hexdigits.indexOf(ch) == -1) return "Neither";
            }
        }
        return "IPv6";
    }

    public String validIPAddress1(String IP) {
        if (IP.chars().filter(ch -> ch == '.').count() == 3) {  //check count can avoid prefix '.' or ':'
            return validateIPv4(IP);
        }
        else if (IP.chars().filter(ch -> ch == ':').count() == 7) {
            return validateIPv6(IP);
        }
        else return "Neither";
    }
}
