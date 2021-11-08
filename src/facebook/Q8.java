package facebook;
// String to Integer (atoi)
public class Q8 {

    public int myAtoi(String str) {
        if(str == null || str.length() == 0) {
            return 0;
        }
        str = str.trim();
        int i = 0, len = str.length();
        char[] chs = str.toCharArray();
        int sign = 1;
        if(i < len && (chs[i] == '+' || chs[i] == '-')) {
            sign = chs[i++] == '+' ? 1 : -1;
        }
        int val = 0;
        while(i < len) {
            if(chs[i] < '0' || chs[i] > '9') {
                break;
            }
            int tmp = chs[i++] - '0';
            if((val == Integer.MAX_VALUE / 10 && tmp > 7) || (val > Integer.MAX_VALUE / 10)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            val = val * 10 + tmp;
        }
        return val * sign;
    }
}
